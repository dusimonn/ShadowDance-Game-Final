import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the projectile shot by the guardian
 */
public class Projectile {
    /* attributes */
    /** This constant represents the speed of the projectile
     */
    public static final int SPEED = 6;
    /** This constant represents the distance at which a collision is valid between projectile and enemy
     */
    public static final int COLLIDING_DIST = 62;
    /** This constant represents the image of the projectile
     */
    public static final Image ARROW = new Image("res/arrow.PNG");
    /** This attribute represents the target enemy the projectile is aimed for
     */
    private final Enemy target;
    /** This attribute represents the drawing options for the projectile due to its rotation
     */
    private final DrawOptions drawOptions = new DrawOptions();
    /** This attribute represents the position of the projectile
     */
    private Point pos = null;
    /** This attribute represents whether the projectile has hit the enemy
     */
    private boolean hasHitEnemy = false;

    /* constructors, setters, getters */
    /** constructor for the projectile
     * @param target This is the enemy the projectile is aimed at
     */
    public Projectile(Enemy target) {
        this.target = target;
    }
    /** getter for hasHitEnemy
     * @return boolean Returns true if the projectile has hit the enemy
     */
    public boolean isHasHitEnemy() {
        return hasHitEnemy;
    }
    /** setter for hasHitEnemy
     * @param hasHitEnemy Sets hasHitEnemy
     */
    public void setHasHitEnemy(boolean hasHitEnemy) {
        this.hasHitEnemy = hasHitEnemy;
    }
    /** getter for pos
     * @return Point returns pos
     */
    public Point getPos() {
        return pos;
    }
    /** setter for pos
     * @param pos Sets the position of the projectile to pos
     */
    public void setPos(Point pos) {
        this.pos = pos;
    }
    /** getter for target
     * @return Enemy Returns the enemy target
     */
    public Enemy getTarget() {
        return target;
    }

    /* methods */
    /** This method checks whether the projectile has hit an enemy object
     * @param lvl3Enemies This is the enemies that are currently on screen
     */
    public void hasShotEnemy(ArrayList<Enemy> lvl3Enemies) {
        int[] hitEnemyIndex = new int[lvl3Enemies.size()];
        int i = 0;
        for (Enemy enemy : lvl3Enemies) {
            double dist = enemy.distance(this.getPos());
            if (dist <= COLLIDING_DIST) {
                hitEnemyIndex[i] = lvl3Enemies.indexOf(enemy);
                i++;
                this.setHasHitEnemy(true);
            }
        }
        /* remove the enemies that have been hit */
        for (int j = 0; j < i; j++) {
            lvl3Enemies.remove(hitEnemyIndex[j]);
        }
    }
    /** This method draws the projectile
     * @param pos This is the position of the projectile
     * @param img This is the image of the projectile
     * @param rotAngle This is the angle at which the projectile is rotated
     */
    /* draws the projectile */
    public void draw(Point pos, Image img, double rotAngle) {
        img.draw(pos.x, pos.y, drawOptions.setRotation(rotAngle));
    }

}
