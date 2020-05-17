package com.hcsc.adc.javajokeslib;

import java.util.Random;

public class JavaJokes {

    private final String[] jokes = {
            "How do all the oceans say hello to each other? The Wave!",
            "What did one wall say to the other wall? I’ll meet you at the corner!",
            "What do you call a bear with no teeth? A gummy bear!",
            "Where do cows go for entertainment? To the moo-vies!",
            "What do you call a cow with no legs? Ground beef!",
            "What do you call a pig that knows karate? A pork chop!",
            "How does a frog feel when he has a broken leg?  UnHoppy",
            "What do you get when you cross a snowman with a vampire Frostbite!"
    };

    //Lets get a random joke
    public String getJavaJokes() {
        int index = new Random().nextInt(jokes.length);
        return jokes[index];
    }
}
