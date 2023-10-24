import bagel.Input;

import java.util.ArrayList;
/** This class represents Level 2
 * @author Du-Simon Nguyen
 */
public class Level2 extends Level{
    /** Constructor for level 2
     * @param lvl2Info This is the level 2 string list obtained after reading level2 csv file
     */
    public Level2(ArrayList<String[]> lvl2Info) {
        this.lvlLane = storeLane(lvl2Info);
        this.lvlNote = storeNote(lvl2Info, lvlLane);
    }
    /** default constructor
     */
    public Level2() {
    }

    /* methods */
    /** This method implements level 2
     * @param input This is the user input
     * @param frameNum This is the current frame number
     * @param scoreSystem This is the system used to score each note press
     * @return boolean Return true if the game has ended
     */
    public boolean updateLvl2(Input input, int frameNum, Score scoreSystem) {
        /* common logic */
        updateLvl(input, frameNum, scoreSystem);
        /* apply any special note effects */
        SpecialNote.checkSpecialNote(lvlNote, frameNum);
        /* print corresponding msg */
        printMsg(lvlNote, frameNum, scoreSystem);
        /* check if game has ended */
        return checkGameEnd(lvlNote);
    }
}
