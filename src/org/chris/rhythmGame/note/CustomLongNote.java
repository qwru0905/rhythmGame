package org.chris.rhythmGame.note;

import org.chris.rhythmGame.Main;

import java.awt.*;

public class CustomLongNote extends CustomNote {

    private final long endPressTime;
    private boolean isHeld = false;

    public CustomLongNote(String noteType, long showTime, long duration, int speed) {
        super(noteType, showTime, speed);
        this.endPressTime = this.toPressTime + duration;
    }

    public CustomLongNote(String noteType, long showTime, long duration, int speed, boolean isCorrectlyDrop) {
        super(noteType, showTime, speed, isCorrectlyDrop);
        this.endPressTime = this.toPressTime + duration;
    }

    public CustomLongNote(String noteType, long showTime, long duration, int speed, long toPressTime) {
        super(noteType, showTime, speed, toPressTime);
        this.endPressTime = this.toPressTime + duration;
    }

    public CustomLongNote(String noteType, long showTime, long duration, int speed, boolean isCorrectlyDrop, long toPressTime) {
        super(noteType, showTime, speed, isCorrectlyDrop, toPressTime);
        this.endPressTime = this.toPressTime + duration;
    }

    @Override
    public void screenDraw(Graphics2D g) {
        int noteHeight = noteBasicImage.getHeight(null);
        int totalLength = (int) ((endPressTime - toPressTime) / 1000 * speed * (1000 / Main.SLEEP_TIME)) - noteHeight;

        for (int i = 0; i <= totalLength; i += noteHeight) {
            g.drawImage(noteBasicImage, x, y - i, null);
            if (noteType.equals("Space")) {
                g.drawImage(noteBasicImage, x + 100, y - i, null);
            }
        }
    }

    @Override
    public String judge() {
        long currTime = System.currentTimeMillis();
        long timingDifference = currTime - toPressTime;

        if (!isHeld && isWithinRange(timingDifference)) {
            isHeld = true;
            return getJudgment(timingDifference);
        }

        if (isHeld) {
            long endDifference = currTime - endPressTime;
            if (isWithinRange(endDifference)) {
                close();
                return getJudgment(endDifference);
            }
        }
        return "";
    }

    private boolean isWithinRange(long difference) {
        return Math.abs(difference) <= 66;
    }

    private String getJudgment(long difference) {
        if (Math.abs(difference) <= 16) return "Perfect";
        else if (Math.abs(difference) <= 32) return "Great";
        else if (Math.abs(difference) <= 50) return "Good";
        else return difference > 0 ? "Late" : "Early";
    }

    @Override
    public boolean isMiss() {
        long currTime = System.currentTimeMillis();
        if (!isHeld) {
            if (currTime - toPressTime > 66) {
                close();
                return true;
            }
        } else if (currTime - endPressTime > 66) {
            close();
            return true;
        }
        return false;
    }
}
