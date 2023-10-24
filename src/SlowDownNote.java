import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the slowdown note
 * @author Du-Simon Nguyen
 */
public class SlowDownNote extends SpecialNote{
    /* attributes */
    /** This constant represents the slowdown note as a string
     */
    public static final String SLOW_DOWN = "SlowDown";
    /** This constant represents the speed decrease effect of the slowdown note
     */
    public static final int SPEED_DECREASE = -1;
    /** This constant represents the effect message of the slowdown note
     */
    public static final String EFFECT_MSG = "Slow Down";
    /** This constant represents the activation score of the slowdown note
     */
    public static final int ACTIVATION_SCORE = 15;
    /** This constant represents the image of the slowdown note
     */
    public static final Image NOTE_SLOW_DOWN = new Image("res/noteSlowDown.PNG");

    /* constructors, setters, getters */
    /** This constructor instantiates the slowdown note
     * @param appearanceFrame this sets the appearance frame for the note
     * @param pos this sets the position of the note
     * @param noteDir this sets the type of note
     */
    public SlowDownNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }

    /* methods */

    /** This method decreases the speed of the notes for a level when the slowdown note is activated
     * @param lvlNote This is the notes appearing in a level
     */
    public void decreaseSpeed(ArrayList<Note> lvlNote) {
        for (Note note : lvlNote) {
            note.setSpeed(note.getSpeed() + SPEED_DECREASE);
        }
    }
}
