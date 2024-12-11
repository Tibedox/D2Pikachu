package ru.d2pikachu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class PokeButton {
    BitmapFont font;
    String text;
    float x, y;
    float width, height;

    public PokeButton(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }

    boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y>ty && ty>y-height;
    }
}
