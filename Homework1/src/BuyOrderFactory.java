
import java.util.*;

/**
 * 
 */
public class BuyOrderFactory extends OrderFactory {

	/**
	 * Default constructor
	 */
	public BuyOrderFactory() {
	}

	/**
	 * @param trader
	 * @return
	 */
	public Order createOrder(Trader trader) {
		return  new BuyOrder(trader);
	}

}