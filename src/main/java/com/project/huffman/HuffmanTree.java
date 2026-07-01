package com.project.huffman;

public record HuffmanTree(HuffmanNode root) {

    public HuffmanTree {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null.");
        }
    }
}
