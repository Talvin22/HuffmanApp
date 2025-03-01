package com.talvin.huffmancode.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HashMapMapper {

    public Map<Character, Integer> stringToHashMap(String message) {
        Map<Character, Integer> charHashMap = new HashMap<>();

        for (char letter : message.toCharArray()) {
            charHashMap.put(letter, charHashMap.getOrDefault(letter, 0) + 1);
        }

        return charHashMap;
    }
}