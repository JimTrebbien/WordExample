package com.word.example.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class State {

        protected OrthographicCamera camera;
        protected StateManager stateManager;

        protected State(StateManager stateManager) {
            this.stateManager = stateManager;
            camera = new OrthographicCamera();
        }

        protected abstract void handleInput();

        public abstract void update(float delta);
        public abstract void render(SpriteBatch spriteBatch);
        public abstract void dispose();

    }
