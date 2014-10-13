import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * It is a Villager class which is designed to be extended by specific
 * villagers.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public abstract class Villager extends Unit {

	public static final int DIALOGUE_DURATION = 4000;
	/** Flag for interaction */
	protected boolean interacted;
	protected String dialogue;
	/** The timer which determines how long a dialogue should be shown. */
	protected int dialogueTimer;
	protected Sound interactSound;

	/**
	 * Create a new Villager object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @param image
	 *            Sprite of unit.
	 */
	public Villager(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		dialogueTimer = 0;
		interacted = false;
	}

	/**
	 * All villagers must be able to interact with player or his inventory
	 * somehow.
	 */
	public abstract void interact(List<Item> inventory, Player player);

	/**
	 * This determines when a villager is able to interact with the player and
	 * it handles the duration of dialogue.
	 */
	@Override
	public void tryToInteract(List<Item> inventory, int delta, Player player,
			boolean interactPressed) {
		/*
		 * If a player successfully interacted with villager, dialogue timer
		 * starts ticking.
		 */
		if (dialogueTimer >= 0)
			dialogueTimer -= delta;
		/*
		 * If player is beyond 50 pixels from the villager than the dialogue
		 * ends and no interaction occurs.
		 */

		if (getDistance(player) > INTERACT_RANGE) {
			dialogueTimer = 0;
			interacted = false;
			return;
		}
		/*
		 * If 'T' is not pressed or player has already interacted in the last 4
		 * seconds, then no further interaction occurs.
		 */
		if (interacted == true || interactPressed == false) {
			return;
		}
		// Player interacts with villager and timer is set.
		dialogueTimer = DIALOGUE_DURATION;
		interact(inventory, player);
		interacted = true;
	}

	@Override
	public void render(Graphics g, int camMinX, int camMinY) {
		super.render(g, camMinX, camMinY);
		// If a player interacted with villager then dialogue is rendered.
		if (dialogueTimer > 0)
			renderDialogue(g, camMinX, camMinY);
	}

	/**
	 * This renders villager's dialogue on screen.
	 * 
	 * @param g
	 *            Graphics object.
	 * @param camMinX
	 *            Top-Left x-coordinate of camera.
	 * @param camMinY
	 *            Top-Left y-coordinate of camera.
	 */
	public void renderDialogue(Graphics g, int camMinX, int camMinY) {
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp

		// Variables for layout
		int barX, barY; // Coordinates to draw rectangles
		int barWidth, barHeight; // Size of rectangle to draw

		barHeight = 20;
		barWidth = RPG.getTextWidth(dialogue) + 6;
		barX = (int) xPos - (barWidth / 2) - camMinX;
		barY = (int) yPos - (image.getHeight() / 2) - barHeight - 20 - camMinY;

		g.setColor(BAR_BG);
		g.fillRect(barX, barY, barWidth, barHeight);
		g.setColor(VALUE);
		g.drawString(dialogue, barX + 3, barY);
	}
}
