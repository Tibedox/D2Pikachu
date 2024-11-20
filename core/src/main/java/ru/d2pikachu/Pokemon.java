package ru.d2pikachu;

import static ru.d2pikachu.Main.*;

import com.badlogic.gdx.math.MathUtils;

public class Pokemon {
    public float x, y;
    public float width;
    public float height;
    private float reduceWidth;
    private float reduceHeight;
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
        } else {
            width -= reduceWidth;
            height -= reduceHeight;
            if(pokeballX-Math.abs(stepX)<x && x<pokeballX+Math.abs(stepX)){
                stepX = 0;
                stepY = 0;
                reduceWidth = 0;
                reduceHeight = 0;
                width = 0;
                height = 0;
            }
        }
    }

    public boolean flip(){
        return stepX<0;
    }

    boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y<ty && ty<y+height;
    }

    void disappear(){
        stepX = (pokeballX-x)/20;
        stepY = (pokeballY-y)/20;
        reduceWidth = width/20;
        reduceHeight = height/20;
        isTouched = true;
    }
}
