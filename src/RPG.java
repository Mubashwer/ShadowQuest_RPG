/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Main class for the Role-Playing Game engine. Handles initialisation, input
 * and rendering.
 */
public class RPG extends BasicGame {

	private World world;

	/** The location of assets directory. */
	public static final String ASSETS_LOCATION = "assets";
	/** The location of the map file within assets directory. */
	public static final String MAP_LOCATION = "/map.tmx";
	/** The property which determines if a tile in map is blocked or not. */
	public static final String MAP_BLOCK_PROPERTY = "block";
	/** Screen width, in pixels. */
	public static final int screenwidth = 800;
	/** Screen height, in pixels. */
	public static final int screenheight = 600;

	/**
	 * Create a new RPG object.
	 */
	public RPG() {
		super("RPG Game Engine");
	}

	/**
	 * Initialise the game state.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		world = new World();
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// Get data about the current input (keyboard state).
		Input input = gc.getInput();

		float xDir = 0;
		float yDir = 0;
		boolean mousePressed = false;

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			mousePressed = true;

		int mouseScreenX = input.getMouseX();
		int mouseScreenY = input.getMouseY();

		// Update the player's movement direction based on keyboard presses.
		if (input.isKeyDown(Input.KEY_DOWN))
			yDir += 1;
		if (input.isKeyDown(Input.KEY_UP))
			yDir -= 1;
		if (input.isKeyDown(Input.KEY_LEFT))
			xDir -= 1;
		if (input.isKeyDown(Input.KEY_RIGHT))
			xDir += 1;
		boolean arrowKeyPressed = (xDir != 0) || (yDir != 0);

		// Let World.update decide what to do with this data.
		world.update(xDir, yDir, delta, mousePressed, mouseScreenX,
				mouseScreenY, arrowKeyPressed);
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Let World.render handle the rendering.
		world.render(g);
	}

	/**
	 * Start-up method. Creates the game and runs it.
	 * 
	 * @param args
	 *            Command-line arguments (ignored).
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new RPG());
		// setShowFPS(true), to show frames-per-second.
		app.setShowFPS(false);
		app.setDisplayMode(screenwidth, screenheight, false);
		app.start();
	}
}
