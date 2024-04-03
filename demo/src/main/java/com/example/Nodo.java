package com.example;

public class Nodo<K extends Comparable<K>, V> {
    Association<K,V> data;
    Nodo<K,V> left, right;

    public Nodo (Association<K,V> data){
        this.data = data;
        left = right = null;
    }
    
}
