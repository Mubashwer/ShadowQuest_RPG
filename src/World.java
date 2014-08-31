/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {

	/** The game map */
	private TiledMap map;
	/** Screen width in tiles */
	private final int screenWidthTiles;
	/** Screen height in tiles */
	private final int screenHeightTiles;
	/** The main unit of the game */
	private Player player;
	/** The camera which follows the player */
	private Camera camera;

	/**
	 * Create a new World object.
	 * */
	public World() throws SlickException {
		map = new TiledMap(RPG.ASSETS_LOCATION + RPG.MAP_LOCATION,
				RPG.ASSETS_LOCATION);
		player = new Player();
		camera = new Camera(player, map, RPG.screenwidth, RPG.screenheight);
		// The screen size in tiles 
		screenWidthTiles = (RPG.screenwidth / getTileWidth()) + 2;
		screenHeightTiles = (RPG.screenheight / getTileHeight()) + 2;
	}

	/**
	 * It returns width of map in number of tiles.
	 */
	public int getWidth() {
		return map.getWidth();
	}

	/**
	 * It returns height of map in number of tiles.
	 */
	public int getHeight() {
		return map.getHeight();
	}

	/**
	 * It returns width of a tile in map.
	 */
	public int getTileWidth() {
		return map.getTileWidth();
	}

	/**
	 * It returns height of a tile in map.
	 */
	public int getTileHeight() {
		return map.getTileHeight();
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param dir_x
	 *            The 's movement in the x axis (-1, 0 or 1).
	 * @param dir_y
	 *            The 's movement in the y axis (-1, 0 or 1).
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	public void update(float xDir, float yDir, int delta) throws SlickException {
		player.update(this, xDir, yDir, delta);
		camera.update();
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException {
		// Camera position in tiles
		int xTile = camera.getxPos() / getTileWidth();
		int yTile = camera.getyPos() / getTileHeight();

		// Pixels offset after camera is positioned in tiles
		int xOffset = camera.getxPos() % getTileWidth();
		int yOffset = camera.getyPos() % getTileHeight();

		map.render(-xOffset, -yOffset, xTile, yTile, screenWidthTiles,
				screenHeightTiles);
		player.draw(camera.getxPos(), camera.getyPos());
	}

	/**
	 * Checks whether given location in map is blocked or not. It also checks 4
	 * corner pixels depending on player width and height.
	 * 
	 * @param xPos
	 *            x coordinate of unit in map.
	 * @param yPos
	 *            y coordinate of unit in map.
	 * @return boolean value true if terrain is blocked otherwise false
	 */
	public boolean terrainBlocked(Player unit, float xPos, float yPos) {
		/*
		 * It checks given position for terrain blocking and also checks terrain
		 * blocking for 4 corners of a rectangle formed with xPos and yPos as
		 * centre. The sides of the rectangle are 1/2 of the sides of the player
		 * image.
		 */
		float yPosTop = yPos - unit.getHeight() / 4;
		float yPosBottom = yPos + unit.getHeight() / 4;
		float xPosLeft = xPos - unit.getWidth() / 4;
		float xPosRight = xPos + unit.getWidth() / 4;

		return (terrainBlocked(xPos, yPos)
				|| terrainBlocked(xPosRight, yPosTop)
				|| terrainBlocked(xPosRight, yPosBottom)
				|| terrainBlocked(xPosLeft, yPosTop) || terrainBlocked(
					xPosLeft, yPosBottom));
	}

	/**
	 * Checks whether given location in map is blocked or not.
	 * 
	 * @param xPos
	 *            x coordinate of unit in map.
	 * @param yPos
	 *            y coordinate of unit in map.
	 * @return boolean value true if terrain is blocked otherwise false
	 */
	public boolean terrainBlocked(float xPos, float yPos) {
		// Checks if new position is inside map.
		if (xPos < 0 || xPos >= getWidth() * getTileWidth()) {
			return true;
		}
		if (yPos < 0 || yPos >= getHeight() * getTileHeight()) {
			return true;
		}
		// Player position in tiles.
		int xTile = (int) (xPos / getTileWidth());
		int yTile = (int) (yPos / getTileHeight());

		int tileId = map.getTileId(xTile, yTile, 0);
		String block = map.getTileProperty(tileId, "block", "0");
		return block.equals("1");
	}
}
