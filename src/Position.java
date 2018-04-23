/**
 *
 */
public class Position {

    /**
     * Default constructor
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     *
     */
    private int x;

    /**
     *
     */
    private int y;

    /**
     * @param position
     * @return
     */
    public double distanceFromThisTo(Position position) {
        double xSquare = Math.abs(this.x - position.getX());
        xSquare*=xSquare;
        double ySquare = Math.abs(this.y - position.getY());
        ySquare*=ySquare;
        double distance = Math.sqrt(xSquare+ySquare);

        // TODO implement here
        return distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void print(){
        System.out.println("X:" + x + " y:" + y);
    }

}