package com.project.huffman;

import java.util.Map;

public record HuffmanCodebook(Map<String, String> codes) {
    public HuffmanCodebook {
        if (codes == null) {
            throw new IllegalArgumentException("Codebook cannot be null.");
        }

        codes = Map.copyOf(codes);
    }

    public String getCode(String symbol) {
        String code = codes.get(symbol);

        if (code == null) {
            throw new IllegalArgumentException("Unknown symbol: " + symbol);
        }

        return code;
    }

    public boolean contains(String symbol) {
        return codes.containsKey(symbol);
    }
}
