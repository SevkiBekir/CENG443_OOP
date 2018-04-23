
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 
 */
public class BasicConMan extends ConMan {

	private Graphics2D g2d;
	private Common common;
	private final int conManWidth, conManHeight;
	private final int photoWidth, photoHeight;
	private int stepSize;


	/**
	 * Default constructor
	 */
	public BasicConMan(String name, Position pos, String photo) {
		super();
		setPhoto(photo);
		setPosition(pos);
		setName(name);
		common = Common.getCommon();

		conManHeight = 145;
		conManWidth = 100;

		photoHeight = 100;
		photoWidth = conManWidth;
		stepSize = 0;

	}

	private void drawText(){
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("default", Font.BOLD, 14));

		int offsetX = 25;
		int offsetY = 15;
		int textX=getPosition().getX()+offsetX,textY = getPosition().getY();
		g2d.drawString(getName(), textX, textY);

		textY += photoHeight+offsetY;
		textX  = getPosition().getX();
		g2d.setColor(Color.blue);
		getState().drawStateStatus(textX,textY,g2d);


		textY += offsetY;
		textX -= -2*offsetX+(offsetX/4);
		g2d.setColor(Color.red);
		g2d.drawString(new StringBuilder().append(getMoneyStolen()).toString(),textX,textY);


	}

	private void drawImage() {
		int offsetY = 25;
		try {
			BufferedImage bufferedImage = ImageIO.read(new File(getPhoto()));
			bufferedImage = common.getScaledImage(bufferedImage, photoWidth, photoHeight);
			g2d.drawImage(bufferedImage, getPosition().getX(), getPosition().getY(), null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 */
	public void step() {
		drawImage();
		drawText();
		getState().step(this);
		checkAnyOrdersAreStolen();

	}


	public void draw(Graphics2D g2d) {
		this.g2d = g2d;
		drawImage();
		drawText();
		arrangeConManState();
		getState().step(this);
		checkAnyOrdersAreStolen();

	}



	private void arrangeConManState(){
		stepSize++;
		if(stepSize%50 == 0){
			int stateNo = common.generateRandomInt(0,30);
			stateNo %=4;
			if(stateNo == 0)
				setState(new Rest());
			else if(stateNo == 1){
//				System.out.println("*****************Selected Shake*****************");
				Shake s = new Shake();
				if(getState().getName().equals("Shake"))
					s.setInitialPosition(((Shake) getState()).getInitialPosition());
				else
					s.setInitialPosition(new Position(this.getPosition().getX(),this.getPosition().getY()));
				setState(s);
			}
			else if(stateNo == 2)
				setState(new GotoXY());
			else if(stateNo == 3)
				setState(new ChaseClosest());
			stepSize= 0;
		}
	}

	private void checkAnyOrdersAreStolen(){
		List<Order> copyOfOrders = new ArrayList<>(common.getOrders());

		Position getOriginOfConMan = new Position(this.getPosition().getX()+photoWidth/2, this.getPosition().getY()+photoHeight/2);

		for (Order order: copyOfOrders) {
			double distance = order.getPosition().distanceFromThisTo(getOriginOfConMan);
			/*
				if distance is equal to 1, it's stolen
			 */
			try {
				if(distance <= photoWidth/2+5){
					Trader trader = order.getTrader();
					if(order.getOrderType() == 1){
						int totalPayment = order.getSize() * common.getPlot().getCurrentCoinPrice();
						trader.setCapital(trader.getCapital() - totalPayment);
						this.setMoneyStolen(this.getMoneyStolen() + totalPayment);

					}else if (order.getOrderType() == 2){
						trader.setCoins(trader.getCoins() - order.getSize());
						this.setMoneyStolen(this.getMoneyStolen() + order.getSize()*common.getPlot().getCurrentCoinPrice());
					}
					common.getOrders().remove(order);
				}
			}catch (Exception ex){

			}

		}
	}


}