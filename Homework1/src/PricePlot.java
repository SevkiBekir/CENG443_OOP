
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PricePlot extends Entity {


    private int plotHeight, plotWidth;
    private int screenWidth;
    private int screenHeight;
    private Common common;
    private List<Integer> prices;
    private Graphics2D g2d;
    private int currentCoinPrice;
    /**
     * Default constructor
     */
    public PricePlot(Position position) {
        super();
        this.setPosition(position);
        common = Common.getCommon();
        screenWidth = common.getWindowWidth();
        screenHeight = common.getWindowHeight();
        plotHeight = getPosition().getY() + 200;
        plotWidth = screenWidth - getPosition().getX();

        //initial price
//        newPrice = 0;
//        oldPrice = randomPrice();

        prices = new ArrayList<>(plotWidth);
        constructorPrice();
        Integer newValue = randomPrice();

        prices.set(plotWidth - 1, newValue);

        currentCoinPrice = newValue;
    }

    private void constructorPrice(){
        int price = randomPrice();

//        price = randomPrice(price);
        for(int i = 0; i < plotWidth; i++){
            price = randomPrice(price);
            prices.add(price);
        }
    }

    /**
     * @param g2d
     */
    public void draw(Graphics2D g2d) {

        this.g2d = g2d;
        createAllSquaresAndText(g2d);

        drawPriceLine(g2d);
    }

    private int randomPriceToInt() {
        int value = common.generateRandomInt(20, plotHeight - 20);

        return value;
    }

    //=================================================================================================================================================

    private int randomPrice(int previousPrice) {
        int value = previousPrice + common.generateRandomInt(0, 30) - 15;

        if (value <= 0) {
            value = 1;
        }
        if (value >= plotHeight) {
            value = plotHeight - 1;
        }

        return value;
    }


    private Integer randomPrice() {
        int value = common.generateRandomInt(20, plotHeight - 20);

        return value;
    }

    //=================================================================================================================================================

    private Integer randomPrice(Integer previousPrice) {
        int value = previousPrice + common.generateRandomInt(0, 30) - 15;

        if (value <= 0) {
            value = 1;
        }
        if (value >= plotHeight) {
            value = plotHeight - 1;
        }

        return value;
    }

    private void arrangePrices() {
        /*
        1-delete first element
        2-shift left side
        3-assing new price to last element
         */

//        for (int i = 0; i < getPlotWidth() - 1; i++) {
//            prices.set(i, prices.get(i + 1));
//        }
        try{
            prices.remove(0);

            Integer newValue = randomPrice(prices.get(getPlotWidth() - 2));
            prices.add(newValue);

            currentCoinPrice = newValue;
        }catch (Exception ex){

        }



//        prices.set(getPlotWidth() - 1, newValue);
    }

    /**
     *
     */
    public void step() {
        this.drawPriceLine(g2d);
    }

    private int getOldPrice(int index){
        return prices.get(index-1);
    }

    public int getNewPrice(int index){
        return prices.get(index);
    }

    private void printPrices(int from, int to){
        System.out.println("**************BEGIN**************");
        for (int i = from; i < to; i++){
            System.out.println("i->" + i + "=>"+prices.get(i));
        }
        System.out.println("**************END**************");

    }

    private void drawPriceLine(Graphics2D g2d) {
        arrangePrices();

        try {
            g2d.setColor(Color.GREEN);
            g2d.setStroke(new BasicStroke(1));
            for (int i = 1; i < plotWidth; i++) {
                if(getOldPrice(i) == 0)
                    continue;
                g2d.drawLine(getPosition().getX()+1+i, getOldPrice(i), getPosition().getX()+i+2, getNewPrice(i));
            }
        }catch (Exception ex){

        }


//        printPrices(750,800);

    }

    private void createAllSquaresAndText(Graphics2D g2d) {

        /*
            LOGO        ------------pricePlot----------
            width:200   | w: scW-logoW
            h:200       | h: scH- 200
                        |
         */
        int squareEdge = 20;
        int howManySquareInWidth = plotWidth / squareEdge;
        int howManySquareInHeight = plotHeight / squareEdge;

        //text
        g2d.setFont(new Font("default", Font.BOLD, 20));
        g2d.drawString("Price Plot", getPosition().getX() + 2 * squareEdge, getPosition().getY() + 2 * squareEdge);
        //squares

        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < howManySquareInWidth; i++) {
            for (int j = 0; j < howManySquareInHeight; j++) {
                g2d.drawRect(getPosition().getX() + squareEdge * i, squareEdge * j, squareEdge, squareEdge);

            }
        }

    }

    public int getPlotHeight() {
        return plotHeight;
    }

    public void setPlotHeight(int plotHeight) {
        this.plotHeight = plotHeight;
    }

    public int getPlotWidth() {
        return plotWidth;
    }

    public void setPlotWidth(int plotWidth) {
        this.plotWidth = plotWidth;
    }

    public int getCurrentCoinPrice() {
        return currentCoinPrice;
    }
}