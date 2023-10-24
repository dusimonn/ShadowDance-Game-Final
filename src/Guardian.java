import bagel.Image;
import bagel.Window;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the guardian object
 * @author Du-Simon Nguyen
 */
public class Guardian extends GameObject {
    /* attributes */
    /** This constant represents the position of the guardian
     */
    public static final Point pos = new Point(800, 600);
    /** This constant represents the image of the guardian
     */
    public static final Image GUARDIAN = new Image("res/guardian.PNG");
    /** This constant represents the projectiles the guardian shoots
     */
    private final ArrayList<Projectile> projectiles = new ArrayList<>();

    /* constructors, setters, getters */
    /** Constructor for the guardian
     */
    public Guardian() {
        super(pos);
    }

    /** Getter for the guardian's projectiles
     * @return ArrayList<Projectile></Projectile> Returns the guardian's projectiles
     */
    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    /* methods */

    /** This method finds the closest enemy to fire the projectile at
     * @param enemies This is the list of enemies on the screen
     * @return Enemy Returns the closest enemy to the guardian
     */
    public Enemy findClosestEnemy(ArrayList<Enemy> enemies) {
        Enemy closestEnemy = new Enemy();
        double minDist = Integer.MAX_VALUE;
        for (Enemy enemy : enemies) {
            double dist = this.distance(enemy.getPos());
            if (dist < minDist) {
                minDist = dist;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }
    /** This method fires the projectile at the closest enemy
     */
    public void fireProjectile() {
        int[] collidedProjectiles = new int[getProjectiles().size()];
        int i = 0;
        for (Projectile projectile : projectiles) {
            if (projectile.getPos() == null) {
                projectile.setPos(pos);
            }
            Enemy target = projectile.getTarget();
            /* find the rotation angle */
            double y = pos.y - target.getPos().y;
            double x = pos.x - target.getPos().x;
            double rotAngle = Math.atan((y/x)) + Math.PI;
            if (rotAngle < Math.PI) {
                rotAngle += Math.PI;
            }
            /* draw the projectile only if position in screen and it has not collided with enemy */
            if (!projectile.isHasHitEnemy() && projectile.getPos().x >= 0 && projectile.getPos().x <=
                Window.getWidth() && projectile.getPos().y >= 0 && projectile.getPos().y <= Window.getHeight()) {
                projectile.draw(projectile.getPos(), Projectile.ARROW, rotAngle);
                /* update the projectile position */
                double changeX = Projectile.SPEED * Math.cos(rotAngle);
                double changeY = Projectile.SPEED * Math.sin(rotAngle);
                Point newPos = new Point(projectile.getPos().x + changeX, projectile.getPos().y + changeY);
                projectile.setPos(newPos);
            }
            else if (projectile.isHasHitEnemy()) {
                collidedProjectiles[i] = projectiles.indexOf(projectile);
                i++;
            }
        }
        /* remove projectiles that have hit enemies */
        for (int j = 0; j < i; j++) {
            projectiles.remove(collidedProjectiles[j]);
        }
    }
}
