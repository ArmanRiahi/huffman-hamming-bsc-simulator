package com.project.huffman;

import com.project.source.SymbolProbability;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanTreeBuilder {

    public HuffmanTree build(List<SymbolProbability> probabilities) {
        if (probabilities == null || probabilities.isEmpty()) {
            throw new IllegalArgumentException("Probability list cannot be null or empty.");
        }

        PriorityQueue<HuffmanNode> priorityQueue = createPriorityQueue(probabilities);
        HuffmanNode root = buildTree(priorityQueue);

        return new HuffmanTree(root);
    }

    private PriorityQueue<HuffmanNode> createPriorityQueue (List<SymbolProbability> probabilities) {
        if (probabilities == null || probabilities.isEmpty()) {
            throw new IllegalArgumentException("Probability list cannot be null or empty.");
        }
        
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        probabilities.stream()
                .map(symbol -> new HuffmanNode(symbol.symbol(), symbol.probability()))
                .forEach(queue::offer);
        return queue;
    }

    private HuffmanNode buildTree(PriorityQueue<HuffmanNode> queue) {
        while (queue.size() > 1) {
            HuffmanNode left = Objects.requireNonNull(queue.poll());
            HuffmanNode right = Objects.requireNonNull(queue.poll());

            HuffmanNode parent = new HuffmanNode(left, right);

            queue.offer(parent);
        }

        return queue.poll();
    }
}
