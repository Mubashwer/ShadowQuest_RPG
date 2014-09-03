/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {

	/** The game map */
	private SimpleMap map;
	/** Screen width in tiles */
	private final int screenWidthTiles;
	/** Screen height in tiles */
	private final int screenHeightTiles;
	/** The main unit of the game */
	private Player player;
	/** The camera which follows the player */
	private Camera camera;
	/** A series of steps from the starting location to the target location. */
	private Path path;
	/** It is the index a path which contains x and y positions in tiles. */
	private int step;

	/**
	 * Create a new World object.
	 * */
	public World() throws SlickException {
		map = new SimpleMap(new TiledMap(
				RPG.ASSETS_LOCATION + RPG.MAP_LOCATION, RPG.ASSETS_LOCATION),
				"block");
		player = new Player();
		camera = new Camera(player, map, RPG.screenwidth, RPG.screenheight);

		// The screen size in tiles
		screenWidthTiles = (RPG.screenwidth / getTileWidth()) + 2;
		screenHeightTiles = (RPG.screenheight / getTileHeight()) + 2;

		path = null;
		step = 0;
	}

	/**
	 * It returns width of map in number of tiles.
	 */
	public int getWidth() {
		return map.getWidthInTiles();
	}

	/**
	 * It returns height of map in number of tiles.
	 */
	public int getHeight() {
		return map.getHeightInTiles();
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
	public void update(float xDir, float yDir, int delta, boolean mousePressed,
			int mouseScreenX, int mouseScreenY, boolean autoMove)
			throws SlickException {

		AStarPathFinder pathFinder = new AStarPathFinder(map, getWidth()
				* getHeight(), false);

		// Player position in tiles.
		int xPosTile = (int) (player.getxPos() / getTileWidth());
		int yPosTile = (int) (player.getyPos() / getTileHeight());

		// User pressed an arrow key, so no auto-play and path is reset.
		if (autoMove == false) {
			this.path = null;
			step = 0;
		}
		// User pressed mouse button only, an attempt is made to find path.
		else if (mousePressed == true) {
			int mouseTileX = (int) ((mouseScreenX + camera.getxPos()) / getTileWidth());
			int mouseTileY = (int) ((mouseScreenY + camera.getyPos()) / getTileHeight());

			/*
			 * It finds path from the player's position to the position where
			 * mouse was clicked and produces a series of steps.
			 */
			Path path = pathFinder.findPath(player, xPosTile, yPosTile,
					mouseTileX, mouseTileY);
			this.path = path;
			step = 0;
			/* If path could not be found */
			if (path == null) {
				this.path = null;
				autoMove = false;
			}
		}
		// If the player is in the middle of an auto-move when path was
		// found in a previous update.
		else if (path != null) {

			// Direction is towards the next step.
			if (xPosTile > path.getX(step))
				xDir--;
			if (xPosTile < path.getX(step))
				xDir++;
			if (yPosTile < path.getY(step))
				yDir++;
			if (yPosTile > path.getY(step))
				yDir--;

			/*
			 * When the player reaches the step, the player should try to reach
			 * the next step during next update call.
			 */
			if (xPosTile == path.getX(step) && yPosTile == path.getY(step))
				step++;

			// When player finally reaches the target...
			if (step >= path.getLength()) {
				path = null;
				step = 0;
			}
		}
		// If player gets trapped due to any error, auto-move is cancelled.
		if (player.update(this, xDir, yDir, delta) == false) {
			path = null;
			step = 0;
		}
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
	 * Checks whether given location in map is blocked or not.
	 * 
	 * @param xPos
	 *            x coordinate of unit in map.
	 * @param yPos
	 *            y coordinate of unit in map.
	 * @return boolean value true if tile is blocked otherwise false
	 */
	public boolean blocked(float xPos, float yPos) {
		// Checks if new position is inside map.
		if (xPos < 0 || xPos >= getWidth() * getTileWidth()) {
			return true;
		}
		if (yPos < 0 || yPos >= getHeight() * getTileHeight()) {
			return true;
		}
		int xTile = (int) (xPos / getTileWidth());
		int yTile = (int) (yPos / getTileHeight());

		// checks for terrain blocking.
		return (map.blocked(null, xTile, yTile));
	}
}
