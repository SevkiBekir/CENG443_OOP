//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Fixer extends Logic {
    //=================================================================================================================================================

    @Override
    public void run(Robot robot) {

        // TODO
        //
        // When implementing this method, the following messages might be printed where appropriate:
        //
        // System.out.printf( "Robot %02d : Fixed and waken up robot (%02d).%n"   , ... ) ;
        // System.out.printf( "Robot %02d : Nothing to fix, waiting!%n"           , ... ) ;
        // System.out.printf( "Robot %02d : Fixer woke up, going back to work.%n" , ... ) ;


        Class baseClass = robot.getClass();
        if (baseClass.getName().equals("Base")) {
            try {
                Field serialNoField = baseClass.getDeclaredField("serialNo");
                serialNoField.setAccessible(true);
                int serialNo = serialNoField.getInt(robot);


                synchronized (System.out) {
                    System.out.printf("Robot %02d : Fixer woke up, going back to work.%n", serialNo);
                }

                List<Robot> robots = Runner.factory.brokenRobots;


                if (robots.size() == 0) {
                    synchronized (System.out) {
                        System.out.printf("Robot %02d : Nothing to fix, waiting!%n", serialNo);
                    }
                    synchronized (robots) {
                        robots.wait();
                    }
                } else {
                    synchronized (robots) {
                        if (robots.size() != 0) {

                            Robot myRobot = robots.get(0);
                            Class baseClassOfMyRobot = myRobot.getClass();

                            Field serialNoFieldOfMyRobot = baseClassOfMyRobot.getDeclaredField("serialNo");
                            serialNoFieldOfMyRobot.setAccessible(true);
                            int serialNoOfMyRobot = serialNoFieldOfMyRobot.getInt(myRobot);

                            Field armField = baseClassOfMyRobot.getDeclaredField("arm");
                            armField.setAccessible(true);
                            Arm arm = (Arm) armField.get(myRobot);

                            Field payloadField = baseClassOfMyRobot.getDeclaredField("payload");
                            payloadField.setAccessible(true);
                            Payload payload = (Payload) payloadField.get(myRobot);


                            Field logicField = baseClassOfMyRobot.getDeclaredField("logic");
                            logicField.setAccessible(true);
                            Logic logic = (Logic) logicField.get(myRobot);

                            boolean isFixed = false;
                            if (arm == null) {
                                //there is a broken-armless robot
                                Runner.set(myRobot, "arm", Factory.createPart("Arm"));
                                isFixed = true;

                            } else if (payload == null) {

                                if (logic == null) {
                                    int randomNo = Runner.random.nextInt(4);
                                    switch (randomNo) {
                                        case 0:
                                            Runner.set(myRobot, "payload", Factory.createPart("Gripper"));
                                            break;
                                        case 1:
                                            Runner.set(myRobot, "payload", Factory.createPart("Supplier"));
                                            break;
                                        case 2:
                                            Runner.set(myRobot, "payload", Factory.createPart("Welder"));
                                            break;
                                        case 3:
                                            Runner.set(myRobot, "payload", Factory.createPart("Camera"));
                                            break;
                                    }
                                } else {
                                    //find appropriate parts according to logic
                                    switch (logic.getClass().getName()) {
                                        case "Supplier":
                                            Runner.set(myRobot, "payload", Factory.createPart("Gripper"));
                                            break;
                                        case "Builder":
                                            Runner.set(myRobot, "payload", Factory.createPart("Welder"));
                                            break;
                                        case "Inspector":
                                            Runner.set(myRobot, "payload", Factory.createPart("Camera"));
                                            break;
                                        case "Fixer":
                                            Runner.set(myRobot, "payload", Factory.createPart("MaintenanceKit"));
                                            break;
                                    }
                                }


                                isFixed = true;
                            } else if (logic == null) {
                                switch (payload.getClass().getName()) {
                                    case "Gripper":
                                        Runner.set(myRobot, "logic", Factory.createPart("Supplier"));
                                        break;
                                    case "Welder":
                                        Runner.set(myRobot, "logic", Factory.createPart("Builder"));
                                        break;
                                    case "Camera":
                                        Runner.set(myRobot, "logic", Factory.createPart("Inspector"));
                                        break;
                                    case "MaintenanceKit":
                                        Runner.set(myRobot, "logic", Factory.createPart("Fixer"));
                                        break;
                                }
                                isFixed = true;
                            }


                            if (isFixed) {
                                synchronized (System.out) {
                                    System.out.printf("Robot %02d : Fixed and waken up robot (%02d).%n", serialNo, serialNoOfMyRobot);
                                }
                                synchronized (myRobot) {
                                    myRobot.notify();

                                }

                                synchronized (Runner.robotsDisplay) {

                                    Runner.robotsDisplay.repaint();
                                }


                            }

                            logicField.setAccessible(false);
                            payloadField.setAccessible(false);
                            armField.setAccessible(false);
                            serialNoFieldOfMyRobot.setAccessible(false);


                            robots.remove(0);
                        }
                    }


                }


                serialNoField.setAccessible(false);


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }


    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
