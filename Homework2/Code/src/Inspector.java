//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

import java.lang.reflect.Field;
import java.util.List;

public class Inspector extends Logic {
    //=================================================================================================================================================

    @Override
    public void run(Robot robot) {
        // TODO
        //
        // When implementing this method, the following messages might be printed where appropriate:
        //
        // System.out.printf( "Robot %02d : Detected a broken robot (%02d), adding it to broken robots list.%n" , ... ) ;
        // System.out.printf( "Robot %02d : Notifying waiting fixers.%n"                                        , ... ) ;


        Class baseClass = robot.getClass();
        if (baseClass.getName().equals("Base")) {
            try {
                Field serialNoField = baseClass.getDeclaredField("serialNo");
                serialNoField.setAccessible(true);
                int serialNo = serialNoField.getInt(robot);

                List<Robot> robots = Runner.factory.robots;


                for (Robot myRobot : robots) {
                    synchronized (myRobot) {

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

                        if (arm == null || payload == null || logic == null) {
                            //there is a broken robot
                            if (!Runner.factory.brokenRobots.contains(myRobot)) {

                                Runner.factory.brokenRobots.add(myRobot);
                                synchronized (System.out) {
                                    System.out.printf("Robot %02d :Detected a broken robot (%02d), adding it to broken robots list.%n", serialNo, serialNoOfMyRobot);
                                }
                            }
                        }

                        logicField.setAccessible(false);
                        payloadField.setAccessible(false);
                        armField.setAccessible(false);
                        serialNoFieldOfMyRobot.setAccessible(false);
                    }
                }


                synchronized (System.out) {
                    System.out.printf("Robot %02d :Notifying waiting fixers.%n", serialNo);
                }

                synchronized (Runner.factory.brokenRobots) {
                    Runner.factory.brokenRobots.notifyAll();
                }


                serialNoField.setAccessible(false);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        //=================================================================================================================================================
    }
}

//***************************************************************************************************************************************************
