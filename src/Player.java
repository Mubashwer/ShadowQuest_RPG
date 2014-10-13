import java.util.List;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.pathfinding.Mover;

/**
 * Represents the main player of the game. It handles movement and rendering.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Player extends Unit implements Mover {

	/** Starting x coordinate of the player in the map */
	public static final float PLAYER_START_X = 756;
	/** Starting y coordinate of the player in the map */
	public static final float PLAYER_START_Y = 684;
	/** Respawn x coordinate of the player in the map */
	public static final float PLAYER_RESPAWN_X = 738;
	/** Respawn y coordinate of the player in the map */
	public static final float PLAYER_RESPAWN_Y = 549;
	/** The location of the player image. */
	public static final String PLAYER_IMAGE_LOCATION = UNITS_LOCATION
			+ "/player.png";
	/** Speed of player in pixels per millisecond. */
	public static final float SPEED = 0.25F;
	/* Unit Stats. */
	public static final int HEALTH = 100;
	public static final int DAMAGE = 26;
	public static final int COOLDOWN = 600;

	public static final String ATTACK_SOUND_LOCATION = UNITS_LOCATION
			+ "melee_woosh.ogg";
	public static final String RESPAWN_SOUND_LOCATION = UNITS_LOCATION
			+ "revive.ogg";
	/* Inventory for items collected by player. */
	private List<Item> inventory;
	/* The sound produced when the player makes an attack. */
	private Sound attackSound;
	/* The sound produced when the player respawns. */
	private Sound respawnSound;

	/**
	 * Create a new Player object.
	 * 
	 * @param inventory
	 *            Inventory for items collected by player.
	 * @throws SlickException
	 */
	public Player(List<Item> inventory) throws SlickException {
		super(PLAYER_START_X, PLAYER_START_Y, new Image(PLAYER_IMAGE_LOCATION));
		speed = SPEED;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		attackSound = new Sound(ATTACK_SOUND_LOCATION);
		respawnSound = new Sound(RESPAWN_SOUND_LOCATION);
		this.inventory = inventory;
	}

	/**
	 * Create a new Player object.
	 * 
	 * @param xPos
	 *            X-coordinate of player in map.
	 * @param yPos
	 *            Y-coordinate of player in map.
	 * @param inventory
	 *            Inventory for items collected by player.
	 * @throws SlickException
	 */
	public Player(float xPos, float yPos, List<Item> inventory)
			throws SlickException {
		super(xPos, yPos, new Image(PLAYER_IMAGE_LOCATION));
		speed = SPEED;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		attackSound = new Sound(ATTACK_SOUND_LOCATION);
		respawnSound = new Sound(RESPAWN_SOUND_LOCATION);
		this.inventory = inventory;
	}

	/**
	 * Moves and interacts/attack other units.
	 * 
	 * @param world
	 *            The game world.
	 * @param delta
	 *            Milliseconds per frame.
	 * @param xDir
	 *            Horizontal direction
	 * @param yDir
	 *            Vertical direction
	 * @param units
	 *            Other units in the world.
	 * @param items
	 *            Items in the world.
	 * @param interactPressed
	 *            Flag for interaction.
	 * @param attackPressed
	 *            Flag for attack.
	 * @return True if player successfully moves.
	 */
	public boolean move(World world, int delta, float xDir, float yDir,
			List<Unit> units, List<Item> items, boolean interactPressed,
			boolean attackPressed) {
		// Amount of pixels to move.
		float pixels = speed * delta;

		// Player position after possible move.
		float xPosNew = xPos + (xDir * pixels);
		float yPosNew = yPos + (yDir * pixels);

		// If any item is within 50 pixels, it is collected by player.
		for (Item item : items)
			if (getDistance(item) <= INTERACT_RANGE) {
				item.interact(this);
				inventory.add(item);
			}

		for (Unit npc : units) {
			// Lets villagers handle how and when to interact.
			if (npc instanceof Villager)
				npc.tryToInteract(inventory, delta, this, interactPressed);
			/*
			 * If monsters are within range and attack button is pressed,
			 * monster gets damaged and cooldown starts.
			 */
			else if (getDistance(npc) <= INTERACT_RANGE && cooldownTimer <= 0
					&& attackPressed) {
				npc.getsDamaged(getRandom(0, maxDamage));
				cooldownTimer = cooldown;
				attackSound.play();
			}
		}
		// cooldownTimer updates until its 0..
		if (cooldownTimer >= 0)
			cooldownTimer -= delta;
		
		// Update player position if it is not totally blocked in map.
		return update(world, delta, xDir, yDir, xPosNew, yPosNew);
	}

	@Override
	public void render(Graphics g, int camMinX, int camMinY) {
		Image image = this.image;
		if (isFacingLeft == true)
			image = image.getFlippedCopy(true, false);

		image.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);
	}

	@Override
	public void getsDamaged(int maxDamage) {
		hp -= maxDamage;
		if (hp <= 0) {
			hp = 0;
			deceased = true;
		}
	}

	/**
	 * Player is resurrected after he is killed.
	 */
	public void respawn() {
		respawnSound.play();
		deceased = false;
		xPos = PLAYER_RESPAWN_X;
		yPos = PLAYER_RESPAWN_Y;
		// hp is restored and cooldownTimer is reset.
		hp = maxHp;
		cooldownTimer = 0;
	}
}
