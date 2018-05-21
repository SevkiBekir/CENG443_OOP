
import java.awt.*;
import java.util.*;

/**
 * 
 */
public  abstract class ConManDecorator extends ConMan {

	private ConMan decoratedConMan;

	/**
	 * Default constructor
	 */
	public ConManDecorator() {
	}


	/**
	 * @param c
	 */
	public ConManDecorator(ConMan c) {
		super(c);
		decoratedConMan = c;
	}



    /**
     *
     */
    public abstract void step();

    /**
     *
     */

    public abstract void draw(Graphics2D g2d);

	public ConMan getDecoratedConMan() {
		return decoratedConMan;
	}

	public void setDecoratedConMan(ConMan decoratedConMan) {
		this.decoratedConMan = decoratedConMan;
	}

	public abstract void drawRectange();
}