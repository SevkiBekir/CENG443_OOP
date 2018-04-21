
import java.awt.*;
import java.util.*;

/**
 * 
 */
public abstract class ConMan extends Entity {

    public ConMan decoratedConMan;
    public State state;
	/**
	 * Default constructor
	 */
	public ConMan() {

	}

	/**
	 * 
	 */
	private int moneyStolen;



	/**
	 * 
	 */
	public abstract void step();

	/**
	 * @param g2d
	 */
	public abstract void draw(Graphics2D g2d);



}