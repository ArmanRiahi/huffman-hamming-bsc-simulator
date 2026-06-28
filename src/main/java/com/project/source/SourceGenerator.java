package com.project.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SourceGenerator {

    private final Random random = new Random();

    public SourceSequence generate(List<SymbolProbability> symbols, int length) {
        SourceValidator.validate(symbols, length);
        List<String> sequence = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            double randomValue = random.nextDouble();

            double cumulativeProbability = 0;
            for (SymbolProbability currentSymbol : symbols) {
                cumulativeProbability += currentSymbol.probability();

                if (randomValue <= cumulativeProbability) {
                    sequence.add(currentSymbol.symbol());
                    break;
                }
            }
        }
        return new SourceSequence(sequence);
    }
}
