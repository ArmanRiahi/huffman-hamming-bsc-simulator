package com.project.source;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SourceGeneratorTest {

    @Test
    void generatedSequenceShouldHaveCorrectLength() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", 0.5),
                new SymbolProbability("B", 0.5)
        );

        SourceGenerator generator = new SourceGenerator();

        SourceSequence sequence =
                generator.generate(symbols, 1000);

        assertEquals(1000, sequence.length());

    }

    @Test
    void generatedDistributionShouldMatchProbability() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", 0.7),
                new SymbolProbability("B", 0.3)
        );

        SourceGenerator generator =
                new SourceGenerator();

        SourceSequence sequence =
                generator.generate(symbols, 100000);

        long countA =
                sequence.symbols()
                        .stream()
                        .filter(s -> s.equals("A"))
                        .count();

        double probability =
                (double) countA / sequence.length();

        assertTrue(
                Math.abs(probability - 0.7) < 0.02
        );

    }
}
