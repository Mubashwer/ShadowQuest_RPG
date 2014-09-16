import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

public abstract class Villager extends Unit {

	public static final int DIALOGUE_DURATION = 4000;
	protected boolean interacted;
	protected String dialogue;
	protected int dialogueTimer;
	protected Sound interactSound;

	public Villager(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		dialogueTimer = 0;
		dialogue = "...";
		interacted = false;
	}

	public abstract void interact(List<Item> inventory, Player player);

	@Override
	public void tryToInteract(List<Item> inventory, int delta, Player player,
			boolean interactPressed) {
		if (dialogueTimer >= 0)
			dialogueTimer -= delta;

		if (getDistance(player) > INTERACT_RANGE) {
			dialogueTimer = 0;
			interacted = false;
			return;
		}
		if (interacted == true || interactPressed == false) {
			return;
		}
		dialogueTimer = DIALOGUE_DURATION;
		interact(inventory, player);
		interacted = true;
	}

	@Override
	public void render(Graphics g, int camMinX, int camMinY) {
		super.render(g, camMinX, camMinY);
		if (dialogueTimer > 0)
			renderDialogue(g, camMinX, camMinY);
	}

	public void renderDialogue(Graphics g, int camMinX, int camMinY) {
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp

		// Variables for layout
		int barX, barY; // Coordinates to draw rectangles
		int barWidth, barHeight; // Size of rectangle to draw

		barHeight = 20;
		barWidth = RPG.getFontWidth(dialogue) + 6;
		barX = (int) xPos - (barWidth / 2) - camMinX;
		;
		barY = (int) yPos - (image.getHeight() / 2) - barHeight - 20 - camMinY;

		g.setColor(BAR_BG);
		g.fillRect(barX, barY, barWidth, barHeight);
		g.setColor(VALUE);
		g.drawString(dialogue, barX + 3, barY);

	}
}
