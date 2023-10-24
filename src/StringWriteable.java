import bagel.Font;
import bagel.util.Point;

/** This is a public interface for writing strings on the game screen
 * @author Du-Simon Nguyen
 */
public interface StringWriteable {
    /** This method writes a string on the screen
     * @param pos This is the position to write the string
     * @param text This is the text to be written
     * @param font This is the font to write the string in
     */
    void write(Point pos, String text, Font font);
}
