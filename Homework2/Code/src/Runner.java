//***************************************************************************************************************************************************

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

//***************************************************************************************************************************************************

public class Runner {
    //=================================================================================================================================================

    public static Factory factory = null;
    public static int breakdownProbabilityConstant = 100;  // This is used in "wearAndTear" method in "Base" class, higher -> less breakdowns
    public static int robotSleepDurationConstant = 400;  // This is used in "run"         method in "Base" class, higher -> slower animation

    //-------------------------------------------------------------------------------------------------------------------------------------------------

    public static RobotsDisplay robotsDisplay = null;
    public static JFrame robotsWindow = null;
    public static ProductionLineDisplay productionLineDisplay = null;
    public static JFrame productionLineWindow = null;
    public static StorageDisplay storageDisplay = null;
    public static JFrame storageWindow = null;

    //-------------------------------------------------------------------------------------------------------------------------------------------------

    public static final Map<String, BufferedImage> robotImageFiles = new HashMap<>();
    public static final Map<String, BufferedImage> payloadImageFiles = new HashMap<>();
    public static final Map<String, BufferedImage> logicImageFiles = new HashMap<>();
    public static final Random random = new Random();

    //=================================================================================================================================================

    static {
        try {
            robotImageFiles.put("Base", ImageIO.read(new File("Images/" + "Base" + ".jpg")));
            robotImageFiles.put("Arm", ImageIO.read(new File("Images/" + "Arm" + ".jpg")));
            robotImageFiles.put("BaseArm", ImageIO.read(new File("Images/" + "BaseArm" + ".jpg")));
            payloadImageFiles.put("Gripper", ImageIO.read(new File("Images/" + "Gripper" + ".jpg")));
            payloadImageFiles.put("Welder", ImageIO.read(new File("Images/" + "Welder" + ".jpg")));
            payloadImageFiles.put("Camera", ImageIO.read(new File("Images/" + "Camera" + ".jpg")));
            payloadImageFiles.put("MaintenanceKit", ImageIO.read(new File("Images/" + "MaintenanceKit" + ".jpg")));
            logicImageFiles.put("Supplier", ImageIO.read(new File("Images/" + "Supplier" + ".jpg")));
            logicImageFiles.put("Builder", ImageIO.read(new File("Images/" + "Builder" + ".jpg")));
            logicImageFiles.put("Inspector", ImageIO.read(new File("Images/" + "Inspector" + ".jpg")));
            logicImageFiles.put("Fixer", ImageIO.read(new File("Images/" + "Fixer" + ".jpg")));
        } catch (IOException e) {
            throw new SmartFactoryException("Failed: images!");
        }
    }

    //=================================================================================================================================================

    public static synchronized Object get(Object object, String fieldName)  // Get the value of a private instance field
    {
        // TODO
        //
        // When implementing this method, you may throw the following exception if things do not work as expected:
        //
        // SmartFactoryException( "Failed: get!" )


        try {
            Class reflectionClass = object.getClass();
            if (reflectionClass.getName().equals("Base")) {
                switch (fieldName) {
                    case "arm":
                        Field arm = reflectionClass.getDeclaredField("arm");
                        return arm.get(object);
                    case "payload":
                        Field payload = reflectionClass.getDeclaredField("payload");
                        return payload.get(object);
                    case "logic":
                        Field logic = reflectionClass.getDeclaredField("logic");
                        return logic.get(object);
                }
            }

        } catch (Exception e) {
            throw new SmartFactoryException("Failed: set!");
        }
        return null;
    }

    //=================================================================================================================================================

