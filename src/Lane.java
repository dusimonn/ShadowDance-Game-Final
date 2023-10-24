import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;

/** This class represents the Lanes
 * @author Du-Simon Nguyen
 */
public class Lane extends GameObject{
    /* attributes */
    /** This constant is the string representation of Lane
     */
    public static final String LANE = "Lane";
    /** This constant is the y coord of the lane
     */
    public static final int LANE_Y = 384;
    /** This constant corresponds to the up lane string
     */
    public static final String UP = "Up";
    /** This constant corresponds to the down lane string
     */
    public static final String DOWN = "Down";
    /** This constant corresponds to the right lane string
     */
    public static final String RIGHT = "Right";
    /** This constant corresponds to the left lane string
     */
    public static final String LEFT = "Left";
    /** This constant corresponds to the special lane string
     */
    public static final String SPECIAL = "Special";
    /** This constant represents the y coord of the centre of the stationary notes
     */
    public static final int CENTRE_Y = 657;
    /** This constant represents the image of an up lane
     */
    public static final Image LANE_UP = new Image("res/laneUp.PNG");
    /** This constant represents the image of a down lane
     */
    public static final Image LANE_DOWN = new Image("res/laneDown.PNG");
    /** This constant represents the image of a right lane
     */
    public static final Image LANE_RIGHT = new Image("res/laneRight.PNG");
    /** This constant represents the image of a left lane
     */
    public static final Image LANE_LEFT = new Image("res/laneLeft.PNG");
    /** This constant represents the image of a special lane
     */
    public static final Image LANE_SPECIAL = new Image("res/laneSpecial.PNG");
    /** This attribute represents the type of Lane
     */
    public String type;

    /* constructors, setters, getters */
    /** Constructor for lane
     * @param pos This sets the position of the lane
     * @param type This sets the type of lane
     */
    public Lane(Point pos, String type) {
        super(pos);
        this.type = type;
    }

    /* methods */
    /** This method draws the lanes for respective level
     * @param lvlLane This is the lanes to draw for the given level
     */
    public static void drawLane(ArrayList<Lane> lvlLane) {
        for (Lane lane : lvlLane) {
            Point lanPos = lane.pos;
            String laneType = lane.type;
            switch (laneType) {
                case Lane.RIGHT:
                    lane.draw(lanPos, Lane.LANE_RIGHT);
                    break;
                case Lane.LEFT:
                    lane.draw(lanPos, Lane.LANE_LEFT);
                    break;
                case Lane.UP:
                    lane.draw(lanPos, Lane.LANE_UP);
                    break;
                case Lane.DOWN:
                    lane.draw(lanPos, Lane.LANE_DOWN);
                    break;
                case Lane.SPECIAL:
                    lane.draw(lanPos, Lane.LANE_SPECIAL);
                    break;
            }
        }
    }
}
