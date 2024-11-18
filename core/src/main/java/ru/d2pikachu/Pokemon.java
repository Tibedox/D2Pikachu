package ru.d2pikachu;

import static ru.d2pikachu.Main.SCR_HEIGHT;
import static ru.d2pikachu.Main.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

abstract public class Pokemon {
    float x, y;
    float width;
    float height;
    float stepX;
    float stepY;
    boolean isTouched;

    public Pokemon(float x, float y){
        this.x = x;
        this.y = y;
        width = height = MathUtils.random(50, 200);
        stepX = MathUtils.random(-3f, 3);
        stepY = MathUtils.random(-3f, 3);
    }

    public void fly(){
        x += stepX;
        y += stepY;
        if(!isTouched) {
            if (x > SCR_WIDTH - width || x < 0) stepX = -stepX;
            if (y > SCR_HEIGHT - height || y < 0) stepY = -stepY;
        }
    }

    public boolean flip(){
        return stepX<0;
    }

    boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y<ty && ty<y+height;
    }

    abstract void disappear();
}
