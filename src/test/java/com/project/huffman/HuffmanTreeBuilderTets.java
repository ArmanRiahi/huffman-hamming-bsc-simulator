package com.project.huffman;

import com.project.source.SymbolProbability;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeBuilderTest {

    @Test
    void shouldBuildHuffmanTree() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        HuffmanTree tree = new HuffmanTreeBuilder().build(probabilities);

        assertNotNull(tree);
        assertNotNull(tree.root());

        assertEquals(1.0, tree.root().getProbability(), 1e-9);
    }

    @Test
    void shouldThrowExceptionWhenProbabilityListIsNull() {

        HuffmanTreeBuilder builder = new HuffmanTreeBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenProbabilityListIsEmpty() {

        HuffmanTreeBuilder builder = new HuffmanTreeBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.build(List.of())
        );
    }

    @Test
    void shouldBuildSingleNodeTree() {

        List<SymbolProbability> probabilities = List.of(
                new SymbolProbability("A", 1.0)
        );

        HuffmanTree tree = new HuffmanTreeBuilder().build(probabilities);

        assertTrue(tree.root().isLeaf());
        assertEquals("A", tree.root().getSymbol());
        assertEquals(1.0, tree.root().getProbability());
    }
}
