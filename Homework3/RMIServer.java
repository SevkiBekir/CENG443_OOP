//***************************************************************************************************************************************************

// TODO : imports

//***************************************************************************************************************************************************


//***************************************************************************************************************************************************

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    //=================================================================================================================================================

    private static String codebase = "http//localhost/";  // An HTTP/FTP/NFS/FS codebase
    private static String name = "RemoteDatabaseServer";  // Name to be registered in RMI Registry

    //=================================================================================================================================================

    public static void main(String args[]) throws Exception {
        if (args.length > 0) {
            codebase = args[0];
        }
        if (args.length > 1) {
            name = args[1];
        }

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        // TODO
        // ....

//        if(System.getSecurityManager() == null){
//            System.setSecurityManager(new SecurityManager());
//        }

        System.setProperty ( "java.rmi.server.codebase" , codebase  ) ;

        RMIImplementation obj = new RMIImplementation();
        RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(obj, 0);

        LocateRegistry.getRegistry().rebind(""+ java.net.URLEncoder.encode(codebase+name, "UTF-8"), stub);

        System.out.println("Server is ready for remote invocations by client");


        //-----------------------------------------------------------------------------------------------------------------------------------------------

        JFrame frame = new JFrame("Server");
        JButton button = new JButton("Exit");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        button.addActionListener(listener);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(button);
        frame.setSize(450, 150);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
