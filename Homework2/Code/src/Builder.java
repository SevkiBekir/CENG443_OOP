//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Builder extends Logic {
    //=================================================================================================================================================


    @Override
    public void run(Robot robot) {
        // TODO
        //
        // When implementing this method, the following messages might be printed where appropriate:
        //
        // System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , ... ) ;
        // System.out.printf( "Robot %02d : Builder cannot build anything, waiting!%n"                     , ... ) ;
        // System.out.printf( "Robot %02d : Builder woke up, going back to work.%n"                        , ... ) ;

        Class baseClass = robot.getClass();
        if (baseClass.getName().equals("Base")) {
            try {

                Field serialNoField = baseClass.getDeclaredField("serialNo");
                serialNoField.setAccessible(true);
                int serialNo = serialNoField.getInt(robot);

                synchronized (System.out) {
                    System.out.printf("Robot %02d : Builder woke up, going back to work.%n", serialNo);
                }

                List<Part> parts = Runner.factory.productionLine.parts;
                boolean isChangedSomething = false;

                for (Part partAsBase:parts) {
                    synchronized (partAsBase){
                        Class classOfPartAsBase = partAsBase.getClass();
                        if(classOfPartAsBase.getName().equals("Base")){

                            Field serialNoOfRobotField = baseClass.getDeclaredField("serialNo");
                            serialNoOfRobotField.setAccessible(true);
                            int serialNoOfRobot = serialNoOfRobotField.getInt(partAsBase);

                            Runner.factory.robots.add(new Base(serialNoOfRobot));
                            Runner.factory.productionLine.parts.remove(partAsBase);

                            serialNoOfRobotField.setAccessible(false);
                        }
                    }

                }



                List<Robot> robots = Runner.factory.robots;
                for (Robot myRobot:robots) {
                        Class robotClass = myRobot.getClass();

                        // find base and attach it something
                        Field armField = robotClass.getDeclaredField("arm");
                        armField.setAccessible(true);
                        Arm arm = (Arm) armField.get(myRobot);

                        Field payloadField = robotClass.getDeclaredField("payload");
                        payloadField.setAccessible(true);
                        Payload payload = (Payload) payloadField.get(myRobot);


                        Field logicField = robotClass.getDeclaredField("logic");
                        logicField.setAccessible(true);
                        Logic logic = (Logic) logicField.get(myRobot);

                        if (arm == null && payload == null && logic == null) {
                            // find arm
                            for (int i = 0; i < parts.size(); i++) {
                                Class classOfPart = parts.get(i).getClass();
                                if (classOfPart.getName().equals("Arm")) {
                                    Arm armInParts = (Arm) parts.get(i);
                                    armField.set(myRobot, armInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                }
                            }

                        }

                        else if (arm != null && payload == null && logic == null) {
                            // then add payload to arm-base
                            for (int i = 0; i < parts.size(); i++) {
                                Class classOfPart = parts.get(i).getClass();
                                if (classOfPart.getName().equals("Gripper")) {
                                    Gripper payloadInParts = (Gripper) parts.get(i);
                                    payloadField.set(myRobot, payloadInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (classOfPart.getName().equals("Welder")) {
                                    Welder payloadInParts = (Welder) parts.get(i);
                                    payloadField.set(myRobot, payloadInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (classOfPart.getName().equals("Camera")) {
                                    Camera payloadInParts = (Camera) parts.get(i);
                                    payloadField.set(myRobot, payloadInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (classOfPart.getName().equals("MaintenanceKit")) {
                                    MaintenanceKit payloadInParts = (MaintenanceKit) parts.get(i);
                                    payloadField.set(myRobot, payloadInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                }
                            }

                        }

                        else if (arm != null && payload != null && logic == null) {
                            // then add logic to payload-arm-base

                            for (int i = 0; i < parts.size(); i++) {
                                Class classOfPart = parts.get(i).getClass();
                                if (payload.getClass().getName().equals("Gripper") && classOfPart.getName().equals("Supplier")) {
                                    Supplier logicInParts = (Supplier) parts.get(i);
                                    logicField.set(myRobot, logicInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (payload.getClass().getName().equals("Welder") && classOfPart.getName().equals("Builder")) {
                                    Builder logicInParts = (Builder) parts.get(i);
                                    logicField.set(myRobot, logicInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (payload.getClass().getName().equals("Camera") && classOfPart.getName().equals("Inspector")) {
                                    Inspector logicInParts = (Inspector) parts.get(i);
                                    logicField.set(myRobot, logicInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                } else if (payload.getClass().getName().equals("MaintenanceKit") && classOfPart.getName().equals("Fixer")) {
                                    Fixer logicInParts = (Fixer) parts.get(i);
                                    logicField.set(myRobot, logicInParts);
                                    parts.remove(i);
                                    isChangedSomething = true;
                                    synchronized (System.out) {
                                        System.out.printf("Robot %02d : Builder attached some parts or relocated a completed robot.%n", serialNo);
                                    }
                                    break;
                                }
                            }


                            // move completed robot to storage
                            if (Runner.factory.storage.robots.size() != Runner.factory.storage.maxCapacity) {
                                // there is a empty area in storage
                                Runner.factory.storage.robots.add(myRobot);

                                Runner.storageDisplay.repaint();

                            } else {
                                // there is NO empty area in storage then stop production.
                                Runner.factory.stopProduction = true;
                            }

                        }



                        logicField.setAccessible(false);
                        payloadField.setAccessible(false);
                        armField.setAccessible(false);



                }



                synchronized (Runner.productionLineDisplay) {

                    Runner.productionLineDisplay.repaint();
                }

                synchronized (Runner.storageDisplay) {

                    Runner.storageDisplay.repaint();
                }

                synchronized (Runner.robotsDisplay) {

                    Runner.robotsDisplay.repaint();
                }


                if (!isChangedSomething) {
                    synchronized (System.out) {
                        System.out.printf("Robot %02d : Builder cannot build anything, waiting!%n", serialNo);
                    }
                    synchronized (parts) {
                        parts.wait();
                    }

                }

                serialNoField.setAccessible(false);




            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
