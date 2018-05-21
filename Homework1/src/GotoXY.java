
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class GotoXY extends State {

	private Position targetPosition;
	private int howManyStepAtRestX;
	private int howManyStepAtRestY;
	private ConMan conMan;
	private int speed;
	/**
	 * Default constructor
	 */
	public GotoXY() {
		super();
		setName("GotoXY");
		targetPosition = selectArbitraryPoint();
		howManyStepAtRestX = -1;
		howManyStepAtRestY = -1;
		speed = getCommon().generateRandomInt(5, 10);
	}

	/**
	 * @param c
	 */
	public void step(ConMan c) {
		this.conMan = c;
		gotoXYMovement();
	}

	public void drawStateStatus(int x, int y, Graphics2D g2d) {
		int offset = 25;
		x += offset;
		g2d.drawString(getName(),x,y);
	}

	private void gotoXYMovement(){
		int positionX = conMan.getPosition().getX();
		int positionY = conMan.getPosition().getY();
		int speedX = speed, speedY = speed;

		if (positionX < targetPosition.getX()) {
			howManyStepAtRestX = (targetPosition.getX() - positionX) / speed;
			if (howManyStepAtRestX <= 0)
				speedX = (targetPosition.getX() - positionX);

		} else if (positionX > targetPosition.getX()) {
			howManyStepAtRestX = (positionX - targetPosition.getX()) / speed;
			if (howManyStepAtRestX <= 0) {
				speedX = (positionX - targetPosition.getX());
			}
		}

		howManyStepAtRestY = Math.abs(targetPosition.getY() - conMan.getPosition().getY()) / speed;
		if (howManyStepAtRestY <= 0)
			speedY = (positionY - targetPosition.getY());

		howManyStepAtRestY--;
		howManyStepAtRestX--;

		Position newPos = new Position(positionX, positionY);
		if (positionX > targetPosition.getX()) {
			newPos.setX(positionX - speedX);
		} else if (positionX < targetPosition.getX()) {
			newPos.setX(positionX + speedX);
		}

		if (positionY > targetPosition.getY()) {
			newPos.setY(positionY - speedY);
		}

		conMan.setPosition(newPos);
	}


	private Position selectArbitraryPoint(){
		Position areaPosition = new Position(0,getCommon().getIconHeight());
		int areaWidth = getCommon().getWindowWidth();
		int areaHeight = getCommon().getTraders().get(0).getPosition().getY() - getCommon().getIconHeight();

		int getX = getCommon().generateRandomInt(1,areaWidth-100);
		int getY = getCommon().generateRandomInt(areaPosition.getY()+1,areaHeight+getCommon().getIconHeight());
		System.out.println((areaPosition.getY()+1)+","+areaHeight+getCommon().getIconHeight());
		return new Position(getX,getY);
	}
}