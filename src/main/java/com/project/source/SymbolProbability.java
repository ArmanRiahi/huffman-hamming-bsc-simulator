package com.project.source;

public record SymbolProbability(String symbol, double probability) {
    public SymbolProbability {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blak.");
        }

        if (Double.isNaN(probability)) {
            throw new IllegalArgumentException("Probability cannot be NaN.");
        }
    }
}
