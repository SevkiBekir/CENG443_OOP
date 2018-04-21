
import java.awt.*;
import java.util.*;

/**
 *
 */
public abstract class Order extends Entity {


    /**
     *
     */
    private Trader trader;
    private Color color;
    private int speed;
    private int size;
    /*
    orderType -> 1 -> Buy
    orderType -> 2 -> Sell
     */
    private int orderType;

    private Common common;
    private Graphics2D g2d;
    private Position targetPosition;
    private int howManyStepAtRestX;
    private int howManyStepAtRestY;
    private boolean isArrivedTarget;
    private int hittedPrice;

    /**
     * Default constructor
     */
    public Order() {
        super();
        common = Common.getCommon();
        orderType = -1;
        trader = null;
        color = null;
        speed = common.generateRandomInt(5, 15);
        size = common.generateRandomInt(1, 15);
        targetPosition = getTargetPosition();
        howManyStepAtRestX = -1;
        howManyStepAtRestY = targetPosition.getY() - getPosition().getY() / speed;
        isArrivedTarget = false;
        hittedPrice = 0;

    }

    private Position getTargetPosition() {
        Position pos = new Position();
        pos.setX(common.getPlot().getPosition().getX() + common.generateRandomInt(0, common.getPlot().getPlotWidth()));
        pos.setY(common.getPlot().getPlotHeight() + common.getPlot().getPosition().getY());

        return pos;
    }


    /**
     *
     */


    /**
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        drawOrder();
    }

    private void drawOrder() {
        int radius = 20;
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();
        int textOffset = 3;
        g2d.setColor(color);
        g2d.fillOval(positionX, positionY, radius, radius);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.BOLD, 11));

        int textX = positionX + textOffset + 1;
        int textY = positionY + 4 * textOffset;

        g2d.drawString(new StringBuilder().append(size).toString(), textX, textY);

        int orTextX = positionX + textOffset;
        int orTextY = positionY - 2 * textOffset;
        g2d.setColor(color);
        g2d.drawString(getOriginatingTitle(), orTextX, orTextY);

    }

    private String getOriginatingTitle() {
        if (this.trader.getName().equals("Bill Gates"))
            return "BG";
        else if (this.trader.getName().equals("Nick Leeson"))
            return "NL";
        else if (this.trader.getName().equals("George Soros"))
            return "GS";
        else if (this.trader.getName().equals("Selim Temizer"))
            return "ST";
        else if (this.trader.getName().equals("Warren Buffet"))
            return "WB";
        else
            return null;
    }

    /**
     *
     */
    public void step() {
        drawOrder();
        movementOfOrder();
        checkOrderIsArrived();

    }

    protected void  orderOperation(){

    }

    private void checkOrderIsArrived() {
        if (getPosition().getY() <= common.getPlot().getPosition().getY() + common.getPlot().getPlotHeight()) {
            isArrivedTarget = true;
            targetPosition.setX(getPosition().getX());
            targetPosition.setY(getPosition().getY());
            int index = (targetPosition.getX() - common.getIconWidth()) -1;
            index = index  == -1?0:index;
            index = index == common.getPlot().getPlotWidth()?common.getPlot().getPlotWidth()-1:index;

            hittedPrice = common.getPlot().getPlotHeight()- common.getPlot().getNewPrice(index);
//            System.out.println(hittedPrice);
//            if (this.getPosition().getY() < 0 || this.getPosition().getX() < 0) {
//                System.out.println("************");
//                System.out.println(this.trader.getName() + " is deleted");
//                this.getPosition().print();
//                System.out.println("************");
//
//            }
//
//            System.out.println("************");
//            System.out.println(this.trader.getName() + " is deleted");
//            this.getPosition().print();
//            System.out.println("************");
        }
    }


    private void movementOfOrder() {
//        speed = 2;
//        System.out.println("****START "+trader.getName()+"******");
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();
        int speedX = speed, speedY = speed;


//        howManyStepAtRestX = howManyStepAtRestX != 0 ? howManyStepAtRestX: speed;
//        int howManyStepAtRestY =  (targetPosition.getY() - positionY) / speed;
//        howManyStepAtRestY = howManyStepAtRestY != 0 ? howManyStepAtRestY: speed;

        /*
            t:          20,5
            o:15,10     20,10       25,10
         */
        if (positionX < targetPosition.getX()) {
            howManyStepAtRestX = (targetPosition.getX() - positionX) / speed;
            if (howManyStepAtRestX <= 0)
                speedX = (targetPosition.getX() - positionX);

        } else if (positionX > targetPosition.getX()) {
            howManyStepAtRestX = (positionX - targetPosition.getX()) / speed;
            if (howManyStepAtRestX <= 0)
                speedX = (positionX - targetPosition.getX());
        }

        howManyStepAtRestY = Math.abs(targetPosition.getY() - getPosition().getY()) / speed;
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
//        if(newPos.getX() < 0 || newPos.getY() <= 200 ){
//            newPos.print();
//        }
//        System.out.println("Target:");
//        targetPosition.print();
//        System.out.println("New Pos:");
//        newPos.print();
        setPosition(newPos);
//        System.out.println("********END******");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Common getCommon() {
        return common;
    }

    public boolean isArrivedTarget() {
        return isArrivedTarget;
    }

    public int getHittedPrice() {
        return hittedPrice;
    }

    public void setHittedPrice(int hittedPrice) {
        this.hittedPrice = hittedPrice;
    }
}