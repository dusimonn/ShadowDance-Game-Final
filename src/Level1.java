import bagel.Input;
import java.util.ArrayList;

/** This class represents Level 1
 * @author Du-Simon Nguyen
 */
public class Level1 extends Level{
    /** Constructor for level 1
     * @param lvl1Info This is the level 1 string list obtained after reading level1 csv file
     */
    public Level1(ArrayList<String[]> lvl1Info) {
        this.lvlLane = storeLane(lvl1Info);
        this.lvlNote = storeNote(lvl1Info, lvlLane);
    }
    /** default constructor
     */
    public Level1() {
    }

    /* methods */
    /** This method implements level 1
     * @param input This is the user input
     * @param frameNum This is the current frame number
     * @param scoreSystem This is the system used to score each note press
     * @return boolean Return true if the game has ended
     */
    public boolean updateLvl1(Input input, int frameNum, Score scoreSystem) {
        /* common logic */
        updateLvl(input, frameNum, scoreSystem);
        /* print corresponding msg */
        printMsg(lvlNote, frameNum, scoreSystem);
        /* check if game has ended */
        return checkGameEnd(lvlNote);
    }
}
