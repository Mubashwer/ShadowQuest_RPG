import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Represents the camera that controls our viewpoint.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 */
public class Camera {

	/** The unit this camera is following */
	private Unit unitFollow;
	/** Screen width, in pixels. */
	public final int screenwidth;
	/** Screen height, in pixels. */
	public final int screenheight;

	/* The camera's top-left position in the world, in x and y coordinates. */
	private int xPos;
	private int yPos;

	/* The camera's farthest possible top-left position in map. */
	public final int maxTopLeftX;
	public final int maxTopLeftY;

	/**
	 * Create a new World object.
	 * 
	 * @param unit
	 *            The unit this camera is to follow.
	 * @param map
	 *            The game map.
	 * @param screenwidth
	 *            The width of camera screen.
	 * @param screenheight
	 *            The height of camera screen.
	 */
	public Camera(Unit unit, TiledMap map, int screenwidth, int screenheight)
			throws SlickException {
		this.screenwidth = screenwidth;
		this.screenheight = screenheight;
		followUnit(unit);

		xPos = (int) unitFollow.getxPos() - screenwidth / 2;
		yPos = (int) unitFollow.getyPos() - screenheight / 2;
		maxTopLeftX = (map.getWidth() * map.getTileWidth()) - screenwidth - 1;
		maxTopLeftY = (map.getHeight() * map.getTileHeight()) - screenheight
				- 1;
	}

	/**
	 * It returns the x coordinate of the unit this camera is following.
	 * 
	 * @return player xPos
	 */
	public float getxPos() {
		return unitFollow.getxPos();
	}

	/**
	 * It returns the y coordinate of the unit this camera is following.
	 * 
	 * @return player yPos
	 */
	public float getyPos() {
		return unitFollow.getyPos();
	}

	/**
	 * Update the game camera to recentre it's viewpoint around the player
	 */
	public void update() throws SlickException {
		int xPosNew = (int) unitFollow.getxPos() - screenwidth / 2;
		int yPosNew = (int) unitFollow.getyPos() - screenheight / 2;

		// Update camera positions as long as it is inside the map.
		if (xPosNew >= 0 && xPosNew <= maxTopLeftX)
			xPos = xPosNew;
		if (yPosNew >= 0 && yPosNew <= maxTopLeftY)
			yPos = yPosNew;
	}

	/**
	 * Returns the top-left x coordinate of camera in map.
	 * 
	 * @return top-left xPos of camera
	 */
	public int getMinX() {
		return xPos;
	}

	/**
	 * Returns the bottom-right x coordinate of camera in map.
	 * 
	 * @return bottom-right xPos of camera
	 */
	public int getMaxX() {
		return xPos + screenwidth - 1;
	}

	/**
	 * Returns the minimum top-left y coordinate of camera in map.
	 * 
	 * @return top-left yPos of camera
	 */
	public int getMinY() {
		return yPos;
	}

	/**
	 * Returns the bottom-right y coordinate of camera in map.
	 * 
	 * @return bottom-right yPos of camera
	 */
	public int getMaxY() {
		return yPos + screenheight - 1;
	}

	/**
	 * Tells the camera to follow a given unit.
	 * 
	 * @param unit
	 *            unit the camera is to follow.
	 */
	public void followUnit(Unit unit) throws SlickException {
		unitFollow = unit;
	}
}