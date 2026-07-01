package com.project.huffman;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodebookBuilder {

    public HuffmanCodebook build(HuffmanTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Tree cannot be null");
        }

        Map<String, String> codes = new HashMap<>();
        if (tree.root().isLeaf()) {
            codes.put(tree.root().getSymbol(), "0");
        } else {
            buildCodes(tree.root(), "", codes);
        }

        return new HuffmanCodebook(codes);
    }

    private void buildCodes(HuffmanNode node, String code, Map<String, String> codes) {
        if (node == null) { return; }
        if (node.isLeaf()) {
            codes.put(node.getSymbol(), code);
            return;
        }

        buildCodes(node.getLeft(), code + "0", codes);
        buildCodes(node.getRight(), code + "1", codes);
    }
}
