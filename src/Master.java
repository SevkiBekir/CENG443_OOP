
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class Master extends ConManDecorator {

	private Graphics2D g2d;

	/**
	 * Default constructor
	 */
	public Master(ConMan c) {
		super(c);
		setDecoratedConMan(c);
	}

	public void step() {
		getDecoratedConMan().step();


	}

	/**
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		this.g2d = g2d;
		drawRectange();
	}

	public void drawRectange(){
		if(getDecoratedConMan().getMoneyStolen()>3000){
			int offset = 25;
			int rectWidth = 10;
			int rectHeight = 8;
			g2d.setColor(Color.magenta);
			g2d.fillRect(getDecoratedConMan().getPosition().getX()+3*offset,getDecoratedConMan().getPosition().getY()-offset,rectWidth,rectHeight);
		}

		getDecoratedConMan().draw(g2d);

	}

}