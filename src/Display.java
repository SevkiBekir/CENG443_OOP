
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Display extends JPanel {



    private static Display display = null;
    private Common common;



    /**
     * Default constructor
     */
    public Display() {
        super();
        common = Common.getCommon();
        display = null;
        getPrefferedSize();
        common.setPlot(new PricePlot(new Position(200, 0)));
        common.setTicker(new Ticker(new Position(0,760)));

//        this.setLayout(new GridBagLayout());

    }

    /**
     *
     */

    public static Display getDisplay() {
        if (display == null)
            display = new Display();

        return display;
    }

    /**
     * @return
     */
    public Dimension getPrefferedSize() {
        // TODO implement here

        if(!common.isSetWindowSize())
        {
            common.setWindowWidth(1000);
            common.setWindowHeight(800);
        }


        return new Dimension(common.getWindowWidth(), common.getWindowHeight());
    }



    /**
     * @return
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // To make edges smooth (Anti-aliasing)

//        Image image = new ImageIcon("assets/Hitcoin.gif").getImage();
//        g2d.drawImage(image,300,300,null);

        common.setIconHeight(200);
        common.setIconWidth(200);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(common.getIconFile()));
            bufferedImage = common.getScaledImage(bufferedImage, common.getIconWidth(), common.getIconHeight());
            g2d.drawImage(bufferedImage, 0, 0, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.drawRect(200, 0, 800, 200);


        PricePlot pricePlot = common.getPlot();
        pricePlot.draw(g2d);


        Ticker ticker = common.getTicker();
        ticker.draw(g2d);


        for (Trader trader: common.getTraders()) {
            trader.draw(g2d);

        }

        for (Order order: common.getOrders()){
            order.draw(g2d);
        }

        for (ConMan conMan: common.getConmen()){
            conMan.draw(g2d);
        }


//        this.add(new IconComponent());

//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.anchor = GridBagConstraints.NORTHWEST;
////		constraints.insets = new Insets(10, 10, 10, 10);
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.weighty = 20;
//        constraints.weightx = 20;
//
//
//        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

//        Image biggerImage = new ImageIcon("assets/Hitcoin.gif").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
//        ImageIcon imageIcon = new ImageIcon(biggerImage);
//        JLabel icon = new JLabel(imageIcon);
//        icon.setBounds(0,0,icon.getPreferredSize().width,icon.getPreferredSize().height);
//        this.add(icon);
//        JButton button = new JButton("hello");
//        button.setSize(800,200);
//        button.setBounds(200,0,button.getWidth(),button.getHeight());
//
//        this.add(button);


        //
//        this.add(icon, constraints);
//
//        constraints.gridx = 1;
//        constraints.gridy = 0;
//        constraints.weighty = 20;
//        constraints.weightx = 800;
//        constraints.fill = GridBagConstraints.HORIZONTAL;
//        this.add(button, constraints);


//		this.add(icon,constraints);

//		this.add(icon,constraints);

    }


}
