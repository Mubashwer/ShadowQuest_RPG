/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {

	private TiledMap map;
	private Player mainUnit;
	private Camera camera;

	/** Create a new World object. */
	public World() throws SlickException {
		map = new TiledMap("assets/map.tmx", "assets/");
		mainUnit = new Player();
		camera = new Camera(mainUnit, RPG.screenwidth, RPG.screenheight);
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param dir_x
	 *            The player's movement in the x axis (-1, 0 or 1).
	 * @param dir_y
	 *            The player's movement in the y axis (-1, 0 or 1).
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	public void update(double dir_x, double dir_y, int delta)
			throws SlickException {
		mainUnit.move((int)dir_x, (int)dir_y, delta);
		camera.update();
		
		
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException {
		// TODO: Fill in
		int screenWidthTile = (RPG.screenwidth / map.getTileWidth()) + 2; // 13 tiles
		int screenHeightTile = (RPG.screenheight / map.getTileHeight())+ 2; // 10 tiles
		
		int xTile = camera.getxPos() / map.getTileWidth(); // camera position in tiles
		int xOffset = camera.getxPos() % map.getTileWidth(); // pixels offset
		int yTile = camera.getyPos() / map.getTileHeight();
		int yOffset = camera.getyPos() % map.getTileHeight();
		

		map.render(-xOffset,-yOffset, xTile, yTile, screenWidthTile,screenHeightTile); //render map
		mainUnit.draw(RPG.screenwidth/2, RPG.screenheight/2); // render player
	}
}
