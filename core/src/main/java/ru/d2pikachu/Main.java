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
    float x = 0, y = 0;
    float width = 378;
    float height = 63;
    float stepX = 8;
    float stepY = 6;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        x += stepX;
        y += stepY;
        if(x>SCR_WIDTH-width || x<0) stepX = -stepX;
        if(y>SCR_HEIGHT-height || y<0) stepY = -stepY;
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, x, y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
