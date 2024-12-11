package ru.d2pikachu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

public class Main extends ApplicationAdapter {
    public static final float SCR_WIDTH = 1280, SCR_HEIGHT = 720;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font32;
    private BitmapFont font55;
    private Texture imgPikachu;
    private Texture imgEevee;
    private Texture imgBackGround;
    private Sound sndPikachu;
    private Sound sndEevee;

    PokeButton btnRestart;

    Pikachu[] pikachu = new Pikachu[1];
    Eevee[] eevee = new Eevee[1];
    Player[] player = new Player[6];
    static float pokeballX = 625, pokeballY = 240;
    private int pokemonCounter;
    private long timeStartGame, timeCurrent;
    private boolean isGameOver;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();

        font32 = new BitmapFont(Gdx.files.internal("fonts/groboldof32.fnt"));
        font55 = new BitmapFont(Gdx.files.internal("fonts/groboldof55.fnt"));
        imgPikachu = new Texture("pika1.png");
        imgEevee = new Texture("eevee.png");
        imgBackGround = new Texture("bg.jpg");
        sndPikachu = Gdx.audio.newSound(Gdx.files.internal("pikachu.mp3"));
        sndEevee = Gdx.audio.newSound(Gdx.files.internal("eevee.mp3"));

        btnRestart = new PokeButton(font32, "RESTART", 550, 150);

        for (int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }
        loadTableOfRecords();
        for (int i = 0; i < pikachu.length; i++) {
            pikachu[i] = new Pikachu(pokeballX, pokeballY, imgPikachu, sndPikachu);
        }
        for (int i = 0; i < eevee.length; i++) {
            eevee[i] = new Eevee(pokeballX, pokeballY, imgEevee, sndEevee);
        }
        timeStartGame = TimeUtils.millis();
    }

    @Override
    public void render() {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            for (Pikachu p: pikachu) {
                if(p.hit(touch.x, touch.y)){
                    p.disappear();
                    pokemonCounter++;
                }
            }
            for (Eevee e: eevee) {
                if(e.hit(touch.x, touch.y)){
                    e.disappear();
                    pokemonCounter++;
                }
            }
            if(pokemonCounter == pikachu.length+eevee.length && !isGameOver){
                gameOver();
            }

            if(isGameOver){
                if (btnRestart.hit(touch.x, touch.y)){
                    gameRestart();
                }
            }
        }

        // события
        for (Pikachu p: pikachu) p.fly();
        for (Eevee e: eevee) e.fly();
        if(!isGameOver) {
            timeCurrent = TimeUtils.millis() - timeStartGame;
        }

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for (Pikachu p: pikachu) {
            batch.draw(p.img, p.x, p.y, p.width, p.height, 0, 0, 300, 300, p.flip(), false);
        }
        for (Eevee e: eevee) {
            batch.draw(e.img, e.x, e.y, e.width, e.height, 0, 0, 300, 300, e.flip(), false);
        }
        font32.draw(batch, "Покемон: "+pokemonCounter, 10, SCR_HEIGHT-10);
        font32.draw(batch, showTime(timeCurrent), SCR_WIDTH-140, SCR_HEIGHT-10);
        if(isGameOver){
            font55.draw(batch, "Game Over", 0, 650, SCR_WIDTH, Align.center, true);
            for (int i = 0; i < player.length-1; i++) {
                font32.draw(batch, player[i].name, 400, 550-i*70);
                font32.draw(batch, showTime(player[i].time), 750, 550-i*70);
            }
            btnRestart.font.draw(batch, btnRestart.text, btnRestart.x, btnRestart.y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgPikachu.dispose();
        imgEevee.dispose();
        imgBackGround.dispose();
        sndPikachu.dispose();
        sndEevee.dispose();
        font32.dispose();
        font55.dispose();
    }

    private String showTime(long time){
        long msec = time%1000;
        long sec = time/1000%60;
        long min = time/1000/60%60;
        //long hour = time/1000/60/60%24;
        return min/10+min%10+":"+sec/10+sec%10+":"+msec/100;
    }

    private void gameOver(){
        isGameOver = true;
        player[player.length-1].name = "Winner";
        player[player.length-1].time = timeCurrent;
        sortTableOfRecords();
        saveTableOfRecords();
    }

    private void gameRestart(){

    }

    void sortTableOfRecords(){
        for(Player p: player) {
            if(p.time == 0){
                p.time = Long.MAX_VALUE;
            }
        }

        for (int j = 0; j < player.length; j++) {
            for (int i = 0; i < player.length-1; i++) {
                if(player[i].time>player[i+1].time){
                    Player p = player[i];
                    player[i] = player[i+1];
                    player[i+1] = p;
                }
            }
        }

        for(Player p: player) {
            if(p.time == Long.MAX_VALUE){
                p.time = 0;
            }
        }
    }

    void saveTableOfRecords(){
        Preferences prefs = Gdx.app.getPreferences("PokemonRecords");
        for (int i = 0; i < player.length; i++) {
            prefs.putString("name"+i, player[i].name);
            prefs.putLong("time"+i, player[i].time);
        }
        prefs.flush();
    }

    void loadTableOfRecords(){
        Preferences prefs = Gdx.app.getPreferences("PokemonRecords");
        for (int i = 0; i < player.length; i++) {
            player[i].name = prefs.getString("name"+i, "Noname");
            player[i].time = prefs.getLong("time"+i, 0);
        }
    }
}
