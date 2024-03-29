//***************************************************************************************************************************************************

import java.util.List;
import java.util.ArrayList;

//***************************************************************************************************************************************************

public class Factory {
    //=================================================================================================================================================

    private static int nextSerialNo = 1;

    /*-----------------------------------------------------------------------------------------------------------------------------------------------*/

    public static Base createBase() {
        // TODO
        //
        // ...

        nextSerialNo++;
        return new Base(nextSerialNo - 1);

    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------*/

    public static Part createPart(String name) {
        // TODO
        //
        // When implementing this method, you may throw the following exception if things do not work as expected:
        //
        // SmartFactoryException( "Failed: createPart!" )
        try {

            Class reflectionClass = Class.forName(name);
            switch (reflectionClass.getName()) {
                case "Arm":
                    return new Arm();
                case "Gripper":
                    return new Gripper();
                case "Supplier":
                    return new Supplier();
                case "Welder":
                    return new Welder();
                case "Camera":
                    return new Camera();
                case "Builder":
                    return new Builder();
                case "MaintenanceKit":
                    return new MaintenanceKit();
                case "Fixer":
                    return new Fixer();
                case "Inspector":
                    return new Inspector();
            }
        } catch (Exception e) {
            throw new SmartFactoryException("Failed: createPart!");
        }

        return null;
    }

    //=================================================================================================================================================

    public int maxRobots;
    public List<Robot> robots;
    public ProductionLine productionLine;
    public Storage storage;
    public List<Robot> brokenRobots;
    public boolean stopProduction;

    //=================================================================================================================================================

    public Factory(int maxRobots, int maxProductionLineCapacity, int maxStorageCapacity) {
        this.maxRobots = maxRobots;
        this.robots = new ArrayList<>();
        this.productionLine = new ProductionLine(maxProductionLineCapacity);
        this.storage = new Storage(maxStorageCapacity);
        this.brokenRobots = new ArrayList<>();
        this.stopProduction = false;

        Base robot;

        robot = createBase();

        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("Gripper"));
        Runner.set(robot, "logic", createPart("Supplier"));
        robots.add(robot);

        robot = createBase();
        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("Welder"));
        Runner.set(robot, "logic", createPart("Builder"));
        robots.add(robot);

        robot = createBase();
        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("Camera"));
        Runner.set(robot, "logic", createPart("Inspector"));
        robots.add(robot);

        robot = createBase();
        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("Camera"));
        Runner.set(robot, "logic", createPart("Inspector"));
        robots.add(robot);

        robot = createBase();
        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("MaintenanceKit"));
        Runner.set(robot, "logic", createPart("Fixer"));
        robots.add(robot);

        robot = createBase();
        Runner.set(robot, "arm", createPart("Arm"));
        Runner.set(robot, "payload", createPart("MaintenanceKit"));
        Runner.set(robot, "logic", createPart("Fixer"));
        robots.add(robot);
    }

    //=================================================================================================================================================

    public void start() {
        for (Robot r : robots) {
            new Thread(r).start();
        }
    }

    //=================================================================================================================================================

    public void initiateStop() {
        stopProduction = true;

        synchronized (robots) {
            for (Robot r : robots) {
                synchronized (r) {
                    r.notifyAll();
                }
            }  // Wake up broken robots (which wait on themselves)
        }

        synchronized (productionLine) {
            productionLine.notifyAll();
        }        // Wake up builder robots (which wait on productionLine)
        synchronized (brokenRobots) {
            brokenRobots.notifyAll();
        }        // Wake up fixer   robots (which wait on brokenRobots  )
    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
