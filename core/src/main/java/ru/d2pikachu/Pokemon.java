package ru.d2pikachu;

import static ru.d2pikachu.Main.SCR_HEIGHT;
import static ru.d2pikachu.Main.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class Pokemon {
    float x, y;
    float width;
    float height;
    private float stepX;
    private float stepY;

    public Pokemon(float x, float y){
        this.x = x;
        this.y = y;
        width = height = MathUtils.random(50, 200);
        stepX = MathUtils.random(-8, 8);
        stepY = MathUtils.random(-8, 8);
    }

    public void fly(){
        x += stepX;
        y += stepY;
        if(x>SCR_WIDTH-width || x<0) stepX = -stepX;
        if(y>SCR_HEIGHT-height || y<0) stepY = -stepY;
    }

    public boolean flip(){
        return stepX<0;
    }
}
