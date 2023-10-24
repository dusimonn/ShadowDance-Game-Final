import bagel.Window;
import bagel.util.Point;
import java.util.ArrayList;

/** This public class represents the Note game object
 * @author Du-Simon Nguyen
 */

public class Note extends GameObject{
    /* attributes */
    /** This constant represents the y coordinate of where the note first appears
     */
    public static final int START_Y = 100;
    /** This constant represents the max dist to press the note to get a perfect score
     */
    public static final int PERFECT_DIST = 15;
    /** This constant represents the max dist to press the note to get a good score
     */
    public static final int GOOD_DIST = 50;
    /** This constant represents the max dist to press the note to get a bad score
     */
    public static final int BAD_DIST = 100;
    /** This constant represents the max dist to press the note to get a miss score
     */
    public static final int MISS_DIST = 200;
    /** This constant represents the note type with right arrow direction
     */
    public static final String RIGHT = "Right";
    /** This constant represents the note type with left arrow direction
     */
    public static final String LEFT = "Left";
    /** This constant represents the note type with up arrow direction
     */
    public static final String UP = "Up";
    /** This constant represents the note type with down arrow direction
     */
    public static final String DOWN = "Down";
    /** This constant represents the special note types
     */
    public static final String SPECIAL = "Special";
    /** This variable represents the frame at which the note first appears
     */
    public int appearanceFrame;
    /** This variable represents the movement speed of each note
     */
    private int speed = 2;
    /** This variable represents whether the note has been completed, i.e. pressed for
     */
    private boolean completed = false;
    /** This variable represents the direction/type of note
     */
    private final String noteDir;
    /** This variable represents the msg frame where the effect msg should begin
     */
    private int startMsgFrame;
    /** This variable represents the msg frame where the effect msg should end
     */
    private int endMsgFrame;
    /** This variable represents the amount of points that should be allocated to the note press
     */
    private int points;
    /** This variable represents the distance at which the note was pressed
     */
    private int minDist;

    /* constructors, setters, getters */

    /** This is the constructor used to initialise a note object
     * @param appearanceFrame This sets the frame at which the note appears
     * @param pos This sets the position of the note on screen
     * @param noteDir This sets the type/direction of the note
     */
    public Note(int appearanceFrame, Point pos, String noteDir) {
        super(pos);
        this.appearanceFrame = appearanceFrame;
        this.noteDir = noteDir;
    }

    /** getter for minDist
     * @return int Returns minDist value
     */
    public int getMinDist() {
        return minDist;
    }

    /** This getter gets the frame beginning the msg effect frame for the note
     * @return int This returns the frame corresponding to the beginning of the msg
     */
    public int getStartMsgFrame() {
        return startMsgFrame;
    }
    /** This getter gets the frame ending the msg effect frame for the note
     * @return int This returns the frame corresponding to the ending of the msg
     */
    public int getEndMsgFrame() {
        return endMsgFrame;
    }
    /** This getter gets the points allocated to the note press
     * @return int This returns the points allocated to the note press
     */
    public int getPoints() {
        return points;
    }
    /** This getter gets the frame which the note first appears
     * @return int This returns the frame which the note first appears
     */
    public int getAppearanceFrame() {
        return appearanceFrame;
    }
    /** This getter gets type of note
     * @return String This returns the type of note in String form
     */
    public String getNoteDir() {
        return noteDir;
    }
    /** This getter gets the speed of the note
     * @return int This returns the speed of the note
     */
    public int getSpeed() {
        return speed;
    }

    /** This setter sets the speed of the notes
     * @param speed This is the speed value of the note
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /** This getter checks whether a note has been completed
     * @return boolean This returns true is the note has been completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /** This setter sets the status of the note
     * @param completed This represents whether the note has been completed or not
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /* methods */

    /** This method draws all the notes that should be appearing on the screen at a certain frame
     * @param lvlNote This represents the notes that may appear on the screen
     * @param frameNum This represents the current frame of the screen
     */
    public static void drawNotes(ArrayList<Note> lvlNote, int frameNum) {
        /* only draw notes if note has not yet been pressed for */
        for (Note note : lvlNote) {
            if (frameNum >= note.appearanceFrame && !note.isCompleted()) {
                if (note instanceof NormalNote) {
                    switch (note.getNoteDir()) {
                        case Note.DOWN:
                            note.draw(note.getPos(), NormalNote.NORMAL_NOTE_DOWN);
                            break;
                        case Note.UP:
                            note.draw(note.getPos(), NormalNote.NORMAL_NOTE_UP);
                            break;
                        case Note.LEFT:
                            note.draw(note.getPos(), NormalNote.NORMAL_NOTE_LEFT);
                            break;
                        case Note.RIGHT:
                            note.draw(note.getPos(), NormalNote.NORMAL_NOTE_RIGHT);
                            break;
                    }
                } else if (note instanceof HoldNote) {
                    switch (note.getNoteDir()) {
                        case Note.DOWN:
                            note.draw(note.getPos(), HoldNote.HOLD_NOTE_DOWN);
                            break;
                        case Note.UP:
                            note.draw(note.getPos(), HoldNote.HOLD_NOTE_UP);
                            break;
                        case Note.LEFT:
                            note.draw(note.getPos(), HoldNote.HOLD_NOTE_LEFT);
                            break;
                        case Note.RIGHT:
                            note.draw(note.getPos(), HoldNote.HOLD_NOTE_RIGHT);
                            break;
                    }
                } else if (note instanceof DoubleScoreNote) {
                    note.draw(note.getPos(), DoubleScoreNote.NOTE_2X);
                } else if (note instanceof BombNote) {
                    note.draw(note.getPos(), BombNote.NOTE_BOMB);
                } else if (note instanceof SpeedUpNote) {
                    note.draw(note.getPos(), SpeedUpNote.NOTE_SPEED_UP);
                } else if (note instanceof SlowDownNote) {
                    note.draw(note.getPos(), SlowDownNote.NOTE_SLOW_DOWN);
                }
                /* update new coordinates of note */
                note.setPos(new Point(note.getPos().x, note.getPos().y + note.getSpeed()));
            }

        }
    }

