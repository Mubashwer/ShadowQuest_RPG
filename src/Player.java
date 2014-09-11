/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Mover;

/**
 * Represents the main player of the game. It handles movement and rendering.
 */
public class Player implements Mover {

	/** Starting x coordinate of the player in the map */
	public static final float PLAYER_START_X = 756;
	/** Starting y coordinate of the player in the map */
	public static final float PLAYER_START_Y = 684;
	/** The location of the player image within assets directory. */
	public static final String PLAYER_IMAGE_LOCATION = "/units/player.png";
	/** x coordinate of player in map. */
	private float xPos;
	/** y coordinate of player in map. */
	private float yPos;
	/** Speed of player in pixels per millisecond. */
	public static final float SPEED = 0.25F;
	/** It is the image of the player. */
	private Image playerImg;
	/** It determines the whether playing is facing left or right. */
	private boolean isFacingLeft;

	/**
	 * Create a new Player object.
	 */
	public Player() throws SlickException {
		this.xPos = PLAYER_START_X;
		this.yPos = PLAYER_START_Y;
		playerImg = new Image(RPG.ASSETS_LOCATION + PLAYER_IMAGE_LOCATION);
		isFacingLeft = false;
	}

	/**
	 * It returns x coordinate of player
	 */
	public float getxPos() {
		return xPos;
	}

	/**
	 * It returns y coordinate of player
	 */
	public float getyPos() {
		return yPos;
	}

	/**
	 * It draws the player in screen with respect to camera.
	 * 
	 * @param camMinX
	 *            top-left x coordinate of camera.
	 * @param camMinY
	 *            top-left y coordinate of camera.
	 */
	public void draw(int camMinX, int camMinY) throws SlickException {
		Image img = playerImg;
		if (isFacingLeft == true)
			img = playerImg.getFlippedCopy(true, false);

		img.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);
	}

	/**
	 * Moves player if path is not blocked.
	 * 
	 * @param world
	 *            It represents the entire game where the player exists.
	 * @param xDir
	 *            The 's movement in the x axis (-1, 0 or 1).
	 * @param yDir
	 *            The 's movement in the y axis (-1, 0 or 1).
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 * @return True if player has moved.
	 */
	public boolean update(World world, float xDir, float yDir, int delta)
			throws SlickException {
		float pixels = SPEED * delta; // distance moved in pixels.
		boolean moved = true;

		// Sets player to face in direction of movement.
		if (xDir == 1)
			isFacingLeft = false;
		else if (xDir == -1)
			isFacingLeft = true;

		// Player position after move.
		float xPosNew = xPos + (xDir * pixels);
		float yPosNew = yPos + (yDir * pixels);

		// If player can't move to new coordinate..
		if (world.blocked(xPosNew, yPosNew)) {
			// Then player tries to move horizontally only..
			if (!world.blocked(xPosNew, yPos))
				xPos = xPosNew;
			// If he can't, then he tries to move vertically only.
			else if (!world.blocked(xPos, yPosNew))
				yPos = yPosNew;
			else
				moved = false;
		}
		// If player can move to new coordinate and he can move horizontally
		// and/or vertically...
		else if (!world.blocked(xPosNew, yPos) || !world.blocked(xPos, yPosNew)) {
			// Then he moves to new coordinate.
			xPos = xPosNew;
			yPos = yPosNew;
		} else
			moved = false;

		return moved;
	}
}
