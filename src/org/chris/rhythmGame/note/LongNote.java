package org.chris.rhythmGame.note;

import org.chris.rhythmGame.Main;

import javax.swing.*;
import java.awt.*;

public class LongNote extends Note {

    private Image noteBasicImage = new ImageIcon(Main.class.getResource("/images/noteBasic.png")).getImage();
    private int x, y = 580 - ((1000 / Main.SLEEP_TIME) * Main.NOTE_SPEED) * Main.REACH_TIME, length;
    private long toPressTime;
    private long endPressTime;
    private String noteType;
    private boolean isHeld = false;

    public LongNote(String noteType, long showTime, long duration) {
        super(noteType, showTime);
        switch (noteType) {
            case "S" -> x = 228;
            case "D" -> x = 332;
            case "F" -> x = 436;
            case "Space" -> x = 540;
            case "J" -> x = 744;
            case "K" -> x = 848;
            case "L" -> x = 952;
        }
        this.noteType = noteType;
        this.toPressTime = showTime + Main.REACH_TIME * 1000;
        this.endPressTime = this.toPressTime + duration;
    }

    @Override
    public void screenDraw(Graphics2D g) {
        int noteHeight = noteBasicImage.getHeight(null);
        int totalLength = (int) ((endPressTime - toPressTime) / 1000 * Main.NOTE_SPEED * (1000 / Main.SLEEP_TIME)) - noteHeight;
        System.out.println(noteHeight + ", " + totalLength);

        if (!noteType.equals("Space")) {
            // 일반 노트의 경우
            for (int i = 0; i < totalLength; i += noteHeight) {
                g.drawImage(noteBasicImage, x, y - i, null);
            }
            g.drawImage(noteBasicImage, x, y - totalLength, null);
        } else {
            // Space 노트의 경우 두 개의 롱노트를 그려줌
            for (int i = 0; i < totalLength; i += noteHeight) {
                g.drawImage(noteBasicImage, x, y - i, null);
                g.drawImage(noteBasicImage, x + 100, y - i, null);
            }
            g.drawImage(noteBasicImage, x, y - totalLength, null);
            g.drawImage(noteBasicImage, x + 100, y - totalLength, null);
        }
    }


    @Override
    public void drop() {
        y = (int) (580 - ((1000 / Main.SLEEP_TIME) * Main.NOTE_SPEED) * ((toPressTime - System.currentTimeMillis()))/1000);
    }

    @Override
    public String judge() {
        long currTime = System.currentTimeMillis();
        long timingDifference = currTime - toPressTime;

        // 롱노트 시작 판정
        if (!isHeld) {
            if (-16 <= timingDifference && timingDifference <= 16) {
                System.out.println("Perfect");
                isHeld = true;
                return "Perfect";
            } else if (-32 <= timingDifference && timingDifference <= 32) {
                System.out.println("Great");
                isHeld = true;
                return "Great";
            } else if (-50 <= timingDifference && timingDifference <= 50) {
                System.out.println("Good");
                isHeld = true;
                return "Good";
            } else if (-66 <= timingDifference && timingDifference <= 66) {
                isHeld = true;
                if (timingDifference > 0) {
                    System.out.println("Late");
                    return "Late";
                } else {
                    System.out.println("Early");
                    return "Early";
                }
            }
        }

        // 롱노트 종료 판정
        if (isHeld) {
            long endDifference = currTime - endPressTime;
            if (endDifference <= 16 && endDifference >= -16) {
                System.out.println("Perfect End");
                close();
                return "Perfect";
            } else if (endDifference <= 32 && endDifference >= -32) {
                System.out.println("Great End");
                close();
                return "Great";
            } else if (endDifference <= 50 && endDifference >= -50) {
                System.out.println("Good End");
                close();
                return "Good";
            } else if (endDifference <= 66 && endDifference >= -66) {
                if (endDifference > 0) {
                    System.out.println("Late End");
                    close();
                    return "Late";
                } else {
                    System.out.println("Early End");
                    close();
                    return "Early";
                }
            }
        }

        return "";
    }

    @Override
    public boolean isMiss() {
        if (!isHeld) {
            if (System.currentTimeMillis() - toPressTime > 66) {
                close();
                return true;
            }
        } else {
            if (System.currentTimeMillis() - endPressTime > 66) {
                close();
                return true;
            }
        }
        return false;
    }
}
