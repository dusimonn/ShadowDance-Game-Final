import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;
/** This class represents the speedup note
 * @author Du-Simon Nguyen
 */
public class SpeedUpNote extends SpecialNote{
    /* attributes */
    /** This constant represents the speedup note as a string
     */
    public static final String SPEED_UP = "SpeedUp";
    /** This constant represents the speed increase effect of the slowdown note
     */
    public static final int SPEED_INCREASE = 1;
    /** This constant represents the effect message of the speedup note
     */
    public static final String EFFECT_MSG = "Speed Up";
    /** This constant represents the activation score of the speedup note
     */
    public static final int ACTIVATION_SCORE = 15;
    /** This constant represents the image of the speedup note
     */
    public static final Image NOTE_SPEED_UP = new Image("res/noteSpeedUp.PNG");

    /* constructors, setters, getters */
    /** This constructor instantiates the speedup note
     * @param appearanceFrame this sets the appearance frame for the note
     * @param pos this sets the position of the note
     * @param noteDir this sets the type of note
     */
    public SpeedUpNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }

    /* methods */

    /** This method increases the speed of the notes for a level when the speedup note is activated
     * @param lvlNote This is the notes appearing in a level
     */
    public void increaseSpeed(ArrayList<Note> lvlNote) {
        for (Note note : lvlNote) {
            note.setSpeed(note.getSpeed() + SPEED_INCREASE);
        }
    }
}
