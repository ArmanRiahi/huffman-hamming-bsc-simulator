package com.project.source;

import java.util.ArrayList;
import java.util.List;

public class SourceExtender {

    public ExtendedSourceSequence extend(SourceSequence sequence, int extensionOrder){
        if (extensionOrder <= 0) {
            throw new IllegalArgumentException("Extension order must be positive.");
        }
        if (sequence.length() % extensionOrder != 0) {
            throw new IllegalArgumentException("Source length must be divisible by extension order.");
        }

        List<String> symbols = sequence.symbols();
        List<String> extendedSymbols = new ArrayList<>();
        for (int i = 0; i < sequence.length(); i += extensionOrder) {
            StringBuilder builder = new StringBuilder();
            for (int j = i; j < i + extensionOrder; j++) {
                builder.append(symbols.get(j));
            }

            extendedSymbols.add(builder.toString());
        }

        return new ExtendedSourceSequence(extendedSymbols);
    }
}
