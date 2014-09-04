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
	/** Screen width, in pixels. */
	public final int screenwidth;
	/** Screen height, in pixels. */
	public final int screenheight;

	/** The camera's top-left position in the world, in x and y coordinates. */
	private int xPos;
	private int yPos;
	
	/** The camera's farthest possible top-left position in map. */
	private final int xPosMax;
	private final int yPosMax;

	/**
	 * Create a new World object.
	 */
	public Camera(Player player, SimpleMap map, int screenwidth, int screenheight)
			throws SlickException {
		this.screenwidth = screenwidth;
		this.screenheight = screenheight;
		followUnit(player);
		
		xPos = (int) unitFollow.getxPos() - screenwidth / 2;
		yPos = (int) unitFollow.getyPos() - screenheight / 2;
	
		xPosMax = (map.getWidthInTiles() * map.getTileWidth()) - screenwidth - 1;
		yPosMax = (map.getHeightInTiles() * map.getTileHeight()) - screenheight - 1;
	}
	
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
	 * Update the game camera to recentre it's viewpoint around the player
	 */
	public void update() throws SlickException {
		int xPosNew = (int) unitFollow.getxPos() - screenwidth / 2;
		int yPosNew = (int) unitFollow.getyPos() - screenheight / 2;
		
		// Update camera positions as long as it is inside the map.
		if (xPosNew >= getMinX() && xPosNew <= getMaxX())
			xPos = xPosNew;
		if (yPosNew >= getMinY() && yPosNew <= getMaxY())
			yPos = yPosNew;
	}

	/**
	 * Returns the minimum top-left x coordinate of camera in map.
	 */
	public int getMinX() {
		return 0;
	}

	/**
	 * Returns the maximum top-left x coordinate of camera in map.
	 */
	public int getMaxX() {
		return xPosMax;
	}

	/**
	 * Returns the minimum top-left y coordinate of camera in map.
	 */
	public int getMinY() {
		return 0;
	}

	/**
	 * Returns the maximum top-left y coordinate of camera in map.
	 */
	public int getMaxY() {
		return yPosMax;
	}

	/**
	 * Tells the camera to follow a given unit.
	 */
	public void followUnit(Player unit) throws SlickException {
		unitFollow = unit;
	}
}