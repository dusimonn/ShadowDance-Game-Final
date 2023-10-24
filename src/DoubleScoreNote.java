import bagel.Image;
import bagel.util.Point;
/** This class represents the double score note which is a type of special note
 * @author Du-Simon Nguyen
 */
public class DoubleScoreNote extends SpecialNote{
    /* attributes */
    /** This constant represents the double score note as a string
     */
    public static final String DOUBLE_SCORE = "DoubleScore";
    /** This constant represents how long the double score effect should last
     */
    public static final int FRAME_EFFECT = 480;
    /** This constant represents the effect msg when the note is pressed
     */
    public static final String EFFECT_MSG = "Double Score";
    /** This constant represents the image of the double score note
     */
    public static final Image NOTE_2X = new Image("res/note2x.PNG");
    /** This constant represents the score multiplier of the double score note
     */
    public static final int DOUBLE_MULTIPLIER = 2;
    /** This attribute represents the start frame which score doubling should being
     */
    private static int startDouble;
    /** This attribute represents the end frame which score doubling should end
     */
    private static int endDouble;
    /** This attribute represents the number of times we should double the score
     */
    private static int doubleInstances = 0;

    /* constructors, setters, getters */
    /** This constructor instantiates the double score note
     * @param appearanceFrame this sets the appearance frame for the note
     * @param pos this sets the position of the note
     * @param noteDir this sets the type of note
     */
    public DoubleScoreNote(int appearanceFrame, Point pos, String noteDir) {
        super(appearanceFrame, pos, noteDir);
    }

    /** setter for startDouble
     * @param startDouble The value to set startDouble to
     */
    public static void setStartDouble(int startDouble) {
        DoubleScoreNote.startDouble = startDouble;
    }

    /** setter for endDouble
     * @param endDouble The value to set endDouble to
     */
    public static void setEndDouble(int endDouble) {
        DoubleScoreNote.endDouble = endDouble;
    }

    /** getter for doubleInstances
     * @return int Return doubleInstances
     */
    public static int getDoubleInstances() {
        return doubleInstances;
    }

    /** setter for doubleInstances
     * @param doubleInstances The value to set doubleInstances to
     */
    public static void setDoubleInstances(int doubleInstances) {
        DoubleScoreNote.doubleInstances = doubleInstances;
    }

    /* methods */

    /** This method checks if double score is in effect and returns corresponding number of points
     * @param frameNum This is the current frame of the game
     * @param points This is the current number of points assigned for a note
     * @return int Returns the adjusted score if double effect in place
     */
    public static int checkDoublePoints(int frameNum, int points) {
        /* only double if within frame effect */
        if (frameNum >= DoubleScoreNote.startDouble && frameNum <= DoubleScoreNote.endDouble) {
            points = points * DoubleScoreNote.DOUBLE_MULTIPLIER * getDoubleInstances();
        }
        /* reset number of times we double after frame effect over, this is for stacking double scores */
        if (frameNum >= DoubleScoreNote.endDouble) {
            doubleInstances = 0;
        }
        return points;
    }
}
