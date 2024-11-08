package org.chris.rhythmGame;

import org.chris.rhythmGame.note.LongNote;
import org.chris.rhythmGame.note.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends Thread {

    private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("/images/noteRouteLine.png")).getImage();
    private Image judgementLineImage = new ImageIcon(Main.class.getResource("/images/judgement.png")).getImage();
    private Image gameInfoImage = new ImageIcon(Main.class.getResource("/images/gameInfo.png")).getImage();

    private Image noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
    private Image noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();

    private boolean isPressS = false;
    private boolean isPressD = false;
    private boolean isPressF = false;
    private boolean isPressSpace = false;
    private boolean isPressJ = false;
    private boolean isPressK = false;
    private boolean isPressL = false;

    private String titleName;
    private String difficulty;
    private String musicTitle;
    private Music gameMusic;
    private String judgement = "";
    private long judgementTime = 0;

    ArrayList<Note> noteList = new ArrayList<Note>();

    public Game(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false, 1);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(noteRouteSImage, 228, 40, null);
        g.drawImage(noteRouteDImage, 332, 40, null);
        g.drawImage(noteRouteFImage, 436, 40, null);
        g.drawImage(noteRouteSpace1Image, 540, 40, null);
        g.drawImage(noteRouteSpace2Image, 640, 40, null);
        g.drawImage(noteRouteJImage, 744, 40, null);
        g.drawImage(noteRouteKImage, 848, 40, null);
        g.drawImage(noteRouteLImage, 952, 40, null);
        g.drawImage(noteRouteLineImage, 224, 40, null);
        g.drawImage(noteRouteLineImage, 328, 40, null);
        g.drawImage(noteRouteLineImage, 432, 40, null);
        g.drawImage(noteRouteLineImage, 536, 40, null);
        g.drawImage(noteRouteLineImage, 740, 40, null);
        g.drawImage(noteRouteLineImage, 844, 40, null);
        g.drawImage(noteRouteLineImage, 948, 40, null);
        g.drawImage(noteRouteLineImage, 1052, 40, null);
        g.drawImage(gameInfoImage, 0, 660, null);
        g.drawImage(judgementLineImage, 0, 580, null);
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.isMiss()) {
                judgement = "Miss";
                judgementTime = System.currentTimeMillis();
            }
            if (!note.isProceeded()) {
                noteList.remove(note);
                i--;
            } else {
                note.screenDraw(g);
            }
        }
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(titleName, 20, 702);
        g.drawString(difficulty, 1190, 702);
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString("S", 270, 609);
        g.drawString("D", 374, 609);
        g.drawString("F", 478, 609);
        g.drawString("Space Bar", 580, 609);
        g.drawString("J", 784, 609);
        g.drawString("K", 889, 609);
        g.drawString("L", 993, 609);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("000000", 590, 702);
        long currentTime = System.currentTimeMillis();
        if (currentTime - judgementTime <= 3000) { // 판정이 3초 이내에 발생한 경우에만 텍스트 표시
            g.setFont(new Font("Arial", Font.BOLD, 30));

            // 판정에 따른 색상 설정
            Color textColor = Color.WHITE;
            if (judgement.equals("Miss")) {
                textColor = Color.RED;
            } else if (judgement.equals("Late")) {
                textColor = Color.ORANGE;
            } else if (judgement.equals("Good")) {
                textColor = Color.YELLOW;
            } else if (judgement.equals("Great")) {
                textColor = Color.GREEN;
            } else if (judgement.equals("Perfect")) {
                textColor = Color.CYAN;
            } else if (judgement.equals("Early")) {
                textColor = Color.ORANGE;
            }

            // 텍스트의 너비와 높이 계산
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(judgement);
            int textHeight = fm.getHeight();

            // x 좌표는 중앙, y 좌표는 500으로 설정
            int x = (1280 - textWidth) / 2;
            int y = 500;

            // 텍스트 배경 그리기 (텍스트 뒤에 사각형을 그려 배경 효과)
            g.setColor(new Color(0, 0, 0, 150));  // 반투명 검정색 배경
            g.fillRect(x - 10, y - textHeight + 5, textWidth + 20, textHeight);  // 텍스트 주변에 여백 추가

            // 텍스트 그리기
            g.setColor(textColor);  // 다시 텍스트 색상으로 변경
            g.drawString(judgement, x, y);
        }

    }

    public void pressS() {
        if (!isPressS) {
            judge("S");
            noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressS = true;
        }
    }

    public void releaseS() {
        judge("release S");
        noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressS = false;
    }

    public void pressD() {
        if (!isPressD) {
            judge("D");
            noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressD = true;
        }
    }

    public void releaseD() {
        judge("release D");
        noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressD = false;
    }

    public void pressF() {
        if (!isPressF) {
            judge("F");
            noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressF = true;
        }
    }

    public void releaseF() {
        judge("release F");
        noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressF = false;
    }

    public void pressSpace() {
        if (!isPressSpace) {
            judge("Space");
            noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("spacePress.mp3", false).start();
            System.out.println(gameMusic.getTime());
            isPressSpace = true;
        }
    }

    public void releaseSpace() {
        judge("release Space");
        noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressSpace = false;
    }

    public void pressJ() {
        if (!isPressJ) {
            judge("J");
            noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressJ = true;
        }
    }

    public void releaseJ() {
        judge("Release J");
        noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressJ = false;
    }

    public void pressK() {
        if (!isPressK) {
            judge("K");
            noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressK = true;
        }
    }

    public void releaseK() {
        judge("Release K");
        noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressK = false;
    }

    public void pressL() {
        if (!isPressL) {
            judge("L");
            noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
            new Music("keyPress.mp3", false).start();
            isPressL = true;
        }
    }

    public void releaseL() {
        judge("Release L");
        noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
        isPressL = false;
    }

    @Override
    public void run() {
        dropNotes();
    }

    public void close() {
        gameMusic.close();
        this.interrupt();
    }

    public void dropNotes() {
        Beat[] beats = null;
        if (titleName.equals("Plum - R") && difficulty.equals("Easy")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[] {
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 1500, "D"),
                    new Beat(startTime + 1800, "F"),
                    new Beat(startTime + 2100, "Space"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L")
            };
        } else if (titleName.equals("Plum - R") && difficulty.equals("Hard")) {
            int startTime = 1250 - Main.REACH_TIME * 1000;
            beats = new Beat[]{
                    // 다단 단 단 단/다단 단 단 단/다단 단 단 단/다다단 단 단 * 2
                    new Beat(startTime, "S"),
                    new Beat(startTime + 167, "D"),
                    new Beat(startTime + 500, "L"),
                    new Beat(startTime + 833, "K"),
                    new Beat(startTime + 1166, "J"),

                    new Beat(startTime + 1332, "D"),
                    new Beat(startTime + 1499, "F"),
                    new Beat(startTime + 1832, "L"),
                    new Beat(startTime + 2165, "K"),
                    new Beat(startTime + 2498, "J"),

                    new Beat(startTime + 2664, "F"),
                    new Beat(startTime + 2831, "Space"),
                    new Beat(startTime + 3164, "L"),
                    new Beat(startTime + 3497, "K"),
                    new Beat(startTime + 3830, "J"),

                    new Beat(startTime + 4163, "S"),
                    new Beat(startTime + 4329, "D"),
                    new Beat(startTime + 4496, "F"),
                    new Beat(startTime + 4662, "Space"),
                    new Beat(startTime + 4829, "J"),
                    new Beat(startTime + 4995, "K"),
                    new Beat(startTime + 5162, "L"),

                    new Beat(startTime + 5328, "L"),
                    new Beat(startTime + 5328 + 167, "K"),
                    new Beat(startTime + 5328 + 500, "S"),
                    new Beat(startTime + 5328 + 833, "D"),
                    new Beat(startTime + 5328 + 1166, "F"),

                    new Beat(startTime + 5328 + 1332, "K"),
                    new Beat(startTime + 5328 + 1499, "J"),
                    new Beat(startTime + 5328 + 1832, "S"),
                    new Beat(startTime + 5328 + 2165, "D"),
                    new Beat(startTime + 5328 + 2498, "F"),

                    new Beat(startTime + 5328 + 2664, "J"),
                    new Beat(startTime + 5328 + 2831, "Space"),
                    new Beat(startTime + 5328 + 3164, "S"),
                    new Beat(startTime + 5328 + 3497, "D"),
                    new Beat(startTime + 5328 + 3830, "F"),

                    new Beat(startTime + 5328 + 4163, "L"),
                    new Beat(startTime + 5328 + 4329, "K"),
                    new Beat(startTime + 5328 + 4496, "J"),
                    new Beat(startTime + 5328 + 4662, "Space"),
                    new Beat(startTime + 5328 + 4829, "F"),
                    new Beat(startTime + 5328 + 4995, "D"),
                    new Beat(startTime + 5328 + 5162, "S"),

                    new Beat(startTime + 5328  + 5328 , "S"),
                    new Beat(startTime + 5328  + 5328  + 167, "D"),
                    new Beat(startTime + 5328  + 5328  + 500, "L"),
                    new Beat(startTime + 5328  + 5328  + 833, "K"),
                    new Beat(startTime + 5328  + 5328  + 1166, "J"),

                    new Beat(startTime + 5328  + 5328  + 1332, "D"),
                    new Beat(startTime + 5328  + 5328  + 1499, "F"),
                    new Beat(startTime + 5328  + 5328  + 1832, "L"),
                    new Beat(startTime + 5328  + 5328  + 2165, "K"),
                    new Beat(startTime + 5328  + 5328  + 2498, "J"),

                    new Beat(startTime + 5328  + 5328  + 2664, "F"),
                    new Beat(startTime + 5328  + 5328  + 2831, "Space"),
                    new Beat(startTime + 5328  + 5328  + 3164, "L"),
                    new Beat(startTime + 5328  + 5328  + 3497, "K"),
                    new Beat(startTime + 5328  + 5328  + 3830, "J"),

                    new Beat(startTime + 5328  + 5328  + 4163, "S"),
                    new Beat(startTime + 5328  + 5328  + 4329, "D"),
                    new Beat(startTime + 5328  + 5328  + 4496, "F"),
                    new Beat(startTime + 5328  + 5328  + 4662, "Space"),
                    new Beat(startTime + 5328  + 5328  + 4829, "J"),
                    new Beat(startTime + 5328  + 5328  + 4995, "K"),
                    new Beat(startTime + 5328  + 5328  + 5162, "L"),


                    new Beat(startTime + 5328  + 5328  + 5328 , "L"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 167, "K"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 500, "S"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 833, "D"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 1166, "F"),

                    new Beat(startTime + 5328 + 5328  + 5328  + 1332, "K"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 1499, "J"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 1832, "S"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 2165, "D"),
                    new Beat(startTime + 5328 + 5328  + 5328  + 2498, "F"),

                    new Beat(startTime + 5328 + 2664, "J"),
                    new Beat(startTime + 5328 + 2831, "Space"),
                    new Beat(startTime + 5328 + 3164, "S"),
                    new Beat(startTime + 5328 + 3497, "D"),
                    new Beat(startTime + 5328 + 3830, "F"),

                    new Beat(startTime + 5328 + 4163, "L"),
                    new Beat(startTime + 5328 + 4329, "K"),
                    new Beat(startTime + 5328 + 4496, "J"),
                    new Beat(startTime + 5328 + 4662, "Space"),
                    new Beat(startTime + 5328 + 4829, "F"),
                    new Beat(startTime + 5328 + 4995, "D"),
                    new Beat(startTime + 5328 + 5162, "S"),
            };
        } else if (titleName.equals("Camellia - Parallel Universe Shifter") && difficulty.equals("Easy")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[]{
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 1500, "D"),
                    new Beat(startTime + 1800, "F"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L")
            };
        } else if (titleName.equals("Camellia - Parallel Universe Shifter") && difficulty.equals("Hard")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[] {
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 1500, "D"),
                    new Beat(startTime + 1800, "F"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L")
            };
        } else if (titleName.equals("Raimukun - Icyxis") && difficulty.equals("Easy")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[] {
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 2100, "Space"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L")
            };
        } else if (titleName.equals("Raimukun - Icyxis") && difficulty.equals("Hard")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[] {
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 2100, "Space"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L")
            };
        } else if (titleName.equals("TEST")) {
            int startTime = 1000 - Main.REACH_TIME * 1000;
            beats = new Beat[] {
                    new Beat(startTime + 1200, "S"),
                    new Beat(startTime + 2100, "Space"),
                    new Beat(startTime + 2400, "J"),
                    new Beat(startTime + 2700, "K"),
                    new Beat(startTime + 3000, "L"),
                    new Beat(startTime + 5000, "L", 1000),
                    new Beat(startTime + 7000, "Space", 1000)
            };
        }
        int i = 0;
        gameMusic.start();
        long startTime = System.currentTimeMillis();
        while (i < beats.length && !isInterrupted()) {
            boolean dropped = false;
            int time = gameMusic.isMusicExists()?gameMusic.getTime(): (int) (System.currentTimeMillis() - startTime);
            if (beats[i].getTime() <= time) {
                Note note;
                if (beats[i].isLongNote()) {
                    note = new LongNote(beats[i].getNoteName(), startTime + time, beats[i].getDuration());
                } else {
                    note = new Note(beats[i].getNoteName(), startTime + time);
                }
                note.start();
                noteList.add(note);
                i++;
                dropped = true;
            }
            if (!dropped) {
                try {
                    Thread.sleep(Main.SLEEP_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void judge(String input) {
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note instanceof LongNote longNote && input.contains("release ")) {
                String realInput = input.substring(8);
                System.out.println(realInput);
                if (realInput.equals(longNote.getNoteType())) {
                    judgement(longNote.judge());
                    break;
                }
            }

            if (input.equals(note.getNoteType())) {
                judgement(note.judge());
                break;
            }
        }
    }

    public void judgement(String judge) {
        if (judge.isEmpty()) {
            return;
        }
        judgement = judge;       // 판정을 저장
        judgementTime = System.currentTimeMillis();  // 판정 발생 시간을 기록
    }
}
