package com.talvin.huffmancode.utils;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public char character;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}