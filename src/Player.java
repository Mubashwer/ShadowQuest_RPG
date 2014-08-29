import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	public static final int PLAYER_START_X = 756;
	public static final int PLAYER_START_Y = 684;
	public static final float SPEED = 0.4F;
	private final String ImageLocation = "assets/units/player.png";

	private int xPos;
	private int yPos;

	Image playerImg;
	Image playerImgLeft;

	private boolean isFacingRight;

	public Player() throws SlickException{
		this.xPos = PLAYER_START_X;
		this.yPos = PLAYER_START_Y;
		this.isFacingRight = true;

		playerImg = new Image(ImageLocation);
		playerImgLeft = playerImg.getFlippedCopy(true, false);
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}
	
	public void draw(int xScreen, int yScreen) throws SlickException{
		playerImg.drawCentered(xScreen, yScreen);
	}
	public void move(int xDir, int yDir, int delta) {
		float pixels = SPEED * delta;
		xPos = xPos + Math.round(xDir * pixels);
		yPos = yPos + Math.round(yDir * pixels); 
		
	}
	
}
