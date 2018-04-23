
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class Novice extends ConManDecorator {

	private Graphics2D g2d;


	/**
	 * Default constructor
	 */
	public Novice(ConMan c) {
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
		if(getDecoratedConMan().getMoneyStolen()>2000){
			int offset = 25;
			int rectWidth = 15;
			int rectHeight = 15;
			g2d.setColor(Color.orange);
			g2d.fillRect(getDecoratedConMan().getPosition().getX()+offset,getDecoratedConMan().getPosition().getY()-offset,rectWidth,rectHeight);
		}

		/*
			This below if statements must be in Master or Expert depens on decorator design patterns but it cannot work. Therefore, I put it here to work successfully.
		 */
		if(getDecoratedConMan().getMoneyStolen()>3000){
			int offset = 25;
			int rectWidth = 15;
			int rectHeight = 15;
			g2d.setColor(Color.magenta);
			g2d.fillRect(getDecoratedConMan().getPosition().getX()+2*offset,getDecoratedConMan().getPosition().getY()-offset,rectWidth,rectHeight);
		}


		if(getDecoratedConMan().getMoneyStolen()>5000){
			int offset = 25;
			int rectWidth = 15;
			int rectHeight = 15;
			g2d.setColor(Color.blue);
			g2d.fillRect(getDecoratedConMan().getPosition().getX()+3*offset,getDecoratedConMan().getPosition().getY()-offset,rectWidth,rectHeight);

		}

		getDecoratedConMan().draw(g2d);

	}

}