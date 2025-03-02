package com.talvin.huffmancode.controller;

import com.talvin.huffmancode.service.HuffmanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/huffman")
public class HuffmanWebController {
    private final HuffmanService huffmanService;

    public HuffmanWebController(HuffmanService huffmanService) {
        this.huffmanService = huffmanService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/encode")
    public String encode(@RequestParam String text, Model model) {
        String encodedText = huffmanService.encode(text);
        model.addAttribute("originalText", text);
        model.addAttribute("encodedText", encodedText);
        return "index";
    }

    @PostMapping("/decode")
    public String decode(@RequestParam String encodedText, Model model) {
        String decodedText = huffmanService.decode(encodedText);
        model.addAttribute("encodedText", encodedText);
        model.addAttribute("decodedText", decodedText);
        return "index";
    }
}