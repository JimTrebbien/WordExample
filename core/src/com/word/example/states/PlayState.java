package com.word.example.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.word.example.enteties.Word;
import com.word.example.helpers.Dictionary;

import java.util.ArrayList;


public class PlayState extends State {

    private ArrayList<Word> words;
    private float wordSpawnDelay = 1; //in seconds
    private float timedelta;
    private OrthographicCamera camera;
    private BitmapFont font;

    private ShapeRenderer shapeRenderer;

    public PlayState(StateManager stateManager){
        super(stateManager);
        words = new ArrayList<>();
        timedelta = 0f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("font.fnt"),  Gdx.files.internal("font.png"), false);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

       //this just checkes if wordSpawnDelay has passed
       timedelta += delta;
        if(timedelta > wordSpawnDelay){
            spawnWord();
            timedelta = 0f;
        }

        updateWords(delta);
    }

    /****
    * We call the update method on each word and at the same time check if it needs to be removed
    **/
    private void updateWords(float delta) {

        for(int i = 0; i < words.size(); i++){
            words.get(i).update(delta);
            if(words.get(i).getBound().y < 0)words.remove(i);
        }
    }


    /****
    * create a bound for the word to save the position and give it a string
     * then add it to the word list
    **/
    private void spawnWord(){
        Rectangle bound = new Rectangle();
        bound.x =  MathUtils.random(0, Gdx.graphics.getWidth() / 2 - 64);
        bound.y = Gdx.graphics.getHeight();
        Word word = new Word(bound, Dictionary.getRandomWord());
        words.add(word);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.begin();
        for(Word word : words){
            font.draw(spriteBatch, word.getWordString(), word.getBound().x, word.getBound().y);
        }
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
