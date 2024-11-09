package org.chris.rhythmGame.note;

import org.chris.rhythmGame.Main;

import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    protected Image noteBasicImage = new ImageIcon(Main.class.getResource("/images/noteBasic.png")).getImage();
    protected int speed = Main.NOTE_SPEED;
    protected int x, y = 580 - ((1000 / Main.SLEEP_TIME) * speed) * Main.REACH_TIME;
    protected long toPressTime;
    protected String noteType;
    protected boolean proceeded = true;

    public Note(String noteType, long showTime) {
        this.noteType = noteType;
        this.toPressTime = showTime + Main.REACH_TIME * 1000;
        initializeXCoordinate();
    }

    protected void initializeXCoordinate() {
        switch (noteType) {
            case "S" -> x = 228;
            case "D" -> x = 332;
            case "F" -> x = 436;
            case "Space" -> x = 540;
            case "J" -> x = 744;
            case "K" -> x = 848;
            case "L" -> x = 952;
        }
    }

    public boolean isMiss() {
        return System.currentTimeMillis() - toPressTime > 66;
    }

    public String getNoteType() {
        return noteType;
    }

    public boolean isProceeded() {
        return proceeded;
    }

    public void close() {
        proceeded = false;
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(noteBasicImage, x, y, null);
        if (noteType.equals("Space")) {
            g.drawImage(noteBasicImage, x + 100, y, null);
        }
    }

    public void drop() {
        y = (int) (580 - ((1000 / Main.SLEEP_TIME) * speed) * ((toPressTime - System.currentTimeMillis())) / 1000);
    }

    @Override
    public void run() {
        try {
            while (proceeded) {
                drop();
                Thread.sleep(Main.SLEEP_TIME);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String judge() {
        long currTime = System.currentTimeMillis();
        long timingDifference = currTime - toPressTime;
        if (-16 <= timingDifference && timingDifference <= 16) {
            close();
            return "Perfect";
        } else if (-32 <= timingDifference && timingDifference <= 32) {
            close();
            return "Great";
        } else if (-50 <= timingDifference && timingDifference <= 50) {
            close();
            return "Good";
        } else if (-66 <= timingDifference && timingDifference <= 66) {
            close();
            return timingDifference > 0 ? "Late" : "Early";
        }
        return "";
    }
}
