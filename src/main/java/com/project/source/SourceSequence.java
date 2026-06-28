package com.project.source;

import java.util.List;

public record SourceSequence(List<String> symbols) {

    public SourceSequence {
        if (symbols == null) {
            throw new IllegalArgumentException("Sequence cannot be null.");
        }

        symbols = List.copyOf(symbols);
    }

    public int length() {
        return symbols.size();
    }

    @Override
    public String toString() {
        return String.join(" ", symbols);
    }
}
