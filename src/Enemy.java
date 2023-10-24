import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;
import java.util.Random;

/** This class represents the Enemy object
 * @author Du-Simon Nguyen
 */
public class Enemy extends GameObject{
    /* attributes */
    /** This constant represents how often an enemy should be created
     */
    public static final int CREATION_FRAME = 600;
    /** This constant represents the speed of the enemy
     */
    public static final int HORIZONTAL_SPD = 1;
    /** This constant represents the left bound to which the enemy can move to
     */
    public static final int LOWER_BOUND_X = 100;
    /** This constant represents the right bound to which the enemy can move to
     */
    public static final int UPPER_BOUND_X = 900;
    /** This constant represents the top bound to which the enemy can move to
     */
    public static final int LOWER_BOUND_Y = 100;
    /** This constant represents the bottom bound to which the enemy can move to
     */
    public static final int UPPER_BOUND_Y = 500;
    /** This constant represents the distance for an enemy to be considered colliding with a note
     */
    public static final int COLLIDING_DIST = 104;
    /** This constant represents moving to the left in negative direction
     */
    public static final int LEFT = -1;
    /** This constant represents moving to the right in positive direction
     */
    public static final int RIGHT = 1;
    /** This constant represents the assigned direction of the enemy
     */
    private int dir;
    /** This constant represents the image of an enemy object
     */
    public static final Image ENEMY = new Image("res/enemy.PNG");
    /** This constant represents whether the enemy has been hit by a projectile
     */
    private boolean beenHit = false;

    /* constructors, setters, getters */
    /** Constructor for enemy
     */
    public Enemy() {
        super(new Point());
        generatePos();
        dir = generateDir();
    }

    /** Getter for beenHit
     * @return boolean Returns true if enemy has been hit by projectile
     */
    public boolean isBeenHit() {
        return beenHit;
    }
    /** Getter for enemy direction
     * @return int Returns positive if moving to the right, negative if left
     */
    public int getDir() {
        return dir;
    }
    /** Setter for direction of enemy
     * @param dir This represents movement direction of enemy
     */
    public void setDir(int dir) {
        this.dir = dir;
    }

    /* methods */
    /** This method generates a random position for enemy to spawn in
     */
    public void generatePos() {
        Random random = new Random();
        int posX = random.nextInt(UPPER_BOUND_X - LOWER_BOUND_X + 1) + LOWER_BOUND_X;
        int posY = random.nextInt(UPPER_BOUND_Y - LOWER_BOUND_Y + 1) + LOWER_BOUND_Y;
        this.setPos(new Point(posX, posY));
    }

    /** This method generates a left or right direction for enemy to move in
     * @return int Returns positive if moving to right, negative if moving to left
     */
    public int generateDir() {
        Random random = new Random();
        boolean dir = random.nextBoolean();
        if (dir) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }
    /** This method switches the direction of the enemy if it reaches the edge
     */
    public void switchDir() {
        if (this.getPos().x == Enemy.LOWER_BOUND_X || this.getPos().x == Enemy.UPPER_BOUND_X) {
            this.setDir(this.getDir() * Enemy.LEFT);
        }
    }

    /** This method checks whether the enemy has collided with a normal note
     * @param lvlNote This is the notes that can appear in a level
     * @param frameNum This is the current frame number
     */
    public void collidedNote(ArrayList<Note> lvlNote, int frameNum) {
        for (Note note: lvlNote) {
            if (note instanceof NormalNote && frameNum >= note.getAppearanceFrame()) {
                double dist = distance(note.getPos());
                if (dist <= COLLIDING_DIST) {
                    note.setCompleted(true);
                }
            }
        }
    }

    /** This method draws all the enemies on the screen
     * @param lvl3Enemies This represents all the enemies that have been generated
     */
    public static void drawEnemy(ArrayList<Enemy> lvl3Enemies) {
        for (Enemy enemy: lvl3Enemies) {
            if (!enemy.isBeenHit()) {
                enemy.draw(enemy.getPos(), Enemy.ENEMY);
                /* update new enemy coord */
                enemy.setPos(new Point(enemy.getPos().x + (Enemy.HORIZONTAL_SPD * enemy.getDir()), enemy.getPos().y));
                /* check if enemy reached screen edge to reverse direction */
                enemy.switchDir();
            }
        }
    }
}
