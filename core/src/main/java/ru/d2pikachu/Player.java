package ru.d2pikachu;

public class Player {
    String name;
    long time;

    public Player() {
    }

    public Player(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public void set(String name, long time) {
        this.name = name;
        this.time = time;
    }
}
