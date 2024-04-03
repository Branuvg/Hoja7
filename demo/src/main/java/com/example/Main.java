package com.example;

public class Main {
    public static void main(String[] args) {
        // Crea el diccionario a partir del archivo "diccionario.txt"
        BinaryTree<String, String> dictionary = BinaryTree.buildDictionary("demo\\src\\main\\java\\com\\example\\diccionario.txt");

        // Imprime el diccionario ya ordenado
        System.out.println("Sorted Dictionary:");
        dictionary.inorderTraversal();

        // Realizar la traducción y lo imprime
        String inputText = "demo\\src\\main\\java\\com\\example\\texto.txt";
        String translatedText = BinaryTree.translator(inputText, dictionary);
        System.out.println("\nTexto Traducido:");
        System.out.println(translatedText);
    }
}