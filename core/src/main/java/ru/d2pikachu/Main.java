package ru.d2pikachu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Main extends ApplicationAdapter {
    public static final float SCR_WIDTH = 1280, SCR_HEIGHT = 720;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;

    private Texture imgPikachu;
    private Texture imgEevee;
    private Texture imgBackGround;
    private Sound sndPikachu;
    private Sound sndEevee;

    Pikachu[] pikachu = new Pikachu[33];
    Eevee[] eevee = new Eevee[22];
    static float pokeballX = 625, pokeballY = 240;
    private int pokemonCounter;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("groboldof32.fnt"));

        imgPikachu = new Texture("pika1.png");
        imgEevee = new Texture("eevee.png");
        imgBackGround = new Texture("bg.jpg");
        sndPikachu = Gdx.audio.newSound(Gdx.files.internal("pikachu.mp3"));
        sndEevee = Gdx.audio.newSound(Gdx.files.internal("eevee.mp3"));

        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i] = new Pikachu(pokeballX, pokeballY);
        }
        for (int i = 0; i < eevee.length; i++) {
            eevee[i] = new Eevee(pokeballX, pokeballY);
        }
    }

    @Override
    public void render() {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            for (Pikachu p: pikachu) {
                if(p.hit(touch.x, touch.y)){
                    p.disappear();
                    sndPikachu.play();
                    pokemonCounter++;
                }
            }
            for (Eevee e: eevee) {
                if(e.hit(touch.x, touch.y)){
                    e.disappear();
                    sndEevee.play();
                    pokemonCounter++;
                }
            }
        }

        // события
        for (Pikachu p: pikachu) p.fly();
        for (Eevee e: eevee) e.fly();

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for (Pikachu p: pikachu) {
            batch.draw(imgPikachu, p.x, p.y, p.width, p.height, 0, 0, 300, 300, p.flip(), false);
        }
        for (Eevee e: eevee) {
            batch.draw(imgEevee, e.x, e.y, e.width, e.height, 0, 0, 300, 300, e.flip(), false);
        }
        font.draw(batch, "Покемон: "+pokemonCounter, 10, SCR_HEIGHT-10);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgPikachu.dispose();
        imgEevee.dispose();
        imgBackGround.dispose();
        sndPikachu.dispose();
        sndEevee.dispose();
        font.dispose();
    }
}
