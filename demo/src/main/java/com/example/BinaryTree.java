package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinaryTree<K extends Comparable<K>,V> {
    Nodo<K,V> root;
    
    public BinaryTree(){
        root = null;
    }

    public static BinaryTree<String, String> buildDictionary(String filePath) {
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
        StringBuilder translatedText = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String[] parts = word.split("(?<=\\p{Punct})|(?=\\p{Punct})");
                    for (String part : parts) {
                        if (part.matches("\\p{Punct}")) {
                            translatedText.append(part);
                        } else {
                            String translatedPart = dictionary.search(part.toLowerCase());
                            if (translatedPart != null)
                                translatedText.append(translatedPart).append(" ");
                            else
                                translatedText.append("*").append(part).append("* ");
                        }
                    }
                    translatedText.append(" ");
                }
                translatedText.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translatedText.toString().trim();
    }

    private Nodo<K, V> insertRecursive(Nodo<K, V> root, Association<K, V> association) {
        if (root == null) {
            root = new Nodo<>(association);
            return root;
        }
        if (association.key.compareTo(root.data.key) < 0)
            root.left = insertRecursive(root.left, association);
        else if (association.key.compareTo(root.data.key) > 0)
            root.right = insertRecursive(root.right, association);
        return root;
    }

    public void insertar(Association<K, V> association) {
        root = insertRecursive(root, association);
    }

    private void inorderTraversalRecursive(Nodo<K, V> root) {
        if (root != null) {
            inorderTraversalRecursive(root.left);
            System.out.println("(" + root.data.key + ", " + root.data.value + ")");
            inorderTraversalRecursive(root.right);
        }
    }

    public void inorderTraversal() {
        inorderTraversalRecursive(root);
    }

    private V searchRecursive(Nodo<K, V> root, K key) {
        if (root == null || root.data.key.equals(key))
            return root != null ? root.data.value : null;
        if (key.compareTo(root.data.key) < 0)
            return searchRecursive(root.left, key);
        else
            return searchRecursive(root.right, key);
    }

    public V search(K key) {
        return searchRecursive(root, key);
    }
}
