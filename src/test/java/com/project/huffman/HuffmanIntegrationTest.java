package com.project.huffman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.project.binary.BinarySequence;
import com.project.source.SourceGenerator;
import com.project.source.SourceSequence;
import com.project.source.SymbolProbability;

import org.junit.jupiter.api.Test;

class HuffmanIntegrationTest {

    @Test
    void shouldEncodeAndDecodeSourceSequence() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        SourceSequence original = new SourceSequence(
                List.of(
                        "A",
                        "D",
                        "A",
                        "B",
                        "C",
                        "D",
                        "A",
                        "A",
                        "C",
                        "B"
                )
        );

        HuffmanTree tree =
                new HuffmanTreeBuilder().build(probabilities);

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder().build(tree);

        BinarySequence encoded =
                new HuffmanEncoder()
                        .encode(original, codebook);

        SourceSequence decoded =
                new HuffmanDecoder()
                        .decode(encoded, tree);

        assertEquals(original, decoded);

    }

    @Test
    void shouldEncodeAndDecodeLargeSequence() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A",0.4),
                new SymbolProbability("B",0.3),
                new SymbolProbability("C",0.2),
                new SymbolProbability("D",0.1)
        );

        SourceSequence sequence =
                new SourceGenerator()
                        .generate(probabilities,10000);

        HuffmanTree tree =
                new HuffmanTreeBuilder().build(probabilities);

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder().build(tree);

        BinarySequence encoded =
                new HuffmanEncoder()
                        .encode(sequence,codebook);

        SourceSequence decoded =
                new HuffmanDecoder()
                        .decode(encoded,tree);

        assertEquals(sequence,decoded);

    }

    @Test
    void shouldEncodeSingleSymbolSource() {

        List<SymbolProbability> probabilities =
                List.of(
                        new SymbolProbability("A",1.0)
                );

        SourceSequence original =
                new SourceSequence(
                        List.of(
                                "A",
                                "A",
                                "A",
                                "A",
                                "A"
                        )
                );

        HuffmanTree tree =
                new HuffmanTreeBuilder().build(probabilities);

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder().build(tree);

        BinarySequence encoded =
                new HuffmanEncoder()
                        .encode(original,codebook);

        SourceSequence decoded =
                new HuffmanDecoder()
                        .decode(encoded,tree);

        assertEquals(original,decoded);

    }
}
