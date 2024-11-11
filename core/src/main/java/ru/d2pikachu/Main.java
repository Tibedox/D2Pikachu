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
    Pikachu pikachu0;
    Pikachu pikachu1;
    Pikachu pikachu2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("pika1.png");
        pikachu0 = new Pikachu(0, 0);
        pikachu1 = new Pikachu(400, 100);
        pikachu2 = new Pikachu(100, 400);
    }

    @Override
    public void render() {
        pikachu0.fly();
        pikachu1.fly();
        pikachu2.fly();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, pikachu0.x, pikachu0.y, pikachu0.width, pikachu0.height);
        batch.draw(image, pikachu1.x, pikachu1.y, pikachu1.width, pikachu1.height);
        batch.draw(image, pikachu2.x, pikachu2.y, pikachu2.width, pikachu2.height);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
