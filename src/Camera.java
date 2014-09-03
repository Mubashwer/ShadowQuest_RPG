/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.SlickException;

/**
 * Represents the camera that controls our viewpoint.
 */
public class Camera {

	/** The unit this camera is following */
	private Player unitFollow;
	/** The game map */
	private SimpleMap map;
	/** Screen width, in pixels. */
	public final int screenwidth;
	/** Screen height, in pixels. */
	public final int screenheight;

	/** The camera's position in the world, in x and y coordinates. */
	private int xPos;
	private int yPos;

	/**
	 * It returns the x coordinate of camera.
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * It returns the y coordinate of camera.
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * Create a new World object.
	 */
	public Camera(Player player, SimpleMap map, int screenwidth, int screenheight)
			throws SlickException {
		this.screenwidth = screenwidth;
		this.screenheight = screenheight;
		this.map = map;
		followUnit(player);
		xPos = (int) unitFollow.getxPos();
		yPos = (int) unitFollow.getyPos();
		update();
	}

	/**
	 * Update the game camera to recentre it's viewpoint around the player
	 */
	public void update() throws SlickException {
		int xPosNew = (int) unitFollow.getxPos() - screenwidth / 2;
		int yPosNew = (int) unitFollow.getyPos() - screenheight / 2;
		// update camera positions
		if (xPosNew >= getMinX() && xPosNew <= getMaxX())
			xPos = xPosNew;
		if (yPosNew >= getMinY() && yPosNew <= getMaxY())
			yPos = yPosNew;
	}

	/**
	 * Returns the minimum x value on screen
	 */
	public int getMinX() {
		return 0;
	}

	/**
	 * Returns the maximum x value on screen
	 */
	public int getMaxX() {
		return (map.getWidthInTiles() * map.getTileWidth()) - screenwidth - 1;
	}

	/**
	 * Returns the minimum y value on screen
	 */
	public int getMinY() {
		return 0;
	}

	/**
	 * Returns the maximum y value on screen
	 */
	public int getMaxY() {
		return (map.getHeightInTiles() * map.getTileHeight()) - screenheight - 1;
	}

	/**
	 * Tells the camera to follow a given unit.
	 */
	public void followUnit(Player unit) throws SlickException {
		unitFollow = unit;
	}
}