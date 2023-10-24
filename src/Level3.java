import bagel.Input;
import bagel.Keys;

import java.util.ArrayList;
/** This class represents Level 3
 * @author Du-Simon Nguyen
 */
public class Level3 extends Level{
    /* instance variables */
    /** This represents the guardian object that spawns in level 3
     */
    private final Guardian guardian = new Guardian();
    /** This represents the enemies that spawns in level 3
     */
    private final ArrayList<Enemy> lvl3Enemies = new ArrayList<>();

    /* constructors, getters, setters */
    /** Constructor for level 3
     * @param lvl3Info This is the level 1 string list obtained after reading level1 csv file
     */
    public Level3(ArrayList<String[]> lvl3Info) {
        this.lvlLane = storeLane(lvl3Info);
        this.lvlNote = storeNote(lvl3Info, lvlLane);
    }
    /** default constructor
     */
    public Level3() {
    }
    /** getter for the guardian object
     * @return Guardian Return the guardian object
     */
    public Guardian getGuardian() {
        return guardian;
    }

    /** getter for the list of enemies
     * @return ArrayList<Enemy> Return the list of enemies
     */
    public ArrayList<Enemy> getLvl3Enemies() {
        return lvl3Enemies;
    }

    /* methods */
    /** This method implements level 3
     * @param input This is the user input
     * @param frameNum This is the current frame number
     * @param scoreSystem This is the system used to score each note press
     * @return boolean Return true if the game has ended
     */
    public boolean updateLvl3(Input input, int frameNum, Score scoreSystem) {
        /* common logic */
        updateLvl(input, frameNum, scoreSystem);
        /* apply any special note effects */
        SpecialNote.checkSpecialNote(lvlNote, frameNum);

        /* implement level 3 guardian, enemy, projectile physics */
        /* draw in guardian */
        guardian.draw(Guardian.pos, Guardian.GUARDIAN);
        /* draw in enemy */
        if (frameNum % Enemy.CREATION_FRAME == 0 && frameNum != 0) {
            lvl3Enemies.add(new Enemy());
        }
        Enemy.drawEnemy(lvl3Enemies);
        /* check if guardian fire projectile */
        if (input.wasPressed(Keys.LEFT_SHIFT)) {
            /* find the closest enemy, if there are any */
            if (!lvl3Enemies.isEmpty()) {
                Enemy closestEnemy = guardian.findClosestEnemy(lvl3Enemies);
                guardian.getProjectiles().add(new Projectile(closestEnemy));
            }
        }
        /* fire any projectiles */
        guardian.fireProjectile();
        /* check for enemy-note collisions */
        for (Enemy enemy: lvl3Enemies) {
            enemy.collidedNote(lvlNote, frameNum);
        }
        /* check for enemy-projectile collisions */
        for (Projectile projectile : guardian.getProjectiles()) {
            projectile.hasShotEnemy(lvl3Enemies);
        }
        /* print corresponding msg */
        printMsg(lvlNote, frameNum, scoreSystem);
        /* check if game has ended */
        return checkGameEnd(lvlNote);
    }
}
