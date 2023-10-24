import bagel.Image;
import bagel.util.Point;

/** This class represents a Normal Note
 * @author Du-Simon Nguyen
 */
public class NormalNote extends Note{
    /* attributes */
    /** This constant represents a normal note as a string
     */
    public static final String NORMAL = "Normal";
    /** This constant represents the image of a normal down note
     */
    public static final Image NORMAL_NOTE_DOWN = new Image("res/noteDown.PNG");
    /** This constant represents the image of a normal left note
     */
    public static final Image NORMAL_NOTE_LEFT = new Image("res/noteLeft.PNG");
    /** This constant represents the image of a normal right note
     */
    public static final Image NORMAL_NOTE_RIGHT = new Image("res/noteRight.PNG");
    /** This constant represents the image of a normal up note
     */
    public static final Image NORMAL_NOTE_UP = new Image("res/noteUp.PNG");

    /** constructor for normal note
     * @param appearanceFrame This is the frame at which the note appears
     * @param pos This sets the position of the note
     * @param noteDir This sets the direction/type of the note
     */
    public NormalNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }
}
