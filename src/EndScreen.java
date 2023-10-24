import bagel.Font;
import bagel.Window;
import bagel.util.Point;

/** This class represents the EndScreen after a level is cleared
 * @author Du-Simon Nguyen
 */
public class EndScreen implements StringWriteable{
    /* attributes */
    /** This constant represents the end screen title font
     */
    public static final Font END_FONT = new Font("res/FSO8BITR.TTF", 64);
    /** This constant represents the end screen message font
     */
    public static final Font MSG_FONT = new Font("res/FSO8BITR.TTF", 24);
    /** This constant represents when a level is cleared
     */
    public static final String WIN_MSG = "CLEAR!";
    /** This constant represents when a level is failed
     */
    public static final String LOSE_MSG = "TRY AGAIN";
    /** This constant represents the location of the winning end screen title
     */
    public static final Point WIN_END_LOC = new Point((double) Window.getWidth()/2 - END_FONT.getWidth(WIN_MSG)/2, 300);
    /** This constant represents the location of the losing end screen title
     */
    public static final Point LOSE_END_LOC = new Point((double) Window.getWidth()/2 - END_FONT.getWidth(LOSE_MSG)/2, 300);
    /** This string constant represents the message to appear when a level is finished
     */
    public static final String INSTRUCTION = "PRESS SPACE TO RETURN TO LEVEL SELECTION";
    /** This string constant location of the message to appear when a level is finished
     */
    public static final Point INSTRUCTION_LOC = new Point((double) Window.getWidth()/2 - MSG_FONT.getWidth(INSTRUCTION)/2, 500);

    /* constructors, setters, getters */
    /** This is the constructor for the end screen
     */
    public EndScreen() {
    }

    /* methods */
    /** This method writes a text in a font at a position on the screen
     * @param pos This is the position to write the string
     * @param text This is the text to be written
     * @param font This is the font to write the string in
     */
    @Override
    public void write(Point pos, String text, Font font) {
        font.drawString(text, pos.x, pos.y);
    }
    /** This method draws in the entire end screen
     * @param scoreSystem This represents the score obtained after completing the level
     */
    public void writeEndScreen(Score scoreSystem) {
        if (scoreSystem.getTotalScore() >= ShadowDance.TARGET_SCORE) {
            write(EndScreen.WIN_END_LOC, EndScreen.WIN_MSG, EndScreen.END_FONT);
        } else {
            write(EndScreen.LOSE_END_LOC, EndScreen.LOSE_MSG, EndScreen.END_FONT);
        }
        write(EndScreen.INSTRUCTION_LOC, EndScreen.INSTRUCTION, EndScreen.MSG_FONT);
    }
}
