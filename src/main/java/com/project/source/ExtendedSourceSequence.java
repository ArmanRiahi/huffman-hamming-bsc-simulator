package com.project.source;

import java.util.List;

public record ExtendedSourceSequence(List<String> symbols) {

    public ExtendedSourceSequence {
        if (symbols == null) {
            throw new IllegalArgumentException("Extended source sequence cannot be null.");
        }

        symbols = List.copyOf(symbols);
    }

    public int size() {
        return symbols.size();
    }

    @Override
    public String toString() {
       return String.join(" ", symbols);
    }
}
