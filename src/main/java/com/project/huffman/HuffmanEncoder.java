package com.project.huffman;

import com.project.binary.BinarySequence;
import com.project.source.SourceSequence;

public class HuffmanEncoder {

    public BinarySequence encode(SourceSequence sequence, HuffmanCodebook codebook) {

        if (sequence == null) {
            throw new IllegalArgumentException("Sequence cannot be null.");
        }

        if (codebook == null) {
            throw new IllegalArgumentException("Codebook cannot be null.");
        }

        StringBuilder builder = new StringBuilder();
        sequence.symbols().stream()
                .map(codebook::getCode)
                .forEach(builder::append);

        return new BinarySequence(builder.toString());

    }
}