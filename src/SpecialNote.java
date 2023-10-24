import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the Special Notes which have an effect when pressed
 * @author Du-Simon Nguyen
 */
public class SpecialNote extends Note{
    /* attributes */
    /** This constant represents the note of special type in string form
     */
    public static final String SPECIAL = "Special";
    /** This constant represents the activation distance within which the note should be pressed to have an effect
     */
    public static final int ACTIVATION_DIST = 50;
    /** This attribute represents whether the special note has been activated or not
     */
    private boolean isActivated = false;

    /* constructors, setters, getters */
    /** This constructor instantiates a special note
     * @param appearanceFrame This represents the frame at which the note should appear
     * @param pos This sets the position of the note
     * @param noteDir This sets the type of the special note
     */
    public SpecialNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }

    /** This setter sets the activation status of the special note
     * @param activated This boolean parameter indicates the activation status
     */
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    /** This getter returns the activation status of the special note
     * @return boolean This returns the activation status of the special note
     */
    public boolean isActivated() {
        return isActivated;
    }

    /* methods */
    /** This method checks for any special notes that have been activated to apply effect
     * @param lvlNote This represents the notes appearing in a level
     * @param frameNum This represents the current frame
     */
    protected static void checkSpecialNote(ArrayList<Note> lvlNote, int frameNum) {
        for (Note note : lvlNote) {
            if (note instanceof SpecialNote && ((SpecialNote) note).isActivated()) {
                if (note instanceof SpeedUpNote) {
                    ((SpeedUpNote) note).increaseSpeed(lvlNote);
                    ((SpeedUpNote) note).setActivated(false);
                } else if (note instanceof SlowDownNote) {
                    ((SlowDownNote) note).decreaseSpeed(lvlNote);
                    ((SlowDownNote) note).setActivated(false);
                } else if (note instanceof BombNote) {
                    ((BombNote) note).removeLaneNotes(lvlNote, frameNum);
                    ((BombNote) note).setActivated(false);
                }
            }
        }
    }
}
