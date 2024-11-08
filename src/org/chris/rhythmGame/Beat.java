package org.chris.rhythmGame;

public class Beat {
    private final int time;
    private int duration;
    private final boolean isLongNote;
    private final String noteName;

    public Beat(int time, String noteName) {
        this.time = time;
        this.noteName = noteName;
        this.isLongNote = false;
    }

    public Beat(int time, String noteName, int duration) {
        this.time = time;
        this.noteName = noteName;
        this.isLongNote = true;
        this.duration = duration;
    }

    public int getTime() {
        return time;
    }

    public String getNoteName() {
        return noteName;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isLongNote() {
        return isLongNote;
    }
}
