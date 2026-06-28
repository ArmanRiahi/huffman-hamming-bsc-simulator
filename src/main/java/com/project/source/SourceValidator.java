package com.project.source;

import java.util.List;

public final class SourceValidator {
    private static final double EPSILON = 1e-9;

    private SourceValidator() {
    }

    public static void validate(List<SymbolProbability> symbols, int length) {
        validateProbabilities(symbols);
        validateProbabilitySum(symbols);
        validateSourceLength(length);
    }

    public static void validateProbabilities(List<SymbolProbability> symbols) {
        symbols.stream()
                .filter(symbol -> symbol.probability() < 0 || symbol.probability() > 1)
                .findFirst()
                .ifPresent(symbol -> {
                    throw new IllegalArgumentException(
                            "Probability of symbol '" +
                                    symbol.symbol() +
                                    "' must be between 0 and 1"
                    );
                });
    }

    public static void validateProbabilitySum(List<SymbolProbability> symbols) {

        double sum = symbols.stream()
                .mapToDouble(SymbolProbability::probability)
                .sum();

        if (Math.abs(sum - 1.0) > EPSILON) {
            throw new IllegalArgumentException(
                    "Sum of probabilities must equal 1."
            );
        }
    }

    public static void validateSourceLength(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException(
                    "Source length must be positive."
            );
        }
    }

}
