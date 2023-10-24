import bagel.Image;
import bagel.util.Point;

/** This abstract class represents a GameObject
 * @author Du-Simon Nguyen
 */
public abstract class GameObject {
    /* attributes */
    /** This attribute represents the position of the game object
     */
    protected Point pos;

    /* constructors, setters, getters */
    /** This constructor initialises the game object with its position
     * @param pos This is the first parameter of the constructor and is the position of the game object
     */
    public GameObject(Point pos) {
        this.pos = pos;
    }
    /** This getter is for the position of the game object
     * @return Point This returns the Point position of the object
     */
    public Point getPos() {
        return pos;
    }
    /** This setter sets the position of the game object
     * @param pos This is the first parameter of the setter, the position
     */
    public void setPos(Point pos) {
        this.pos = pos;
    }

    /* methods */
    /** This method finds the distance between 2 game objects
     * @param p This is the position of the 2nd object
     * @return double This returns the distance between the 2 objects
     */
    protected double distance(Point p) {
        return Math.sqrt((pos.x - p.x) * (pos.x - p.x) + (pos.y - p.y) * (pos.y - p.y));
    }
    /** This method draws the game object
     * @param pos This is the position of the object to be drawn
     * @param img This is the image of the object to be drawn
     */
    public void draw(Point pos, Image img) {
        img.draw(pos.x, pos.y);
    }
}
