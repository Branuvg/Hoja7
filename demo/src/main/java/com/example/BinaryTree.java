package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinaryTree<K extends Comparable<K>,V> {
    Nodo<K,V> root;
    
    public BinaryTree(){
        root = null;
    }

    public static BinaryTree<String, String> delDictionary(String filePath) {
        BinaryTree<String, String> dictionary = new BinaryTree<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.substring(1, line.length() - 1).split(", ");
                String key = parts[0].trim();
                String value = parts[1].trim();
                Association<String, String> association = new Association<>(key, value);
                dictionary.insertar(association);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static String translator(String inputFilePath, BinaryTree<String, String> dictionary) {
        StringBuilder traducido = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] palabras = line.split("\\s+");
                for (String word : palabras) {
                    String[] parts = word.split("(?<=\\p{Punct})|(?=\\p{Punct})");
                    for (String part : parts) {
                        if (part.matches("\\p{Punct}")) {
                            traducido.append(part);
                        } else {
                            String translatedPart = dictionary.search(part.toLowerCase());
                            if (translatedPart != null)
                                traducido.append(translatedPart).append(" ");
                            else
                                traducido.append("*").append(part).append("* ");
                        }
                    }
                    traducido.append(" ");
                }
                traducido.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return traducido.toString().trim();
    }

    private Nodo<K, V> Recursivo(Nodo<K, V> root, Association<K, V> association) {
        if (root == null) {
            root = new Nodo<>(association);
            return root;
        }
        if (association.key.compareTo(root.data.key) < 0)
            root.left = Recursivo(root.left, association);
        else if (association.key.compareTo(root.data.key) > 0)
            root.right = Recursivo(root.right, association);
        return root;
    }

    public void insertar(Association<K, V> association) {
        root = Recursivo(root, association);
    }

    private void RecursivoEnOrden(Nodo<K, V> root) {
        if (root != null) {
            RecursivoEnOrden(root.left);
            RecursivoEnOrden(root.right);
        }
    }

    public void Traversal() {
        RecursivoEnOrden(root);
    }

    private V BuscarEnRecursivo(Nodo<K, V> root, K key) {
        if (root == null || root.data.key.equals(key))
            return root != null ? root.data.value : null;
        if (key.compareTo(root.data.key) < 0)
            return BuscarEnRecursivo(root.left, key);
        else
            return BuscarEnRecursivo(root.right, key);
    }

    public V search(K key) {
        return BuscarEnRecursivo(root, key);
    }
}
