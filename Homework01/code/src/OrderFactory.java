
import java.util.*;

/**
 * 
 */
public abstract class OrderFactory {

	/**
	 * Default constructor
	 */
	public OrderFactory() {
	}

	/**
	 * @param trader 
	 * @return
	 */
	public abstract Order createOrder(Trader trader);

}