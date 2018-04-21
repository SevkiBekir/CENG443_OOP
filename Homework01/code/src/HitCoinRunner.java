
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class HitCoinRunner {

	/**
	 * Default constructor
	 */
	public HitCoinRunner() {
	}

	/**
	 * 
	 */
	private static JFrame window;

	/**
	 * 
	 */
	private static Display display;

	/**
	 * 
	 */
    private static Common common;

    private static Timer timer;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO implement here
		System.out.println("Starting!");


        singletonConstructors();


        windowOperations();





	}

	private static ActionListener timerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			common.stepAllEntities();
			window.repaint();

		}


	};


	private static void singletonConstructors(){
	    display = Display.getDisplay();
	    common = Common.getCommon();

	    common.getCommonReadyToUse();

	    timer = new Timer(50,timerListener);
	    timer.start();



    }

	private static void windowOperations(){
        window = new JFrame();
        window.setSize(display.getPrefferedSize());
        window.getContentPane().add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HitCoin Business by SevkiBekir");
        window.setVisible(true);
//        window.pack();
    }

}