    /** This method updates a notes property after it has been pressed for
     * @param frameNum This represents the current frame at which the note was pressed
     * @param points This represents the number of points allocated to the note press
     */
    public void updatePressedNote(int frameNum, int points) {
        /* Note state is complete, capture frame which msg should print until and corresponding points */
            this.startMsgFrame = frameNum;
            this.endMsgFrame = frameNum + Score.MSG_FRAME;
            this.points = points;
            if (points != 0) {
                /* Pressed note was a normal note */
                if (this instanceof NormalNote && !this.isCompleted()) {
                    this.setCompleted(true);
                }
                /* Pressed note was a hold note */
                else if (this instanceof HoldNote && !this.isCompleted()) {
                    /* first press */
                    if (!((HoldNote) this).isHoldStarted()) {
                        ((HoldNote) this).setHoldStarted(true);
                    }
                    /* release */
                }
                /* Pressed note was a speed note */
                else if ((this instanceof SlowDownNote || this instanceof SpeedUpNote) && !this.isCompleted()) {
                    ((SpecialNote) this).setActivated(true);
                    this.setCompleted(true);
                }
            }
            else {
                /* double score and bomb do not contribute to points */
                if (this instanceof DoubleScoreNote) {
                    /* set the start and end frame for score double */
                    DoubleScoreNote.setStartDouble(frameNum);
                    DoubleScoreNote.setEndDouble(frameNum + DoubleScoreNote.FRAME_EFFECT);
                    DoubleScoreNote.setDoubleInstances(DoubleScoreNote.getDoubleInstances() + 1);
                    ((SpecialNote) this).setActivated(true);
                    this.setCompleted(true);
                }
                else if (this instanceof BombNote) {
                    ((SpecialNote) this).setActivated(true);
                    this.setCompleted(true);
                }
            }
    }

    /** This method checks whether the note has left the screen without being pressed for
     * @param frameNum This represents the frame number which would correspond to the notes msg effect
     * @return boolean This returns true if the note has left the screen without being pressed for at a certain frame
     */
    public boolean leftWindow(int frameNum) {
        if (!this.isCompleted()) {
            if (this instanceof NormalNote && this.getPos().y > Window.getHeight() ) {
                this.setCompleted(true);
                this.startMsgFrame = frameNum;
                this.endMsgFrame = frameNum + Score.MSG_FRAME;
                this.points = Score.MISS_SCORE;
                return true;
            } else if (this instanceof HoldNote && this.getPos().y > Window.getHeight() + HoldNote.HOLD_WIDTH) {
                this.setCompleted(true);
                this.startMsgFrame = frameNum;
                this.endMsgFrame = frameNum + Score.MSG_FRAME;
                this.points = Score.MISS_SCORE;
                return true;
            }
        }
        return false;
    }

    /** This method finds the note the input was for, which will be the closest note
     * @param lvlNote This is the notes that appear in a level
     * @param inputDir This is the input that the user presses as a string
     * @return Note Return the note that was pressed for as a Note object
     */
    public static Note findMinNote(ArrayList<Note> lvlNote, String inputDir) {
        int minIndex = 0;
        int minDist = Window.getHeight();
        int dist = -1;
        for (Note note : lvlNote) {
            /* Only check notes which match the arrow input direction and has not been completed */
            if (note.getNoteDir().equals(inputDir) && !note.isCompleted()) {
                /* note is normal or special */
                if (note instanceof NormalNote || note instanceof SpecialNote) {
                    dist = Math.abs((int)note.getPos().y - Lane.CENTRE_Y);
                }
                /* note is hold */
                else if (note instanceof HoldNote) {
                    /* start of the hold */
                    dist = Math.abs((int)note.getPos().y + HoldNote.HOLD_WIDTH - Lane.CENTRE_Y);
                    /* end of the hold */
                }

            }
            if (dist < minDist && dist != -1) {
                minDist = dist;
                minIndex = lvlNote.indexOf(note);
            }
        }
        Note minNote = lvlNote.get(minIndex);
        minNote.minDist = minDist;
        /* only return notes where input actually valid, i.e. dist <= 200 */
        if (minNote.minDist <= Note.MISS_DIST) {
            return minNote;
        } else {
            return null;
        }
    }

}
