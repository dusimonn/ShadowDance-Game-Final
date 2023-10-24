import bagel.Image;
import bagel.util.Point;

/** This class represents a Hold Note which is a type of Note
 * @author Du-Simon Nguyen
 */
public class HoldNote extends Note{
    /* attributes */
    /** This constant represents the Hold Note as a string
     */
    public static final String HOLD = "Hold";
    /** This constant represents Released as a string
     */
    public static final String RELEASED = "Released";
    /** This constant represents the y coord at which the hold note should appear
     */
    public static final int HOLD_START_Y = 24;
    /** This constant represents the width of a hold note
     */
    public static final int HOLD_WIDTH = 82;
    /** This constant represents the image of a hold down note
     */
    public static final Image HOLD_NOTE_DOWN = new Image("res/holdNoteDown.PNG");
    /** This constant represents the image of a hold left note
     */
    public static final Image HOLD_NOTE_LEFT = new Image("res/holdNoteLeft.PNG");
    /** This constant represents the image of a hold right note
     */
    public static final Image HOLD_NOTE_RIGHT = new Image("res/holdNoteRight.PNG");
    /** This constant represents the image of a hold up note
     */
    public static final Image HOLD_NOTE_UP = new Image("res/holdNoteUp.PNG");
    /** This attribute represents whether the hold has started
     */
    private boolean holdStarted = false;

    /* constructors, setters, getters */
    /** Constructor for the HoldNote
     * @param appearanceFrame This is the frame at which the note should appear
     * @param pos This sets the position of the hold note
     * @param noteDir This sets the direction/type of the hold note
     */
    public HoldNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }
    /** Setter for holdStarted
     * @param holdStarted This is true if the hold has started
     */
    public void setHoldStarted(boolean holdStarted) {
        this.holdStarted = holdStarted;
    }
    /** Getter for holdStarted
     * @return boolean Return true if the hold has started
     */
    public boolean isHoldStarted() {
        return holdStarted;
    }
}
