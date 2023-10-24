import bagel.Font;
import bagel.Window;
import bagel.util.Point;

/** This class represents the scoring system
 * @author Du-Simon Nguyen
 */
public class Score implements StringWriteable{
    /* attributes */
    /** This constant represents PERFECT
     */
    public static final String PERFECT_MSG = "PERFECT";
    /** This constant represents GOOD
     */
    public static final String GOOD_MSG = "GOOD";
    /** This constant represents BAD
     */
    public static final String BAD_MSG = "BAD";
    /** This constant represents MISS
     */
    public static final String MISS_MSG = "MISS";
    /** This constant represents the perfect score
     */
    public static final int PERFECT_SCORE = 10;
    /** This constant represents the good score
     */
    public static final int GOOD_SCORE = 5;
    /** This constant represents the bad score
     */
    public static final int BAD_SCORE = -1;
    /** This constant represents the miss score
     */
    public static final int MISS_SCORE = -5;
    /** This constant represents the position at which the score should be displayed
     */
    public static final Point TOTAL_POS = new Point(35, 35);
    /** This constant represents the string which should display the score
     */
    public static final String SCORE = "SCORE ";
    /** This constant represents how long each message should be shown on screen
     */
    public static final int MSG_FRAME = 30;
    /** This constant represents the font for the score
     */
    public static final Font SCORE_FONT = new Font("res/FSO8BITR.TTF", 30);
    /** This constant represents the font for the message
     */
    public static final Font MSG_FONT = new Font("res/FSO8BITR.TTF", 40);
    /** This attribute represents the total score
     */
    private int totalScore;

    /* constructors, setters, getters */
    /** constructor for score
     * @param totalScore This represents the initial score
     */
    public Score(int totalScore) {
        this.totalScore = totalScore;
    }
    /** getter for totalScore
     * @return int Return totalScore
     */
    public int getTotalScore() {
        return totalScore;
    }
    /** setter for total score
     * @param totalScore This is what the score is set to
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    /* methods */
    /** This method finds the corresponding score given distance at which a note is pressed
     * @param dist This is the distance at which a note is pressed
     * @return int Return the points that should be added to total score
     */
    public int score(int dist) {
        int points;
        if (dist <= Note.PERFECT_DIST) {
            points = PERFECT_SCORE;
        } else if (dist <= Note.GOOD_DIST) {
            points = GOOD_SCORE;
        } else if (dist <= Note.BAD_DIST) {
            points = BAD_SCORE;
        } else if (dist <= Note.MISS_DIST){
            points = MISS_SCORE;
        } else {
            /* the input does not count, not close to any note */
            points = 0;
        }
        return points;
    }

    /** This method is for scoring speed notes
     * @param dist This is the distance at which a note is pressed
     * @param note This is the note which is scored for
     * @return int Return the points that should be added to total score
     */
    public int scoreSpecial(int dist, Note note) {
        int points = 0;
        /* only speed notes have an associated score */
        if (note instanceof SlowDownNote || note instanceof SpeedUpNote) {
            if (dist <= SpecialNote.ACTIVATION_DIST) {
                points = SpeedUpNote.ACTIVATION_SCORE;
            }
        }
        return points;
    }

    /** This method writes the total score
     * @param pos This is the position to write the string
     * @param text This is the text to be written
     * @param font This is the font to write the string in
     */
    @Override
    public void write(Point pos, String text, Font font) {
        font.drawString(text, pos.x, pos.y);
    }

    /** This method writes the score msg effect in centre of screen
     * @param points This is the points for a note which corresponds to a score string
     * @param note This is the note that the output msg is for
     */
    public void msg(int points, Note note) {
        if (note instanceof HoldNote || note instanceof NormalNote) {
            if (points == PERFECT_SCORE) {
                MSG_FONT.drawString(PERFECT_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth("PERFECT")/2, Window.getHeight()/2.0);
            } else if (points == GOOD_SCORE) {
                MSG_FONT.drawString(GOOD_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth("GOOD")/2, Window.getHeight()/2.0);
            } else if (points == BAD_SCORE) {
                MSG_FONT.drawString(BAD_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth("BAD")/2, Window.getHeight()/2.0);
            } else if (points == MISS_SCORE) {
                MSG_FONT.drawString(MISS_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth("MISS")/2, Window.getHeight()/2.0);
            }
        }
        else if (note instanceof SpeedUpNote) {
            if (points == SpeedUpNote.ACTIVATION_SCORE) {
                MSG_FONT.drawString(SpeedUpNote.EFFECT_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth(SpeedUpNote.EFFECT_MSG)/2, Window.getHeight()/2.0);
            }
        }
        else if (note instanceof SlowDownNote) {
            if (points == SlowDownNote.ACTIVATION_SCORE) {
                MSG_FONT.drawString(SlowDownNote.EFFECT_MSG,
                        Window.getWidth()/2.0 - MSG_FONT.getWidth(SlowDownNote.EFFECT_MSG)/2, Window.getHeight()/2.0);
            }
        }
        else if (note instanceof DoubleScoreNote) {
            MSG_FONT.drawString(DoubleScoreNote.EFFECT_MSG,
                    Window.getWidth()/2.0 - MSG_FONT.getWidth(DoubleScoreNote.EFFECT_MSG)/2, Window.getHeight()/2.0);
        }
        else if (note instanceof BombNote) {
            MSG_FONT.drawString(BombNote.EFFECT_MSG,
                    Window.getWidth()/2.0 - MSG_FONT.getWidth(BombNote.EFFECT_MSG)/2, Window.getHeight()/2.0);
        }
    }
}
