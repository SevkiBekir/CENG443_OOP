
import java.util.*;

/**
 * 
 */
public abstract class State {

	/**
	 * Default constructor
	 */
	public State() {
	}


	/**
	 * @param c 
	 * @param common
	 */
	public abstract void step(ConMan c, Common common);

}