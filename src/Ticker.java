
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 */
public class Ticker extends Entity {

    private int tickerHeight, tickerWidth;
    private int screenWidth;
    private int screenHeight;
    private Common common;
    private Graphics2D g2d;
    private int counter;
    private Integer[] arrowTypeX;
    private Integer[] arrowTypeY;
    private List<Integer> randomNumbersForShown;
    private List<String> companyNameForShown;
    private int companySizeToShown;
    private int howManyCompanyShown;

    private final String[] bist100 = {
            "AEFES", "AFYON", "AKBNK", "AKENR", "AKSA", "AKSEN", "ALARK", "ALCTL", "ALGYO", "ALKIM",
            "ANACM", "ANELE", "ARCLK", "ASELS", "AYGAZ", "BAGFS", "BANVT", "BIMAS", "BIZIM", "BJKAS",
            "BRISA", "CCOLA", "CEMTS", "CLEBI", "CRFSA", "DEVA", "DOAS", "DOHOL", "ECILC", "EGEEN",
            "EKGYO", "ENKAI", "ERBOS", "EREGL", "FENER", "FROTO", "GARAN", "GLYHO", "GOLTS", "GOODY",
            "GOZDE", "GSDHO", "GSRAY", "GUBRF", "HALKB", "HLGYO", "ICBCT", "IHLAS", "IPEKE", "ISCTR",
            "ISGYO", "IZMDC", "KARSN", "KARTN", "KCHOL", "KIPA", "KLGYO", "KONYA", "KORDS", "KOZAA",
            "KOZAL", "KRDMD", "MAVI", "METRO", "MGROS", "NETAS", "NTTUR", "ODAS", "OTKAR", "PETKM",
            "PGSUS", "PRKME", "SAHOL", "SASA", "SISE", "SKBNK", "SODA", "TATGD", "TAVHL", "TCELL",
            "THYAO", "TKFEN", "TKNSA", "TMSN", "TOASO", "TRCAS", "TRKCM", "TSKB", "TSPOR", "TTKOM",
            "TTRAK", "TUPRS", "ULKER", "VAKBN", "VESTL", "VKGYO", "YATAS", "YAZIC", "YKBNK", "ZOREN"
    };

    //-------------------------------------------------------------------------------------------------------------------------------------------------

    // Coordinates for polygons (up arrow, right arrow, down arrow)

    private final Integer[] xUp = {7, 14, 10, 10, 4, 4, 0};
    private final Integer[] yUp = {0, 7, 7, 14, 14, 7, 7};

    private final Integer[] xRight = {0, 7, 7, 14, 7, 7, 0};
    private final Integer[] yRight = {4, 4, 0, 7, 14, 10, 10};

    private final Integer[] xDown = {4, 10, 10, 14, 7, 0, 4};
    private final Integer[] yDown = {0, 0, 7, 7, 14, 7, 7};

    /**
     * Default constructor
     */
    public Ticker(Position position) {
        super();
        this.setPosition(position);
        common = Common.getCommon();
        screenWidth = common.getWindowWidth();
        screenHeight = common.getWindowHeight();
        tickerHeight = screenHeight - getPosition().getY();
        tickerWidth = screenWidth;
        counter = 0;
        randomNumbersForShown = new ArrayList<>();
        companyNameForShown = new ArrayList<>();
        companySizeToShown = 10;
        howManyCompanyShown = companySizeToShown + 1;

        arrangeRandomNumbers();
        arrangeCompanyNames();
    }

    /**
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        drawTickerArea();

        drawCompanies();

    }

    /**
     *
     */
    public void step() {
        this.drawCompanies();
        counter++;
        addNewCompany();
    }

    public String getRandomElement() {
        int rnd = new Random().nextInt(bist100.length);
        return bist100[rnd];
    }

    private void drawCompanies() {
        int offset = 100;
        int positionX = 30;
        for (int i = 0; i < companySizeToShown + 1; i++) {
            drawOneCompany(positionX, i);
            positionX += offset;
        }

    }

    private void arrangeCompanyNames() {
        for (int i = 0; i < companySizeToShown + 1; i++) {
            companyNameForShown.add(getRandomElement());
        }
    }

    private boolean isOneCompanyFinishedToShow() {
        return counter == 100;
    }

    private void addNewCompany() {
        if (isOneCompanyFinishedToShow()) {
            howManyCompanyShown++;
            randomNumbersForShown.remove(0);
            generateRandomNumbersForArrowType();

            howManyCompanyShown %= 100;
            companyNameForShown.remove(0);
//            companyNameForShown.add(bist100[howManyCompanyShown]);
            companyNameForShown.add(getRandomElement());

            drawOneCompany(screenWidth + 30, 10);
            counter = 0;


        }
    }

    private void drawTickerArea() {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(getPosition().getX(), getPosition().getY(), tickerWidth, tickerHeight);
    }

    private void arrangeRandomNumbers() {
        for (int i = 0; i < companySizeToShown + 1; i++) {
            generateRandomNumbersForArrowType();
        }
    }

    private void generateRandomNumbersForArrowType() {
        int rand = common.generateRandomInt(0, 300);
        rand %= 3;
        randomNumbersForShown.add(rand);
    }

    private void getArrowType(int rand) {

        if (rand == 0) {
            arrowTypeX = xUp;
            arrowTypeY = yUp;
            g2d.setColor(Color.GREEN);
        } else if (rand == 1) {
            arrowTypeX = xDown;
            arrowTypeY = yDown;
            g2d.setColor(Color.RED);

        } else if (rand == 2) {
            arrowTypeX = xRight;
            arrowTypeY = yRight;
            g2d.setColor(Color.BLACK);

        }
    }

    private void drawOneCompany(int positionX, int index) {

        try{
            int offset = 25;
        int textPositionX = getPosition().getX() + positionX - counter;
        int textPositionY = getPosition().getY() + offset;
        int arrowPositionX = textPositionX - 20;
        int arrowPositionY = getPosition().getY() + offset / 2;
        //text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.BOLD, 16));
        g2d.drawString(companyNameForShown.get(index), textPositionX, textPositionY);

        // drawArror
        getArrowType(randomNumbersForShown.get(index));
        Polygon arrow = new Polygon();
        for (int i = 0; i < arrowTypeX.length; i++)
            arrow.addPoint(arrowPositionX + arrowTypeX[i], arrowPositionY + arrowTypeY[i]);
        g2d.fillPolygon(arrow);
        }catch (Exception ex){

        }

//		System.out.println("textPosStart:"+textPositionX+" textPosEnd:"+(textPositionX+50)+" arrPos:"+arrowPositionX+" w:"+(textPositionX+50-arrowPositionX));

    }

}