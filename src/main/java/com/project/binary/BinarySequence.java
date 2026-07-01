package com.project.binary;

public record BinarySequence(String bits) {

    public BinarySequence {

        if (bits == null) {
            throw new IllegalArgumentException("Bits cannot be null.");
        }

        if (!bits.matches("[01]*")) {
            throw new IllegalArgumentException(
                    "Binary sequence contains invalid characters."
            );
        }
    }

    public int length() {
        return bits.length();
    }

    @Override
    public String toString() {
        return bits;
    }

}