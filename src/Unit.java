import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
	


	public Unit(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		deceased = false;
		isFacingLeft = false;
		cooldownTimer = 0;
	}
	
	public void move(World world, int delta, Player player) {
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean isDeceased() {
		return deceased;
	}
	
	public void die() {
		deceased = true;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getMaxDamage() {
		return maxDamage;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCooldown() {
		return cooldown;
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public float getSpeed() {
		return speed;
	}

	public void getsDamaged(int damage) {
		
	};

	
	public void render(Graphics g, int camMinX, int camMinY) {
		Image image = this.image;
		if (isFacingLeft == true)
			image = image.getFlippedCopy(true, false);

		image.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);
		
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red, transp
		// Variables for layout
		int bar_x, bar_y; // Coordinates to draw rectangles
		int bar_width, bar_height; // Size of rectangle to draw
		int hp_bar_width; // Size of red (HP) rectangle
		float health_percent; // Player's health, as a percentage

		
		bar_width = image.getWidth();
		bar_height = 20;

		

		if (RPG.getFontWidth(name) + 6 > image.getWidth()) {
			bar_width = RPG.getFontWidth(name) + 6;
		}
		else {
			bar_width = image.getWidth();
		}
		
		bar_x = (int) xPos - bar_width / 2 - camMinX;;
		bar_y = (int) yPos - image.getHeight() / 2 - bar_height - camMinY;
		health_percent = (float) hp /maxHp;
		hp_bar_width = (int) (bar_width * health_percent);
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(name, bar_x + 3, bar_y);
		
		
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

	public static int getRandom(int min, int max) {
		int random = min + (int) (Math.random() * (max - min + 1));
		return random;
	}

	public void tryToInteract(List<Item> Inventory, int delta, Player player, boolean interactPressed) {
	}
	

}
