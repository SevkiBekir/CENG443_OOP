
import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 *
 */
public class Common {

    /**
     * Default constructor
     */
    public Common() {
        common = null;
        windowWidth = 0;
        windowHeight = 0;
        iconWidth = 0;
        iconWidth = 0;
        assetFolder = "assets/";


        iconFile = new StringBuilder().append(assetFolder).append("Hitcoin.gif").toString();


        photoListOfTraders = new ArrayList<>();
        traders = new ArrayList<>();
        orders = new ArrayList<>();


    }


    private static Common common = null;
    private String assetFolder;
    private String iconFile;
    private List<String> photoListOfTraders;
    private int iconWidth, iconHeight;
    /**
     *
     */
    private int windowWidth;

    /**
     *
     */
    private int windowHeight;

    /**
     *
     */
    private PricePlot plot;

    /**
     *
     */
    private Ticker ticker;

    /**
     *
     */
    private List<Trader> traders;

    /**
     *
     */
    private List<ConMan> conmen;

    /**
     *
     */
    private List<Order> orders;

    public static Common getCommon() {
        if (common == null)
            common = new Common();

        return common;
    }

    /**
     *
     */

    public boolean isSetWindowSize() {
        return windowHeight != 0 && windowWidth != 0;
    }

    public void stepAllEntities() {
        // TODO implement here
        plot.step();
        ticker.step();
        for (Trader trader : traders) {
            trader.step();

        }

        for (Order order : orders) {
            order.step();

        }

        processOrderOperation();
    }

    private void printAllOrders() {
        System.out.println("**** ALL ORDERS ****");
        for (Order order : orders) {
            System.out.println(order.getTrader().getName());
            order.getPosition().print();

        }

        System.out.println("**********");
    }

    private void processOrderOperation() {
        List<Order> waitingToDeleteOrder = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).isArrivedTarget()) {
                waitingToDeleteOrder.add(orders.get(i));
            }
        }
        if (waitingToDeleteOrder.size() != 0) {
            for (int i = 0; i < waitingToDeleteOrder.size(); i++) {
                orders.remove(waitingToDeleteOrder.get(i));
            }
//            printAllOrders();


        }

    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public PricePlot getPlot() {
        return plot;
    }

    public void setPlot(PricePlot plot) {
        this.plot = plot;
    }

    public int generateRandomInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public String getIconFile() {
        return iconFile;
    }

    public List<Trader> getTraders() {
        return traders;
    }

    public BufferedImage getScaledImage(BufferedImage src, int w, int h) {
        int finalw = w;
        int finalh = h;
        double factor = 1.0d;
        if (src.getWidth() > src.getHeight()) {
            factor = ((double) src.getHeight() / (double) src.getWidth());
            finalh = (int) (finalw * factor);
        } else {
            factor = ((double) src.getWidth() / (double) src.getHeight());
            finalw = (int) (finalh * factor);
        }

        BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, finalw, finalh, null);
        g2.dispose();
        return resizedImg;
    }

    private void arrangeTraders() {
        int offset = 22;
        int photoWidth = 0;
        int positionX = 0;
        int positionY = 475;

        int initialCoins = 10000;
        int initialCapital = 500000;
        int netMoney = initialCapital + initialCoins*getPlot().getCurrentCoinPrice();

        Trader trader = new Trader();
        photoWidth = trader.getPhotoWidth();

        positionX = offset;
        trader.setPosition(new Position(positionX, positionY));
        trader.setName("Bill Gates");
        trader.setNickName("Hell Gates");
        trader.setCoins(initialCoins);
        trader.setNetMoney(netMoney);
        trader.setCapital(initialCapital);
        trader.setPhoto(photoListOfTraders.get(0));
        traders.add(trader);


        trader = new Trader();
        positionX += 2 * offset + photoWidth;
        trader.setPosition(new Position(positionX, positionY));
        trader.setName("Warren Buffet");
        trader.setNickName("Warum Buffet");
        trader.setCoins(initialCoins);
        trader.setNetMoney(netMoney);
        trader.setCapital(initialCapital);
        trader.setPhoto(photoListOfTraders.get(1));
        traders.add(trader);


        trader = new Trader();
        positionX += 2 * offset + photoWidth;
        trader.setPosition(new Position(positionX, positionY));
        trader.setName("Selim Temizer");
        trader.setNickName("King Trader");
        trader.setCoins(initialCoins);
        trader.setNetMoney(netMoney);
        trader.setCapital(initialCapital);
        trader.setPhoto(photoListOfTraders.get(2));
        traders.add(trader);

        trader = new Trader();
        positionX += 2 * offset + photoWidth;
        trader.setPosition(new Position(positionX, positionY));
        trader.setName("Nick Leeson");
        trader.setNickName("Rogue Trader");
        trader.setCoins(initialCoins);
        trader.setNetMoney(netMoney);
        trader.setCapital(initialCapital);
        trader.setPhoto(photoListOfTraders.get(3));
        traders.add(trader);

        trader = new Trader();
        positionX += 2 * offset + photoWidth;
        trader.setPosition(new Position(positionX, positionY));
        trader.setName("George Soros");
        trader.setNickName("Doom Bringer");
        trader.setCoins(initialCoins);
        trader.setNetMoney(netMoney);
        trader.setCapital(initialCapital);
        trader.setPhoto(photoListOfTraders.get(4));
        traders.add(trader);


    }

    private void arrangeImageListOfTraders() {
        photoListOfTraders.add(new StringBuilder().append(assetFolder).append("BillGates.gif").toString());
        photoListOfTraders.add(new StringBuilder().append(assetFolder).append("WarrenBuffet.gif").toString());
        photoListOfTraders.add(new StringBuilder().append(assetFolder).append("SelimTemizer.gif").toString());
        photoListOfTraders.add(new StringBuilder().append(assetFolder).append("NickLeeson.gif").toString());
        photoListOfTraders.add(new StringBuilder().append(assetFolder).append("GeorgeSoros.gif").toString());
    }


    public void getCommonReadyToUse() {
        arrangeImageListOfTraders();
        arrangeTraders();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getOrdersWrtTrader(Trader trader) {
        int size = 0;
        for (Order order : orders) {
            if (order.getTrader() == trader)
                size++;
        }

        return size;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }
}