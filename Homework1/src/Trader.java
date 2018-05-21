
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Trader extends Entity {


    /**
     *
     */
    private String name;
    private String nickName;

    /**
     *
     */
    private int coins;

    /**
     *
     */
    private int capital;

    private int netMoney;

    private Graphics2D g2d;

    private final int traderWidth, traderHeight;
    private Common common;


    private String photo;
    private final int photoWidth, photoHeight;

    private int minOrders;
    private int stepSize;



    /**
     * Default constructor
     */
    public Trader() {
        super();
        traderWidth = 150;
        traderHeight = 300;

        common = Common.getCommon();

        name = "";
        nickName = "";
        coins = 0;
        capital = 0;
        netMoney = 0;
        photo = "";

        photoWidth = traderWidth;
        photoHeight = traderWidth + 30;

        minOrders = 5;

        stepSize = 0;



    }

    private void drawImage() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(photo));
            bufferedImage = common.getScaledImage(bufferedImage, photoWidth, photoHeight);
            g2d.drawImage(bufferedImage, getPosition().getX(), getPosition().getY(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void drawText() {
        int offsetY = photoHeight / 11;
        int offsetX = photoWidth / 4;
        int textX = 0, textY = 0;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.BOLD, 16));

        textX = getPosition().getX() + offsetX;
        textY = getPosition().getY() + photoHeight + offsetY;
        g2d.drawString(name, textX, textY);

        textY += offsetY;
        textX -= 10;
        Color purple = new Color(238, 130, 238);
        g2d.setColor(purple);
        g2d.drawString(new StringBuilder().append("'").append(nickName).append("'").toString(), textX, textY);

        textY += offsetY;
//        textX += 10;
        g2d.setColor(Color.BLUE);
        g2d.drawString(new StringBuilder().append(coins).append(" coins").toString(), textX, textY);

        textY += offsetY;
        g2d.setColor(Color.GREEN);
        g2d.drawString(new StringBuilder().append(capital).append(" cash").toString(), textX, textY);

        netMoney = capital + coins * common.getPlot().getCurrentCoinPrice();
        textY += offsetY;
        g2d.setColor(Color.BLACK);
        g2d.drawString(new StringBuilder().append("Net: ").append(netMoney).toString(), textX, textY);


    }

    /**
     * @return
     */
    public Order createOrder() {
        int next = common.generateRandomInt(1, 50);
        next %= 2;
        next += 1;

        //for debug
//        next = 2;

        OrderFactory orderFactory = null;
        if (next == 1) {
            orderFactory = new BuyOrderFactory();
        } else if (next == 2) {
            orderFactory = new SellOrderFactory();
        }
        return orderFactory.createOrder(this);
    }

    /**
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        drawImage();
        drawText();
//        createFirstOrders();
        createOrders();

    }

    /**
     *
     */
    public void step() {
        drawText();
        createOrders();
        stepSize++;
//        System.out.println("****" + name + "****");
//        System.out.println("StepSize" + stepSize + "orderSize:" + common.getOrdersWrtTrader(this));
//        System.out.println("************");
    }

    private void createFirstOrders(){
        Order order = createOrder();
        order.setPosition(new Position(this.getPosition().getX()+(this.photoWidth/2), this.getPosition().getY()-10));
        common.getOrders().add(order);
    }

    public void createOrders() {



        /*
            if it is 0, then generate new Order
         */
//        if(getRandomNumber == 0){
//            if(common.getOrdersWrtTrader(this) <= minOrders){
//                Order order = createOrder();
//                order.setPosition(new Position(this.getPosition().getX()+(this.photoWidth/2), this.getPosition().getY()-10));
//                common.getOrders().add(order);
//            }
//        }

        for (int i = common.getOrdersWrtTrader(this); i < minOrders && stepSize % 5 == 0; i++) {
            Order order = createOrder();
            order.setPosition(new Position(this.getPosition().getX()+(this.photoWidth/2), this.getPosition().getY()-10));
            common.getOrders().add(order);
            stepSize = 0;

        }
    }

    public int getNetMoney() {
        return netMoney;
    }

    public void setNetMoney(int netMoney) {
        this.netMoney = netMoney;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTraderWidth() {
        return traderWidth;
    }

    public int getTraderHeight() {
        return traderHeight;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }
}
