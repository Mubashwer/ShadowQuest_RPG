import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Main class for the Role-Playing Game engine. Handles initialisation, input
 * and rendering.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 */
public class RPG extends BasicGame {

	private World world;
	public static final String ASSETS_LOCATION = "assets/";
	public static final String MAP_LOCATION = ASSETS_LOCATION + "map.tmx";
	public static final String FONT_LOCATION = ASSETS_LOCATION
			+ "DejaVuSans-Bold.ttf";
	public static final String THEME_LOCATION = ASSETS_LOCATION
			+ "bg_theme.ogg";
	public static final int FONT_SIZE = 15;
	/** The property which determines if a tile in map is blocked or not. */
	public static final String MAP_BLOCK_PROPERTY = "block";
	/** THe height of render player status panel. */
	public static final int PANEL_HEIGHT = 70;
	public static final String PANEL_IMAGE_LOCATION = ASSETS_LOCATION
			+ "panel.png";
	/** Screen width, in pixels. */
	public static final int SCREEN_WIDTH = 1280;
	/** Screen height, in pixels. */
	public static final int SCREEN_HEIGHT = 720;
	/** Background theme volume */
	public static final float VOLUME = 0.03F;
	/** Game font */
	private static Font font;

	/**
	 * Create a new RPG object.
	 */
	public RPG() {
		super("Shadow Quest");
	}

	/**
	 * Initialise the game state.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setMusicVolume(VOLUME);
		gc.setSoundVolume(VOLUME * 6);
		font = FontLoader.loadFont(RPG.FONT_LOCATION, RPG.FONT_SIZE);
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
		boolean interactPressed = false;
		boolean attackPressed = false;

		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			mousePressed = true;
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				|| input.isKeyDown(Input.KEY_T))
			interactPressed = true;
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				|| input.isKeyDown(Input.KEY_A))
			attackPressed = true;

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
				mouseScreenY, arrowKeyPressed, interactPressed, attackPressed);
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
		g.setFont(font);
		world.render(g);
	}

	/**
	 * It gets the width of a text in pixels.
	 * 
	 * @param text
	 *            The string.
	 * @return width of the string
	 */
	public static int getTextWidth(String text) {
		return font.getWidth(text);
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
		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.start();
	}
}
