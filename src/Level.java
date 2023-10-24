import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents a Level
 * @author Du-Simon Nguyen
 */
public abstract class Level {
    /* instance variables */
    /** This attribute represents the lanes for the level
     */
    protected ArrayList<Lane> lvlLane = new ArrayList<>();
    /** This attribute represents the notes for the level
     */
    protected ArrayList<Note> lvlNote = new ArrayList<>();

    /* constructors, getters, setters */
    /** Getter for lvlNotes
     * @return ArrayList<Note> Returns the lvlNotes
     */
    public ArrayList<Note> getLvlNote() {
        return lvlNote;
    }

    /* methods relating to storing data */
    /** This method stores the lane info from the level csv
     * @param lvlInfo This is the list obtained after reading the level csv file
     * @return ArrayList<Lane> Returns the lanes for the level
     */
    protected ArrayList<Lane> storeLane(ArrayList<String[]> lvlInfo) {
        ArrayList<Lane> lvlLane = new ArrayList<>();
        for (String[] row : lvlInfo) {
            if (row[0].equals(Lane.LANE)) {
                Point lanePos = new Point(Integer.parseInt(row[2]), Lane.LANE_Y);
                String laneType = row[1];
                lvlLane.add(new Lane(lanePos, laneType));
            }
        }
        return lvlLane;
    }

    /** This method stores the note info from the level csv
     * @param lvlInfo This is the list obtained after reading the level csv file
     * @param lvlLane This is the lanes for the leve
     * @return ArrayList<Note> Return the notes for the level
     */
    protected ArrayList<Note> storeNote(ArrayList<String[]> lvlInfo, ArrayList<Lane> lvlLane) {
        ArrayList<Note> lvlNote = new ArrayList<>();
        for (String[] row : lvlInfo) {
            if (!row[0].equals(Lane.LANE)) {
                int noteFrame = Integer.parseInt(row[2]);
                String noteDir = row[0];
                String noteType = row[1];
                Point notePos = getNotePos(noteDir, noteType, lvlLane);
                /* note is normal type */
                if (noteType.equals(NormalNote.NORMAL)) {
                    lvlNote.add(new NormalNote(noteFrame, notePos, noteDir));
                }
                /* note is hold type */
                else if (noteType.equals(HoldNote.HOLD)) {
                    lvlNote.add(new HoldNote(noteFrame, notePos, noteDir));
                }
                /* note is bomb type */
                else if (noteType.equals(BombNote.BOMB)) {
                    lvlNote.add(new BombNote(noteFrame, notePos, noteDir));
                }
                /* note is special type */
                else if (noteDir.equals(SpecialNote.SPECIAL)) {
                    /* determine type of special note */
                    switch (noteType) {
                        case SlowDownNote.SLOW_DOWN:
                            lvlNote.add(new SlowDownNote(noteFrame, notePos, noteDir));
                            break;
                        case SpeedUpNote.SPEED_UP:
                            lvlNote.add(new SpeedUpNote(noteFrame, notePos, noteDir));
                            break;
                        case DoubleScoreNote.DOUBLE_SCORE:
                            lvlNote.add(new DoubleScoreNote(noteFrame, notePos, noteDir));
                            break;
                    }
                }
            }
        }
        return lvlNote;
    }

    /** This method gets the position of a note
     * @param noteDir This represents the direction of note
     * @param noteType This represents the type of note
     * @param lvlLane This represents the lanes for the level
     * @return Point Returns the position of a note
     */
    protected Point getNotePos(String noteDir, String noteType, ArrayList<Lane> lvlLane) {
        int notePosX = 0;
        int notePosY = 0;
        for (Lane lane : lvlLane) {
            if (noteDir.equals(lane.type)) {
                notePosX = (int) lane.getPos().x;
                if (noteType.equals(NormalNote.NORMAL) || noteDir.equals(SpecialNote.SPECIAL)) {
                    notePosY = Note.START_Y;
                } else if (noteType.equals(HoldNote.HOLD)) {
                    notePosY = HoldNote.HOLD_START_Y;
                }
                return new Point(notePosX, notePosY);
            }
        }
        return new Point(notePosX, notePosY);
    }

    /** This method implements the common logic for all levels
     * @param input This is the key input received from user
     * @param frameNum This is the current frame number
     * @param scoreSystem This is the system used to score each note press
     */

    /* methods relating to gameplay */
    protected void updateLvl(Input input, int frameNum, Score scoreSystem) {
        /* write down score */
        scoreSystem.write(Score.TOTAL_POS, Score.SCORE + scoreSystem.getTotalScore(), Score.SCORE_FONT);
        /* draw in the lanes */
        Lane.drawLane(lvlLane);
        /* draw in the notes */
        Note.drawNotes(lvlNote, frameNum);
        /* check for any input */
        String inputDir = receivedInput(input);
        if (inputDir != null) {
            /* check which note the input was for and update that note */
            checkMinNoteUpdate(lvlNote, inputDir, scoreSystem, frameNum);
        }
        else {
            /* no input so check if note has left the screen without being pressed and output "MISS" if so */
            checkLeftScreen(lvlNote, scoreSystem, frameNum);
        }
    }

