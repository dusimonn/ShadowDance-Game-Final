import bagel.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2023
 * Please enter your name below
 * @author
 * Du-Simon Nguyen, 1352062
 */

public class ShadowDance extends AbstractGame  {
    /* constants */
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DANCE";
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");
    private final static int LVL_1 = 1;
    private final static int LVL_2 = 2;
    private final static int LVL_3 = 3;
    public final static int TARGET_SCORE = 150;

    /* instance variables */
    private boolean gameStart = true;
    private boolean gameEnd = false;
    private int currLev;
    private int frameNum = 0;

    /* Associations */
    private final StartScreen startScreen = new StartScreen();
    private final EndScreen endScreen = new EndScreen();
    private final Score scoreSystem = new Score(0);
    private final ArrayList<String[]> lvl1Info = new ArrayList<>();
    private final ArrayList<String[]> lvl2Info = new ArrayList<>();
    private final ArrayList<String[]> lvl3Info = new ArrayList<>();
    private Level1 level1 = new Level1();
    private Level2 level2 = new Level2();
    private Level3 level3 = new Level3();

    /** This constructor initialises the ShadowDance object
     * @param lvl1CSV this represents the file for lvl 1
     * @param lvl2CSV this represents the file for lvl 2
     * @param lvl3CSV this represents the file for lvl 3
     */

    public ShadowDance(String lvl1CSV, String lvl2CSV, String lvl3CSV){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        /* read in csv level files */
        readCSV(lvl1CSV, lvl2CSV, lvl3CSV);
        /* set the lanes and notes for each level */
        level1 = new Level1(lvl1Info);
        level2 = new Level2(lvl2Info);
        level3 = new Level3(lvl3Info);
    }
    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */
    private void readCSV(String lvl1CSV, String lvl2CSV, String lvl3CSV) {
        readLvlCSV(lvl1Info, lvl1CSV);
        readLvlCSV(lvl2Info, lvl2CSV);
        readLvlCSV(lvl3Info, lvl3CSV);
    }
    /** This main method is he entry point for the program.
     */
    public static void main(String[] args) {
        String lvl1 = "res/level1.csv";
        String lvl2 = "res/level2.csv";
        String lvl3 = "res/level3.csv";
//        String lvl1 = "res/test1.csv";
//        String lvl2 = "res/test2.csv";
//        String lvl3 = "res/test3.csv";
        ShadowDance game = new ShadowDance(lvl1, lvl2, lvl3);
        game.run();
    }
    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        if (gameStart) {
            /* reset the world files */
            resetWorld();
            startScreen.writeStartScreen();
            int lvlInput = getLevel(input);
            if (lvlInput != 0) {
                currLev = lvlInput;
                gameStart = false;
                frameNum = 0;
            }
        }
        else {
            /* implement the level */
            if (currLev == LVL_1) {
                gameEnd = level1.updateLvl1(input, frameNum, scoreSystem);
            } else if (currLev == LVL_2) {
                gameEnd = level2.updateLvl2(input, frameNum, scoreSystem);
            } else if (currLev == LVL_3) {
                gameEnd = level3.updateLvl3(input, frameNum, scoreSystem);
            }
            frameNum++;
        }
        if (gameEnd) {
            BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
            endScreen.writeEndScreen(scoreSystem);
            /* if user wants to replay game */
            if (input.wasPressed(Keys.SPACE)) {
                gameStart = true;
                gameEnd = false;
                scoreSystem.setTotalScore(0);
                frameNum = 0;
            }
        }

    }
    /* reset the world files so user can replay each level */
    private void resetWorld() {
        /* clear existing note information */
        level1.getLvlNote().clear();
        level2.getLvlNote().clear();
        level3.getLvlNote().clear();
        /* set the lanes and notes for each level */
        level1 = new Level1(lvl1Info);
        level2 = new Level2(lvl2Info);
        level3 = new Level3(lvl3Info);
        /* clear any enemies and projectiles from previous level plays */
        level3.getGuardian().getProjectiles().clear();
        level3.getLvl3Enemies().clear();
    }
    /* get the level input */
    private int getLevel(Input input) {
        if (input.wasPressed(Keys.NUM_1)) {
            return LVL_1;
        } else if (input.wasPressed(Keys.NUM_2)) {
            return LVL_2;
        } else if (input.wasPressed(Keys.NUM_3)) {
            return LVL_3;
        }
        return 0;
    }
    /* read csv file for corresponding level */
    private void readLvlCSV(ArrayList<String[]> lvlInfo, String lvlFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(lvlFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                /* Store each csv field value into the arraylist */
                lvlInfo.add(lineSplit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
