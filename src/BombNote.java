import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the Bomb note which is a type of special note
 * @author Du-Simon Nguyen
 */
public class BombNote extends SpecialNote{
    /* attributes */
    /** Constant to represent name of the BombNote
     */
    public static final String BOMB = "Bomb";
    /** Constant to represent Bomb Lane Clear effect
     */
    public static final String EFFECT_MSG = "Lane CLear";
    /** Constant to represent Bomb image
     */
    public static final Image NOTE_BOMB = new Image("res/noteBomb.PNG");

    /* constructors, setters, getters */
    /**
     * Constructor for the BombNote
     * @param appearanceFrame this sets the appearance frame for the note
     * @param pos this sets the position of the note
     * @param noteDir this sets the type of note
     */
    public BombNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }

    /* methods */
    /** This method removes all the notes in the current lane that are on the screen
     * @param lvlNote This represents the notes that appear in a level
     * @param frameNum This represents the current frame
     */
    public void removeLaneNotes(ArrayList<Note> lvlNote, int frameNum) {
        /* in same lane if x coord are equal */
        double thisX = this.getPos().x;
        for (Note note : lvlNote) {
            double noteX = note.getPos().x;
            /* notes that are currently on screen will have appearance frameNum < curr frameNum */
            if (Double.compare(thisX, noteX) == 0 && !note.isCompleted() && note.getAppearanceFrame() <= frameNum) {
                note.setCompleted(true);
            }
        }
    }
}
