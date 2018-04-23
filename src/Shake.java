
import java.awt.*;
import java.util.*;

/**
 * arrangeConManState
 */
public class Shake extends State {

    private Position initialPosition;

    /**
     * Default constructor
     */
    public Shake() {
        super();
        setName("Shake");
        initialPosition = new Position();
    }

    /**
     * @param c
     */
    public void step(ConMan c) {
        shakeMovement(c);

    }

    public void drawStateStatus(int x, int y, Graphics2D g2d) {
        int offset = 25;
        x += offset;
        g2d.drawString(getName(), x, y);
    }


    private void shakeMovement(ConMan c) {
//        System.out.println("***" + c.getName() +"****");
//        initialPosition.print();
//        System.out.println("*********");

        int offset = 5;
        Position boundryPosition = new Position(initialPosition.getX() - offset, initialPosition.getY() - offset);
        int boundryWidth = 2 * offset;
        int boundryHeight = 2 * offset;
        int offsetX = getCommon().generateRandomInt(1, 500);
        int offsetY = getCommon().generateRandomInt(1, 500);
        offsetX %= offset;
        offsetY %= offset;

        int randomChoice = getCommon().generateRandomInt(0, 500);
        Position newPosition;
        randomChoice %= 2;
        if (randomChoice == 0)
            newPosition = new Position(c.getPosition().getX() + offsetX, c.getPosition().getY() + offsetY);
        else
            newPosition = new Position(c.getPosition().getX() - offsetX, c.getPosition().getY() - offsetY);


        while (true) {
            if (newPosition.getX() >= boundryPosition.getX() &&
                    newPosition.getY() >= boundryPosition.getY() &&
                    newPosition.getX() <= boundryPosition.getX() + boundryWidth &&
                    newPosition.getY() <= boundryPosition.getY() + boundryHeight) {
                break;
            } else {
                offsetX = getCommon().generateRandomInt(1, 500);
                offsetY = getCommon().generateRandomInt(1, 500);
                offsetX %= offset;
                offsetY %= offset;
                if (randomChoice == 0)
                    newPosition = new Position(c.getPosition().getX() + offsetX, c.getPosition().getY() + offsetY);
                else
                    newPosition = new Position(c.getPosition().getX() - offsetX, c.getPosition().getY() - offsetY);
            }
        }

//        while (true) {
//            if (newPosition.getX() >= boundryPosition.getX() &&
//                    newPosition.getY() >= boundryPosition.getY() &&
//                    newPosition.getX() <= boundryPosition.getX() + boundryWidth &&
//                    newPosition.getY() <= boundryPosition.getY() + boundryHeight) {
//                break;
//            } else {
//                if (newPosition.getX() > boundryPosition.getX() &&
//                        newPosition.getY() > boundryPosition.getY() &&
//                        !(newPosition.getX() < boundryPosition.getX() + boundryWidth &&
//                                newPosition.getY() < boundryPosition.getY() + boundryHeight)
//                        ) {
//
//                    newPosition = new Position(c.getPosition().getX() + offsetX, c.getPosition().getY() + offsetY);
//
//                } else if (!(newPosition.getX() > boundryPosition.getX() &&
//                        newPosition.getY() > boundryPosition.getY()) &&
//                        newPosition.getX() < boundryPosition.getX() + boundryWidth &&
//                        newPosition.getY() < boundryPosition.getY() + boundryHeight
//                        ) {
//
//                    newPosition = new Position(c.getPosition().getX() - offsetX, c.getPosition().getY() - offsetY);
//
//                } else {
//                    newPosition = new Position(c.getPosition().getX(), c.getPosition().getY());
//                    if (newPosition.getX() < boundryPosition.getX()) {
//                        newPosition.setX(newPosition.getX() + offsetX);
//                    } else if (newPosition.getX() > boundryPosition.getX()) {
//                        newPosition.setX(newPosition.getX() - offsetX);
//                    }
//                    if (newPosition.getY() < boundryPosition.getY()) {
//                        newPosition.setY(newPosition.getY() + offsetY);
//                    } else if (newPosition.getY() > boundryPosition.getY()) {
//                        newPosition.setY(newPosition.getY() - offsetY);
//                    }
//                }
//
//
//                continue;
//            }
//        }

        c.setPosition(newPosition);

//        if (newPosition.getX() >= boundryPosition.getX() &&
//                newPosition.getY() >= boundryPosition.getY() &&
//                newPosition.getX() <= boundryPosition.getX() + boundryWidth &&
//                newPosition.getY() <= boundryPosition.getY() + boundryHeight) {
//            c.setPosition(newPosition);
//        }


    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }
}