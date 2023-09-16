package com.noktapa.qrgenapp.random_letter;

import java.util.Random;

public class RandomLetterFromWord {

    public static char getRandomLetter() {
        String word = "ManUtd";

        // Create a random number generator
        Random random = new Random();

        // Generate a random index within the range of the word's length
        int randomIndex = random.nextInt(word.length());

        // Get the random letter from the word
        char randomLetter = word.charAt(randomIndex);

        // Print the random letter
        System.out.println("Random letter from the word: " + randomLetter);

        return randomLetter;
    }
}



