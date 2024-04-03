package com.example;

public class Main {
    public static void main(String[] args) {
        // Crea el diccionario a partir del archivo "diccionario.txt"
        BinaryTree<String, String> dictionary = BinaryTree.delDictionary("demo\\src\\main\\java\\com\\example\\diccionario.txt");

        // diccionario ya ordenado
        dictionary.Traversal();
        String inputText = "demo\\src\\main\\java\\com\\example\\texto.txt";
        String traducido = BinaryTree.translator(inputText, dictionary);
        System.out.println(traducido);
    }
}