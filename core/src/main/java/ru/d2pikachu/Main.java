package ru.d2pikachu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static final float SCR_WIDTH = 1280, SCR_HEIGHT = 720;
    private SpriteBatch batch;
    private Texture imgPikachu;
    private Texture imgBackGround;
    Pikachu[] pikachu = new Pikachu[33];

    @Override
    public void create() {
        batch = new SpriteBatch();
        imgPikachu = new Texture("pika1.png");
        imgBackGround = new Texture("bg.png");
        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i] = new Pikachu(SCR_WIDTH/2, SCR_HEIGHT/3);
        }
    }

    @Override
    public void render() {
        // действия с объектами
        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i].fly();
        }
        // for (Pikachu p: pikachu) p.fly();

        // отрисовка
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for (int i = 0; i < pikachu.length; i++) {
            batch.draw(imgPikachu, pikachu[i].x, pikachu[i].y, pikachu[i].width, pikachu[i].height);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgPikachu.dispose();
        imgBackGround.dispose();
    }
}
