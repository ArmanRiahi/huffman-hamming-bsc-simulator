package com.project.huffman;

import com.project.binary.BinarySequence;
import com.project.source.SourceSequence;

import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {

    public SourceSequence decode(BinarySequence sequence, HuffmanTree tree) {
        if (sequence == null) {
            throw new IllegalArgumentException("Binary sequence cannot be null.");
        }

        if (tree == null) {
            throw new IllegalArgumentException("Tree cannot be null.");
        }

        List<String> symbols = new ArrayList<>();
        if (tree.root().isLeaf()) {

            for (int i = 0; i < sequence.length(); i++) {
                symbols.add(tree.root().getSymbol());
            }

            return new SourceSequence(symbols);
        }

        HuffmanNode current = tree.root();
        String bits = sequence.bits();
        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);

            switch (bit) {
                case '0' -> current = current.getLeft();
                case '1' -> current = current.getRight();
                default -> throw new IllegalArgumentException("Invalid bit: " + bit);
            }

            if (current == null) {
                throw new IllegalStateException("Invalid Huffman tree.");
            }

            if (current.isLeaf()) {
                symbols.add(current.getSymbol());
                current = tree.root();
            }
        }

        if (current != tree.root()) {
            throw new IllegalArgumentException(
                    "Incomplete Huffman code."
            );
        }
        return new SourceSequence(symbols);
    }
}