    /** This method prints the corresponding messages for a level when a note is pressed
     * @param lvlNote This represents the notes in a level
     * @param frameNum This represents the current frame
     * @param scoreSystem This represents the system used to score a note press
     */
    protected void printMsg(ArrayList<Note> lvlNote, int frameNum, Score scoreSystem) {
        /* notes are valid for printing if within frame */
        ArrayList<Note> validNotes = new ArrayList<>();
        for (Note note : lvlNote) {
            if (frameNum <= note.getEndMsgFrame() && frameNum >= note.getStartMsgFrame()
                    && note.getStartMsgFrame() != 0) {
                validNotes.add(note);
            }
        }
        /* do not want messages to overlap so only print the most recent msg */
        int maxEndFrame = 0;
        Note printNote = null;
        for (Note note : validNotes) {
            if (note.getEndMsgFrame() > maxEndFrame) {
                maxEndFrame = note.getEndMsgFrame();
                printNote = note;
            }
        }
        if (printNote != null) {
            scoreSystem.msg(printNote.getPoints(), printNote);
        }
    }
    /** This method checks whether the game has ended if last note has been completed or has left the screen
     * @param lvlNote This is the notes for a level
     * @return This returns true if the game has ended
     */
    protected boolean checkGameEnd(ArrayList<Note> lvlNote) {
        Note lastNote = lvlNote.get(lvlNote.size() - 1);
        /* check if last note has left screen */
        if (lastNote instanceof HoldNote) {
            if (lastNote.getPos().y - HoldNote.HOLD_WIDTH > Window.getHeight()) {
                return true;
            }
        }
        else {
            if (lastNote.getPos().y > Window.getHeight()) {
                return true;
            }
        }
        /* check if last note has been completed */
        return lastNote.isCompleted();
    }
    /* this method checks which key was pressed */
    private String receivedInput(Input input) {
        if (input.wasPressed(Keys.UP)) {
            return Note.UP;
        } else if (input.wasPressed(Keys.DOWN)) {
            return Note.DOWN;
        } else if ((input.wasPressed(Keys.LEFT))) {
            return Note.LEFT;
        } else if (input.wasPressed(Keys.RIGHT)) {
            return Note.RIGHT;
        } else if (input.wasPressed(Keys.SPACE)){
            return Note.SPECIAL;
        } else if (input.wasReleased(Keys.DOWN) || input.wasReleased(Keys.UP)
                || input.wasReleased(Keys.LEFT) || input.wasReleased(Keys.RIGHT)){
            return HoldNote.RELEASED;
        }
        return null;
    }
    /* This method checks which note the input was for by finding the closest note */
    private void checkMinNoteUpdate(ArrayList<Note> lvlNote, String inputDir, Score scoreSystem, int frameNum) {
        /* find which note the input was for */
        Note inputNote = Note.findMinNote(lvlNote, inputDir);
        if (inputNote != null) {
            int minDist = inputNote.getMinDist();
            int points = 0;
            /* adjust score for normal and hold notes */
            if ((inputNote instanceof NormalNote || inputNote instanceof HoldNote)) {
                points = scoreSystem.score(minDist);
            }
            /* adjust score for speed and slow notes */
            else if (inputNote instanceof SpecialNote) {
                points = scoreSystem.scoreSpecial(minDist, inputNote);
            }
            /* update pressed note properties */
            inputNote.updatePressedNote(frameNum, points);
            int currScore = scoreSystem.getTotalScore();
            /* only add score for notes that are not bomb or double */
            if (!(inputNote instanceof BombNote) && !(inputNote instanceof DoubleScoreNote)) {
                /* double the points received if current frame within double effect */
                currScore += DoubleScoreNote.checkDoublePoints(frameNum, points);
            }
            scoreSystem.setTotalScore(currScore);
        }
    }
    /* check if a note has left the screen without being pressed and update properties */
    private void checkLeftScreen(ArrayList<Note> lvlNote, Score scoreSystem, int frameNum) {
        for (Note note : lvlNote) {
            if ((note instanceof HoldNote || note instanceof NormalNote) && note.leftWindow(frameNum)) {
                note.setCompleted(true);
                int currScore = scoreSystem.getTotalScore();
                /* multiply the miss score if within double score effect */
                currScore += DoubleScoreNote.checkDoublePoints(frameNum, Score.MISS_SCORE);
                scoreSystem.setTotalScore(currScore);
            }
        }
    }


}
