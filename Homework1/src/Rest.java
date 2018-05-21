
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class Rest extends State {

	/**
	 * Default constructor
	 */
	public Rest() {
		super();
		setName("Rest");
	}

	/**
	 * @param c
	 */
	public void step(ConMan c) {


	}

	public void drawStateStatus(int x, int y, Graphics2D g2d){
		int offset = 30;
		x += offset;
		g2d.drawString(getName(),x,y);
	}


}