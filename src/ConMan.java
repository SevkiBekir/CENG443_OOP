
import java.awt.*;
import java.util.*;

/**
 * 
 */
public abstract class ConMan extends Entity {

    private State state;
	private int moneyStolen;
	private String name;
	private String photo;


	/**
	 * Default constructor
	 */
	public ConMan() {
		super();
		state = new Rest();
		name = "";
		moneyStolen = 0;
		photo = "";


	}

	public ConMan(ConMan c) {
		super();
		state = c.getState();
		name = c.getName();
		moneyStolen = c.getMoneyStolen();
		photo = c.getPhoto();


	}



	/**
	 * 
	 */
	public abstract void step();

	/**
	 * @param g2d
	 */
	public abstract void draw(Graphics2D g2d);



	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getMoneyStolen() {
		return moneyStolen;
	}

	public void setMoneyStolen(int moneyStolen) {
		this.moneyStolen = moneyStolen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}