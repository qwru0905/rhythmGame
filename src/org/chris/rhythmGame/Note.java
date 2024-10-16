package org.chris.rhythmGame;

import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    private Image noteBasicImage = new ImageIcon(Main.class.getResource("/images/noteBasic.png")).getImage();
    private int x, y = 580 - ((1000 / Main.SLEEP_TIME) * Main.NOTE_SPEED) * Main.REACH_TIME;
    private long toPressTime;
    private String noteType;
    private boolean proceeded = true;

    public String getNoteType() {
        return noteType;
    }

    public boolean isProceeded() {
        return proceeded;
    }

    public void close() {
        proceeded = false;
    }

    public Note(String noteType) {
        if (noteType.equals("S")) {
            x = 228;
        } else if (noteType.equals("D")) {
            x = 332;
        } else if (noteType.equals("F")) {
            x = 436;
        } else if (noteType.equals("Space")) {
            x = 540;
        } else if (noteType.equals("J")) {
            x = 744;
        } else if (noteType.equals("K")) {
            x = 848;
        } else if (noteType.equals("L")) {
            x = 952;
        }
        this.noteType = noteType;
        this.toPressTime = System.currentTimeMillis() + Main.REACH_TIME * 1000;
    }

    public void screenDraw(Graphics2D g) {
        if (!noteType.equals("Space")) {
            g.drawImage(noteBasicImage, x, y, null);
        } else {
            g.drawImage(noteBasicImage, x, y, null);
            g.drawImage(noteBasicImage, x + 100, y, null);
        }
    }

    public void drop() {
        y = (int) (580 - ((1000 / Main.SLEEP_TIME) * Main.NOTE_SPEED) * ((toPressTime - System.currentTimeMillis()))/1000);
        if (y > 620) {
            System.out.println("Miss");
            close();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                drop();
                if (proceeded) {
                    Thread.sleep(Main.SLEEP_TIME);
                } else {
                    interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String judge() {
        /*if (y >= 613) {
            System.out.println("Late");
            close();
            return "Late";
        } else if (y >= 600) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (y >= 587) {
            System.out.println("Great");
            close();
            return "Great";
        } else if (y >= 573) {
            System.out.println("Perfect");
            close();
            return "Perfect";
        } else if (y >= 565) {
            System.out.println("Great");
            close();
            return "Great";
        } else if (y >= 550) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (y >= 535) {
            System.out.println("Early");
            close();
            return "Early";
        }
        return null;*/
        long currTime = System.currentTimeMillis();
        long timingDifference = currTime - toPressTime;
        System.out.println(timingDifference);
        System.out.println(y);
        if (-16 <= timingDifference && timingDifference <= 16) {
            System.out.println("Perfect");
            close();
            return "Perfect";
        } else if (-32 <= timingDifference && timingDifference <= 32) {
            System.out.println("Great");
            close();
            return "Great";
        } else if (-50 <= timingDifference && timingDifference <= 50) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (-66 <= timingDifference && timingDifference <= 66) {
            if (timingDifference > 0) {
                System.out.println("Late");
                close();
                return "Late";
            } else {
                System.out.println("Early");
                close();
                return "Early";
            }
        }
        return "";
    }

    public int getY() {
        return y;
    }
}
