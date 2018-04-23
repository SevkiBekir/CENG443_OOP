
import java.awt.*;
import java.util.*;

/**
 * 
 */
public abstract class State {

	private  String name;
	private Common common;

	/**
	 * Default constructor
	 */
	public State() {
		common = Common.getCommon();
	}


	/**
	 * @param c 
	 */
	public abstract void step(ConMan c);
	public abstract void drawStateStatus(int x, int y, Graphics2D g2d);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Common getCommon() {
		return common;
	}
}