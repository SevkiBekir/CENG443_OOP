
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private static boolean isPaused;

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
		isPaused = true;
        window = new JFrame();
        window.setSize(display.getPrefferedSize());
        window.getContentPane().add(display);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HitCoin Business by SevkiBekir");
        window.setVisible(true);
        window.setResizable(false);
//        window.pack();

		window.addKeyListener(new KeyListener() {
			String selectedItem = "";

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				selectedItem = "";
				selectedItem += e.getKeyChar();
				selectedItem = selectedItem.toLowerCase();
				if (selectedItem.equals("q")) {
					System.exit(0);
				}else if (selectedItem.equals("p")){
					if(isPaused){
						timer.stop();
					}else
						timer.start();
					isPaused = !isPaused;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		System.out.println("Press Q to quit.");

		String osName = System.getProperty("os.name");
		if(osName.startsWith("Windows")){
			common.setFileDescriptor("\\");
		}else
			common.setFileDescriptor("/");

    }

}