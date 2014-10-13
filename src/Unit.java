import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * The class for the characters in the game. It handles damage, stats, death
 * etc.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public abstract class Unit extends Entity {
	public static final String UNITS_LOCATION = RPG.ASSETS_LOCATION + "units/";
	/** Health of unit. */
	protected int hp;
	/** Maximum health of unit. */
	protected int maxHp;
	/** Maximum damage unit can inflict on another unit */
	protected int maxDamage;
	/** Minimum waiting time between attacks in milliseconds. */
	protected int cooldown;
	/** Timer for cooldown in milliseconds. */
	protected int cooldownTimer;
	/** It determines the whether unit is facing left or right. */
	protected boolean isFacingLeft;
	/** Speed of unit in pixels per millisecond. */
	protected float speed;
	/** It determines whether the player is alive or not. */
	protected boolean deceased;

	/**
	 * Create a new Unit object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit in map.
	 * @param yPos
	 *            Y-coordinate of unit in map.
	 * @param image
	 *            Sprite of unit.
	 */
	public Unit(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		deceased = false;
		isFacingLeft = false;
		cooldownTimer = 0;
	}

	/**
	 * Tries to move and/or attacks main player.
	 * 
	 * @param world
	 *            The game world.
	 * @param delta
	 *            Milliseconds per frame.
	 * @param player
	 *            The main player.
	 */
	public void move(World world, int delta, Player player) {
		// Designed to be overridden by movable units.
	}

	/**
	 * It handles how and when to interact with main player.
	 * 
	 * @param Inventory
	 *            The player's inventory.
	 * @param delta
	 *            Millisecond per frame.
	 * @param player
	 *            The main player.
	 * @param interactPressed
	 *            Flag for interaction.
	 */
	public void tryToInteract(List<Item> Inventory, int delta, Player player,
			boolean interactPressed) {
		// Designed to be overridden by Villager units.
	}

	/**
	 * It reduces hp by given value.
	 * 
	 * @param damage
	 *            The reduction in hp.
	 */
	public void getsDamaged(int damage) {
		// Designed to be overriden by monster units.
	};

	/**
	 * It gets the maximum damage the unit can inflict on other units.
	 * 
	 * @return maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * It changes the maximum damage of unit.
	 * 
	 * @param maxDamage
	 *            maximum damage stat of unit.
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	/**
	 * It checks whether unit is dead or alive.
	 * 
	 * @return True if unit is dead.
	 */
	public boolean isDeceased() {
		return deceased;
	}

	/**
	 * Kills unit.
	 */
	public void die() {
		deceased = true;
	}

	/**
	 * It gets the hp of unit.
	 * 
	 * @return hp.
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * It changes hp of unit.
	 * 
	 * @param hp
	 *            life of unit.
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * It gets the maximum hp of unit.
	 * 
	 * @return maxHp.
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * It changes maximum hp of unit.
	 * 
	 * @param maxHp
	 *            maximum hp
	 */
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * It gets the cooldown rate of unit.
	 * 
	 * @return cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * It changes the cooldown rate of unit.
	 * 
	 * @param cooldown
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public void render(Graphics g, int camMinX, int camMinY) {
		Image image = this.image;
		if (isFacingLeft == true)
			image = image.getFlippedCopy(true, false);
		// Draw player sprite
		image.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);

		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red, transp
		// Variables for layout
		int barX, barY; // Coordinates to draw rectangles
		int barWidth, barHeight; // Size of rectangle to draw
		int hpBarWidth; // Size of red (HP) rectangle
		float healthPercent; // Player's health, as a percentage

		barWidth = image.getWidth();
		barHeight = 20;

		if (RPG.getTextWidth(name) + 6 > image.getWidth()) {
			barWidth = RPG.getTextWidth(name) + 6;
		} else {
			barWidth = image.getWidth();
		}

		barX = (int) xPos - barWidth / 2 - camMinX;
		barY = (int) yPos - image.getHeight() / 2 - barHeight - camMinY;
		healthPercent = (float) hp / maxHp;
		hpBarWidth = (int) (barWidth * healthPercent);
		// Draw health bar and its background.
		g.setColor(BAR_BG);
		g.fillRect(barX, barY, barWidth, barHeight);
		g.setColor(BAR);
		g.fillRect(barX, barY, hpBarWidth, barHeight);
		g.setColor(VALUE);
		g.drawString(name, barX + 3, barY);

	}

	/**
	 * Moves player if path is not blocked.
	 * 
	 * @param world
	 *            It represents the entire game where the player exists.
	 * @param xDir
	 *            The 's movement in the x axis (-1, 0 or 1).
	 * @param yDir
	 *            The 's movement in the y axis (-1, 0 or 1).
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 * @return True if player has moved.
	 */
	public boolean update(World world, int delta, float xDir, float yDir,
			float xPosNew, float yPosNew) {

		boolean moved = true;

		// Sets player to face in direction of movement.
		if (xDir == 1)
			isFacingLeft = false;
		else if (xDir == -1)
			isFacingLeft = true;

		// If player can't move to new coordinate..
		if (world.blocked(xPosNew, yPosNew)) {
			// Then player tries to move horizontally only..
			if (!world.blocked(xPosNew, yPos))
				xPos = xPosNew;
			// If he can't, then he tries to move vertically only.
			else if (!world.blocked(xPos, yPosNew))
				yPos = yPosNew;
			else
				moved = false;
		}
		// If player can move to new coordinate and he can move horizontally
		// and/or vertically...
		else if (!world.blocked(xPosNew, yPos) || !world.blocked(xPos, yPosNew)) {
			// Then he moves to new coordinate.
			xPos = xPosNew;
			yPos = yPosNew;
		} else
			moved = false;

		return moved;
	}

	/**
	 * It gets a random number between the given range.
	 * 
	 * @param min
	 *            The minimum number.
	 * @param max
	 *            The maximum number.
	 * @return A random number.
	 */
	public static int getRandom(int min, int max) {
		int random = min + (int) (Math.random() * (max - min + 1));
		return random;
	}
}
