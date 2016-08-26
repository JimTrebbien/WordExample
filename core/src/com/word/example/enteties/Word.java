package com.word.example.enteties;


import com.badlogic.gdx.math.Rectangle;

public class Word{
    private Rectangle bound;
    private String wordString;

    public Word(Rectangle bound, String wordString){
        this.bound = bound;
        this.wordString = wordString;
    }

    public String getWordString(){
        return wordString;
    }

    public Rectangle getBound(){
        return bound;
    }

    public void update(float delta){
        bound.y -= 200 * delta; // move the words at a constant speed of 200 pixels/units per second
    }
}