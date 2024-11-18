package ru.d2pikachu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static final float SCR_WIDTH = 1280, SCR_HEIGHT = 720;

    private SpriteBatch batch;

    private Texture imgPikachu;
    private Texture imgEevee;
    private Texture imgBackGround;

    Pikachu[] pikachu = new Pikachu[33];
    Eevee[] eevee = new Eevee[22];

    @Override
    public void create() {
        batch = new SpriteBatch();

        imgPikachu = new Texture("pika1.png");
        imgEevee = new Texture("eevee.png");
        imgBackGround = new Texture("bg.png");

        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i] = new Pikachu(SCR_WIDTH/2, SCR_HEIGHT/3);
        }
        for (int i = 0; i < eevee.length; i++) {
            eevee[i] = new Eevee(500, 500);
        }
    }

    @Override
    public void render() {
        // касания
        if(Gdx.input.justTouched()){
            float tx = Gdx.input.getX();
            float ty = SCR_HEIGHT-Gdx.input.getY();

            for (int i = 0; i < pikachu.length; i++) {
                if(pikachu[i].hit(tx, ty)){
                    pikachu[i].disappear();
                }
            }
            for (int i = 0; i < eevee.length; i++) {
                if(eevee[i].hit(tx, ty)){
                    eevee[i].disappear();
                }
            }
        }

        // события
        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i].fly();
        }
        for (int i = 0; i < eevee.length; i++) {
            eevee[i].fly();
        }
        // for (Pikachu p: pikachu) p.fly();

        // отрисовка
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for (int i = 0; i < pikachu.length; i++) {
            batch.draw(imgPikachu, pikachu[i].x, pikachu[i].y, pikachu[i].width, pikachu[i].height,
                0, 0, 300, 300, pikachu[i].flip(), false);
        }
        for (int i = 0; i < eevee.length; i++) {
            batch.draw(imgEevee, eevee[i].x, eevee[i].y, eevee[i].width, eevee[i].height,
                0, 0, 300, 300, eevee[i].flip(), false);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgPikachu.dispose();
        imgEevee.dispose();
        imgBackGround.dispose();
    }
}
