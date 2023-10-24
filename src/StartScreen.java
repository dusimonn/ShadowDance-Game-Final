import bagel.Font;
import bagel.util.Point;

/** This class represents the StartScreen
 * @author Du-Simon Nguyen
 */
public class StartScreen implements StringWriteable{
    /* attributes */
    /** This constant represents the title of the start screen
     */
    public static final String TITLE = "SHADOW DANCE";
    /** This constant represents the position of the title
     */
    public static final Point POSITION = new Point(220, 250);
    /** This constant represents part 1 of the start screen instruction
     */
    public static final String INSTR_1 = "SELECT LEVEL WITH";
    /** This constant represents part 2 of the start screen instruction
     */
    public static final String INSTR_2 = "NUMBER KEYS";
    /** This constant represents part 3 of the start screen instruction
     */
    public static final String INSTR_3 = "1      2      3";
    /** This constant represents the position of part 1 of instruction
     */
    public static final Point INSTR_1_POS = new Point(POSITION.x + 100, POSITION.y + 190);
    /** This constant represents the position of part 2 of instruction
     */
    public static final Point INSTR_2_POS = new Point(INSTR_1_POS.x, INSTR_1_POS.y + 25);
    /** This constant represents the position of part 3 of instruction
     */
    public static final Point INSTR_3_POS = new Point(INSTR_2_POS.x + 50, INSTR_2_POS.y + 50);
    /** This constant represents the font of the start screen title
     */
    public static final Font TITLE_FONT = new Font("res/FSO8BITR.TTF", 64);
    /** This constant represents the font of the start screen instruction
     */
    public static final Font INSTR_FONT = new Font("res/FSO8BITR.TTF", 24);

    /* constructors, setters, getters */
    /** constructor for start screen
     */
    public StartScreen() {
    }

    /* methods */
    /** This method writes the text at position on the screen with specified font
     * @param pos This is the position to write the string
     * @param text This is the text to be written
     * @param font This is the font to write the string in
     */
    @Override
    public void write(Point pos, String text, Font font) {
        font.drawString(text, pos.x, pos.y);
    }

    /** This method writes in the startScreen
     */
    public void writeStartScreen() {
        write(StartScreen.POSITION, StartScreen.TITLE, StartScreen.TITLE_FONT);
        write(StartScreen.INSTR_1_POS, StartScreen.INSTR_1, StartScreen.INSTR_FONT);
        write(StartScreen.INSTR_2_POS, StartScreen.INSTR_2, StartScreen.INSTR_FONT);
        write(StartScreen.INSTR_3_POS, StartScreen.INSTR_3, StartScreen.INSTR_FONT);
    }
}
