//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

import java.lang.reflect.Field;
import java.util.List;

public class Supplier extends Logic {
    //=================================================================================================================================================

    private int getEmptyIndexInProductionLine() {
        List<Part> parts = Runner.factory.productionLine.parts;
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) == null)
                return i;
        }
        return -1;
    }

    @Override
    public void run(Robot robot) {
        // TODO
        //
        // When implementing this method, the following messages might be printed where appropriate:
        //
        // System.out.printf( "Robot %02d : Supplying a random part on production line.%n"                           , ... ) ;
        // System.out.printf( "Robot %02d : Production line is full, removing a random part from production line.%n" , ... ) ;
        // System.out.printf( "Robot %02d : Waking up waiting builders.%n"                                           , ... ) ;
        //
        // You may also use the following code fragment when implementing this method:
        //

        Class baseClass = robot.getClass();
        if (baseClass.getName().equals("Base")) {
            try {
                Field serialNoField = baseClass.getDeclaredField("serialNo");
                serialNoField.setAccessible(true);
                int serialNo = serialNoField.getInt(robot);
//                synchronized (this) {

                switch (Runner.random.nextInt(16)) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);
                            }
                            Runner.factory.productionLine.parts.add(Factory.createBase());
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }
                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createBase());
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }

                            Runner.factory.productionLine.parts.add(Factory.createPart("Arm"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }

                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Arm"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }
                        break;
                    case 8:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Gripper"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Gripper"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        }
                        break;
                    case 9:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Welder"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Welder"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 10:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Camera"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Camera"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 11:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("MaintenanceKit"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("MaintenanceKit"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 12:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Supplier"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Supplier"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 13:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Builder"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Builder"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 14:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Inspector"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }

                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Inspector"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                    case 15:
                        if (Runner.factory.productionLine.parts.size() != Runner.factory.productionLine.maxCapacity) {
                            // add parts
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Supplying a random part on production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.add(Factory.createPart("Fixer"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }

                        } else {
                            // it is full
                            synchronized (System.out) {
                                System.out.printf("Robot %02d : Production line is full, removing a random part from production line.%n", serialNo);
                                System.out.printf("Robot %02d : Waking up waiting builders.%n", serialNo);

                            }
                            Runner.factory.productionLine.parts.remove(Runner.random.nextInt(10));
                            Runner.factory.productionLine.parts.add(Factory.createPart("Fixer"));
                            synchronized (Runner.productionLineDisplay) {

                                Runner.productionLineDisplay.repaint();
                            }
                            synchronized (Runner.factory.productionLine.parts) {
                                Runner.factory.productionLine.parts.notifyAll();

                            }


                        }

                        break;
                }
//                }

                serialNoField.setAccessible(false);
            } catch (NoSuchFieldException e) {
                throw new SmartFactoryException("Failed: NoSuchField in Supplier!");
            } catch (IllegalAccessException e) {
                throw new SmartFactoryException("Failed: IllegalAccess in Supplier!");

            }

        }
//


    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
