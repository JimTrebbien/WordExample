package com.word.example.helpers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

public class Dictionary {

    /**
     * The word list is private because we never want to edit
     */

    private static ArrayList<String> words;



    public static void init(){

        /**
         * Read the files once and ONLY once!
         */
        ArrayList<String> allWords = readFiles("wordsEn.txt");
        ArrayList<String> swearWords = readFiles("swearWords.txt");

        /**
         * The word list is already sorted so we can do a binary search and remove alle the words from the
         * swearword list. Look up binary search if you don't know what it is. It can save a lot of time in some cases
         */
        for(String wordToRemove : swearWords){
            int indexToRemove = Collections.binarySearch(allWords,wordToRemove);
            if(indexToRemove>-1)allWords.remove(indexToRemove);
        }

        words = allWords;

    }


    /***
     * Returns a random word from the list
     * Method is static so we never have to instantiate the class
     */
    public static String getRandomWord(){
        Random randomGenerator;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(words.size());
        return words.get(index);
    }

    /***
     * Read the file and return an array with all the words
     * some lamda just to be fancy
     */
    private static ArrayList readFiles(String filePath){

        ArrayList<String> tmplist = new ArrayList<String>();

        try {
            try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
                stream.forEach(tmplist::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmplist;

    }



}
