package org.chris.rhythmGame.note;

import org.chris.rhythmGame.Main;

public class CustomNote extends Note {
    protected boolean isCorrectlyDrop;
    public CustomNote(String noteType, long showTime, int speed) {
        super(noteType, showTime);
        this.speed = speed;
        this.isCorrectlyDrop = true;
    }

    public CustomNote(String noteType, long showTime, int speed, boolean isCorrectlyDrop) {
        this(noteType, showTime, speed);
        this.isCorrectlyDrop = isCorrectlyDrop;
    }

    public CustomNote(String noteType, long showTime, int speed, long toPressTime) {
        this(noteType, showTime, speed);
        this.toPressTime = toPressTime;
        this.isCorrectlyDrop = true;
    }

    public CustomNote(String noteType, long showTime, int speed, boolean isCorrectlyDrop, long toPressTime) {
        this(noteType, showTime, speed, isCorrectlyDrop);
        this.toPressTime = toPressTime;
    }

    @Override
    public void drop() {
        if (isCorrectlyDrop) {
            y = (int) (580 - ((1000 / Main.SLEEP_TIME) * speed) * ((toPressTime - System.currentTimeMillis())) / 1000);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
