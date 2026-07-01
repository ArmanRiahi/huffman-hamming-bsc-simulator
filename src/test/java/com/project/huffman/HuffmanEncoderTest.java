package com.project.huffman;

import com.project.binary.BinarySequence;
import com.project.source.SourceSequence;
import com.project.source.SymbolProbability;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncoderTest {

    @Test
    void shouldEncodeSourceSequence() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A",0.4),
                new SymbolProbability("B",0.3),
                new SymbolProbability("C",0.2),
                new SymbolProbability("D",0.1)
        );

        HuffmanTree tree =
                new HuffmanTreeBuilder().build(probabilities);

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder().build(tree);

        SourceSequence sequence =
                new SourceSequence(
                        List.of("A","B","C","D","A")
                );

        BinarySequence binary =
                new HuffmanEncoder()
                        .encode(sequence, codebook);

        assertNotNull(binary);

        assertFalse(binary.bits().isEmpty());
    }

}