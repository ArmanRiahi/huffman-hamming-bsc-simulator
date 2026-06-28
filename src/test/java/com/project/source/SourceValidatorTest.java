package com.project.source;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SourceValidatorTest {

    @Test
    void validProbabilitiesShouldPass() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.3),
                new SymbolProbability("C", 0.2),
                new SymbolProbability("D", 0.1)
        );

        assertDoesNotThrow(() ->
                SourceValidator.validate(symbols, 100)
        );
    }

    @Test
    void negativeProbabilityShouldThrowException() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", -0.1),
                new SymbolProbability("B", 1.1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> SourceValidator.validate(symbols, 100)
        );
    }

    @Test
    void invalidProbabilitySumShouldThrowException() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", 0.4),
                new SymbolProbability("B", 0.4)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> SourceValidator.validate(symbols, 100)
        );
    }

    @Test
    void invalidLengthShouldThrowException() {

        List<SymbolProbability> symbols = List.of(
                new SymbolProbability("A", 1.0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> SourceValidator.validate(symbols, 0)
        );
    }
}
