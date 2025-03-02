package com.talvin.huffmancode.service;

import com.talvin.huffmancode.utils.HashMapMapper;
import com.talvin.huffmancode.utils.HuffmanNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Service
public class HuffmanService {
    private final HashMapMapper hashMapMapper;
    private Map<Character, String> huffmanCodes;
    private HuffmanNode root;

    public HuffmanService(HashMapMapper hashMapMapper) {
        this.hashMapMapper = hashMapMapper;
    }

    public void buildTree(String text) {
        Map<Character, Integer> frequencyMap = hashMapMapper.stringToHashMap(text);

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (var entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        root = priorityQueue.poll();
        huffmanCodes = new HashMap<>();
        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.character != '\0') {
            huffmanCodes.put(node.character, code);
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public String encode(String text) {
        buildTree(text);
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decodedText.append(current.character);
                current = root;
            }
        }
        return decodedText.toString();
    }
}