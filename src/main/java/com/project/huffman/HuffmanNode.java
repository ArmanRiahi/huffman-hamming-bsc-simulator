package com.project.huffman;

public final class HuffmanNode implements Comparable<HuffmanNode> {

    private final String symbol;
    private final double probability;
    private final HuffmanNode left;
    private final HuffmanNode right;

    public HuffmanNode(String symbol, double probability) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blank.");
        }

        if (Double.isNaN(probability)) {
            throw new IllegalArgumentException("Probability cannot be NaN.");
        }
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Probability must between 0 and 1.");
        }
        this.symbol = symbol;
        this.probability = probability;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Children cannot be null.");
        }

        this.symbol = null;
        this.probability = left.probability + right.probability;
        this.left = left;
        this.right = right;
    }

    public String getSymbol() {
        return symbol;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public double getProbability() {
        return probability;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isInternalNode() {
        return !isLeaf();
    }

    public boolean hasChildren() {
        return left != null && right != null;
    }

    @Override
    public int compareTo(HuffmanNode other) {

        if (other == null) {
            throw new NullPointerException("Cannot compare with null.");
        }

        return Double.compare(this.probability, other.probability);
    }

    @Override
    public String toString() {
        if (isLeaf()) {
            return symbol + "(" + probability + ")";
        }

        return "*(" + probability + ")";
    }
}
