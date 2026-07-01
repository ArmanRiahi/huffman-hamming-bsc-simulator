package com.project.huffman;

import com.project.source.SymbolProbability;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodebookBuilderTest {

    @Test
    void shouldGenerateCodesForAllSymbols() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        HuffmanTree tree =
                new HuffmanTreeBuilder().build(probabilities);

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder().build(tree);

        assertEquals(4, codebook.codes().size());

        assertTrue(codebook.contains("A"));
        assertTrue(codebook.contains("B"));
        assertTrue(codebook.contains("C"));
        assertTrue(codebook.contains("D"));
    }

    @Test
    void shouldGenerateUniqueCodes() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder()
                        .build(new HuffmanTreeBuilder().build(probabilities));

        long uniqueCount = codebook.codes()
                .values()
                .stream()
                .distinct()
                .count();

        assertEquals(
                codebook.codes().size(),
                uniqueCount
        );
    }

    @Test
    void shouldGeneratePrefixFreeCodes() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder()
                        .build(new HuffmanTreeBuilder().build(probabilities));

        assertTrue(
                isPrefixFree(codebook.codes().values())
        );
    }

    @Test
    void shouldGenerateCodeForSingleSymbol() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 1.0)
        );

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder()
                        .build(new HuffmanTreeBuilder().build(probabilities));

        assertEquals("0", codebook.getCode("A"));
    }

    private boolean isPrefixFree(Collection<String> codes) {

        for (String first : codes) {

            for (String second : codes) {

                if (first.equals(second)) {
                    continue;
                }

                if (second.startsWith(first)) {
                    return false;
                }

            }

        }

        return true;
    }

    @Test
    void moreProbableSymbolShouldNotHaveLongerCode() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        HuffmanCodebook codebook =
                new HuffmanCodebookBuilder()
                        .build(new HuffmanTreeBuilder().build(probabilities));

        assertTrue(
                codebook.getCode("A").length()
                        <= codebook.getCode("D").length()
        );
    }
}