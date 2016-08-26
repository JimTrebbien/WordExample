package com.word.example.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class StateManager {
    private Stack<State> states;

    public StateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }

    public void dispose(){
        for(State state : states){
            state.dispose();
        }
    }
}