    public static synchronized void set(Object object, String fieldName, Object value)  // Set the value of a private instance field
    {
        // TODO
        //
        // When implementing this method, you may throw the following exception if things do not work as expected:
        //
        // SmartFactoryException( "Failed: set!" )


        try {
            Class reflectionClass = object.getClass();
            if (reflectionClass.getName().equals("Base")) {
                Boolean isAccessible = false;
                switch (fieldName) {
                    case "arm":
                        Field arm = reflectionClass.getDeclaredField("arm");
                        isAccessible = arm.isAccessible();
                        arm.setAccessible(true);
                        arm.set(object, value);
                        arm.setAccessible(isAccessible);
                        break;
                    case "payload":
                        Field payload = reflectionClass.getDeclaredField("payload");
                        isAccessible = payload.isAccessible();
                        payload.setAccessible(true);
                        payload.set(object, value);
                        payload.setAccessible(isAccessible);
                        break;
                    case "logic":
                        Field logic = reflectionClass.getDeclaredField("logic");
                        isAccessible = logic.isAccessible();
                        logic.setAccessible(true);
                        logic.set(object, value);
                        logic.setAccessible(isAccessible);
                        break;
                }
            }

        } catch (Exception e) {
            throw new SmartFactoryException("Failed: set!");
        }
    }

    //=================================================================================================================================================

    public static void draw(Graphics2D g2d, String imageName) {
        BufferedImage image = null;
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;

        if (robotImageFiles.containsKey(imageName)) {
            image = robotImageFiles.get(imageName);
            width = image.getWidth();
            height = image.getHeight();
            x = 1;
            y = 0;
        } else if (payloadImageFiles.containsKey(imageName)) {
            image = payloadImageFiles.get(imageName);
            width = image.getWidth() * 11 / 15;
            height = image.getHeight() * 11 / 15;
            x = 200 - width;
            y = 0;
        } else if (logicImageFiles.containsKey(imageName)) {
            image = logicImageFiles.get(imageName);
            width = image.getWidth() * 9 / 15;
            height = image.getHeight() * 9 / 15;
            x = 200 - width - 10;
            y = 70;
        }

        if (image != null) {
            g2d.drawImage(image, x, y, width, height, null);
        }
    }

    //=================================================================================================================================================

    public Runner(int maxRobots, int maxProductionLineCapacity, int maxStorageCapacity) {
        //-----------------------------------------------------------------------------------------------------------------------------------------------

        factory = new Factory(maxRobots, maxProductionLineCapacity, maxStorageCapacity);

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        robotsDisplay = new RobotsDisplay();
        robotsWindow = new JFrame("Robots");
        productionLineDisplay = new ProductionLineDisplay();
        productionLineWindow = new JFrame("Production Line");
        storageDisplay = new StorageDisplay();
        storageWindow = new JFrame("Storage");

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        robotsWindow.add(robotsDisplay);
        robotsWindow.setLocation(50, 50);
        robotsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        robotsWindow.pack();

        productionLineWindow.add(productionLineDisplay);
        productionLineWindow.setLocation(50, 350);
        productionLineWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productionLineWindow.pack();

        storageWindow.add(storageDisplay);
        storageWindow.setLocation(50, 650);
        storageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        storageWindow.pack();

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                robotsWindow.setVisible(true);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                productionLineWindow.setVisible(true);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                storageWindow.setVisible(true);
            }
        });

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        // Add keypress processing capability

        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher
                (
                        new java.awt.KeyEventDispatcher() {
                            @Override
                            public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
                                String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();

                                if (key.equals("pressed Q")) {
                                    System.exit(0);
                                    return true;
                                }

                                return false;
                            }
                        }
                );

        System.out.println("Press Q to quit");
        System.out.println();

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        factory.start();

        //-----------------------------------------------------------------------------------------------------------------------------------------------
    }

    //=================================================================================================================================================

    public static void main(String[] args) {
        //-----------------------------------------------------------------------------------------------------------------------------------------------

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { /* Do nothing */ }

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        int maxRobots = 8;
        int maxProductionLineCapacity = 10;
        int maxStorageCapacity = 10;

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        if (args.length >= 1) {
            maxRobots = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            maxProductionLineCapacity = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            maxStorageCapacity = Integer.parseInt(args[2]);
        }
        if (args.length >= 4) {
            breakdownProbabilityConstant = Integer.parseInt(args[3]);
        }
        if (args.length >= 5) {
            robotSleepDurationConstant = Integer.parseInt(args[4]);
        }

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        new Runner(maxRobots, maxProductionLineCapacity, maxStorageCapacity);

        //-----------------------------------------------------------------------------------------------------------------------------------------------
    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
