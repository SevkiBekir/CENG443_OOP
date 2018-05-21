
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class Expert extends ConManDecorator {

	private Graphics2D g2d;

	/**
	 * Default constructor
	 */
	public Expert(ConMan c) {
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
		if(getDecoratedConMan().getMoneyStolen()>5000){
			int offset = 25;
			int rectWidth = 15;
			int rectHeight = 15;
			g2d.setColor(Color.blue);
			g2d.fillRect(getDecoratedConMan().getPosition().getX()+5*offset,getDecoratedConMan().getPosition().getY()-offset,rectWidth,rectHeight);

		}
		getDecoratedConMan().draw(g2d);


	}

}