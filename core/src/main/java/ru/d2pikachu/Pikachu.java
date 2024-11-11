package ru.d2pikachu;

import static ru.d2pikachu.Main.*;

public class Pikachu {
    float x, y;
    float width = 300;
    float height = 300;
    float stepX = 8;
    float stepY = 6;

    Pikachu(float x, float y){
        this.x = x;
        this.y = y;
    }

    void fly(){
        x += stepX;
        y += stepY;
        if(x>SCR_WIDTH-width || x<0) stepX = -stepX;
        if(y>SCR_HEIGHT-height || y<0) stepY = -stepY;
    }
}
