package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

@Service
public class GenerateShortLink {
    private String characters = "abcdefghijklmnopqrstuvwxyz";

    public String Generate() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");

//        return generatedString;
    }
}
