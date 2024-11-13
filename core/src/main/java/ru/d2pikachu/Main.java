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
    private Texture image;
    Pikachu[] pikachu = new Pikachu[3];

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("pika1.png");
        pikachu[0] = new Pikachu(0, 0);
        pikachu[1] = new Pikachu(400, 100);
        pikachu[2] = new Pikachu(100, 400);
    }

    @Override
    public void render() {
        // действия с объектами
        for (int i = 0; i < 3; i++) {
            pikachu[i].fly();
        }

        // отрисовка
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        for (int i = 0; i < 3; i++) {
            batch.draw(image, pikachu[i].x, pikachu[i].y, pikachu[i].width, pikachu[i].height);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
