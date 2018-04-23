
import java.awt.*;
import java.util.*;

/**
 * 
 */
public abstract class Entity {

	/**
	 * Default constructor
	 */
	public Entity() {
		position = new Position();
	}

	/**
	 * 
	 */
	private Position position;

	/**
	 * @param g2d
	 */
	public abstract void draw(Graphics2D g2d);

	/**
	 * 
	 */
	public abstract void step();

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}