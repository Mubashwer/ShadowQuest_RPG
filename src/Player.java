/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	/** Starting x coordinate of the player in the map */
	public static final float PLAYER_START_X = 756;
	/** Starting y coordinate of the player in the map */
	public static final float PLAYER_START_Y = 684;
	/** Speed of player in pixels per millisecond. */
	public static final float SPEED = 0.3F;
	/** The location of the player image within assets directory. */
	public static final String PLAYER_IMAGE_LOCATION = "/units/player.png";
	/** x coordinate of player in map. */
	private float xPos;
	/** y coordinate of player in map. */
	private float yPos;
	/** It refers to the image of player which is drawn. */
	private Image playerImg;
	/** It refers to the image of player facing right. */
	private Image playerImgRight;
	/** It refers to the image of player facing left. */
	private Image playerImgLeft;

	/** Create a new Player object. */
	public Player() throws SlickException {
		this.xPos = PLAYER_START_X;
		this.yPos = PLAYER_START_Y;
		// Original image of player faces right.
		playerImgRight = new Image(RPG.ASSETS_LOCATION + PLAYER_IMAGE_LOCATION);
		playerImgLeft = playerImgRight.getFlippedCopy(true, false);
		playerImg = playerImgRight;
	}
	/**
	 * It gets width of player
	 * 
	 * @return width of player image
	 * 
	 */
	int getWidth() {
		return playerImg.getWidth();
	}
	/**
	 * It gets height of player
	 * 
	 * @return height of player image
	 * 
	 */
	int getHeight() {
		return playerImg.getWidth();
	}
	/**
	 * It gets x coordinate of player
	 * 
	 * @return x coordinate of player
	 * 
	 */
	public float getxPos() {
		return xPos;
	}

	/**
	 * It gets y coordinate of player
	 * 
	 * @return y coordinate of player
	 * 
	 */
	public float getyPos() {
		return yPos;
	}

	/**
	 * It draws the player in screen with respect to camera.
	 * 
	 * @param xPosCam
	 *            x coordinate of camera to draw player
	 * @param yPosCam
	 *            y coordinate of camera to draw player
	 */
	public void draw(int xPosCam, int yPosCam) throws SlickException {
		playerImg.drawCentered((int)xPos - xPosCam, (int)yPos - yPosCam);
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
	 */
	public void move(World world, float xDir, float yDir, int delta)
			throws SlickException {
		float pixels = SPEED * delta; // distance moved in pixels.

		// Sets player to face in direction of movement.
		if (xDir == 1)
			playerImg = playerImgRight;
		else if (xDir == -1)
			playerImg = playerImgLeft;

		// Player position after move.
		float xPosNew = xPos + (xDir * pixels);
		float yPosNew = yPos + (yDir * pixels);

		// Checks if player can move to new position
		if (!world.terrainBlocked(xPosNew, yPosNew)) {
			xPos = xPosNew;
			yPos = yPosNew;
		} // Checks if player can only move in x-direction
		else if (!world.terrainBlocked(xPosNew, yPos)) {
			xPos = xPosNew;
		} // Checks if player can only move in y-direction
		else if (!world.terrainBlocked(xPos, yPosNew)) {
			yPos = yPosNew;
		}
	}
}
