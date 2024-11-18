package ru.d2pikachu;

public class Pikachu extends Pokemon{
    public Pikachu(float x, float y){
        super(x, y);
    }

    @Override
    void disappear() {
        stepX = 0;
        stepY = -10;
        isTouched = true;
    }
}
