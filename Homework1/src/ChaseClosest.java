
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 */
public class ChaseClosest extends State {

    private ConMan conMan;
    private Position targetPosition;
    private int howManyStepAtRestX;
    private int howManyStepAtRestY;
    private int speed;

    /**
     * Default constructor
     */
    public ChaseClosest() {
        super();
        setName("Chase Closest");
        howManyStepAtRestX = -1;
        howManyStepAtRestY = -1;
        targetPosition = new Position();
        speed = getCommon().generateRandomInt(5, 10);
    }

    /**
     * @param c
     */
    public void step(ConMan c) {
        this.conMan = c;
        targetPosition = getPositionOfClosestOrder();
        chaseClosestMovement();

    }

    public void drawStateStatus(int x, int y, Graphics2D g2d) {
        int offset = 5;
        x -= offset;
        g2d.drawString(getName(), x, y);
    }

    private Position getPositionOfClosestOrder() {

        double min = Double.MAX_VALUE;
        Order closestOrder = null;
        List<Order> orders = new ArrayList<>(getCommon().getOrders());
        for (Order order : orders) {
            double getDistance = order.getPosition().distanceFromThisTo(conMan.getPosition());
            if (getDistance < min) {
                min = getDistance;
                closestOrder = order;
            }

        }

        return closestOrder.getPosition();
    }

    private void chaseClosestMovement() {
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

}