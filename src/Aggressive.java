import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Aggressive Monster class. It is designed to be inherited by specific
 * monsters.
 */
public abstract class Aggressive extends Monster {

	/** Speed of monster in pixels per millisecond. */
	public static final float SPEED = 0.25F;
	/** The pixel range at which the monster starts chasing the player. */
	public static final int CHASE_RANGE = 150;
	public static final String DEFAULT_ATTACK_SOUND_LOCATION = UNITS_LOCATION
			+ "hit.ogg";
	/* The sound produced when the monster makes an attack. */
	protected Sound attackSound;

	/**
	 * Create a new Aggressive object.
	 * 
	 * @param xPos
	 *            X-coordinate of monster.
	 * @param yPos
	 *            Y-coordinate of monster.
	 * @param image
	 *            Sprite of monster.
	 * @throws SlickException
	 */
	public Aggressive(float xPos, float yPos, Image image)
			throws SlickException {
		super(xPos, yPos, image);
		speed = SPEED;
		attackSound = new Sound(DEFAULT_ATTACK_SOUND_LOCATION);
	}

	@Override
	public void move(World world, int delta, Player player) {
		float playerX = player.getxPos();
		float playerY = player.getyPos();

		/* Calculates distance to be moved by monster. */
		float distX = (playerX - xPos);
		float distY = (playerY - yPos);
		float distTotal = (float) Math.sqrt((distX * distX) + (distY * distY));
		float pixels = speed * delta;
		/*
		 * If player is within 50 pixels and greater than 150 pixels then the
		 * monster starts chasing the player.
		 */
		if (distTotal > INTERACT_RANGE && distTotal <= CHASE_RANGE) {
			xDir = (int) ((playerX - xPos) / (Math.abs(xPos - playerX)));
			yDir = (int) ((playerY - yPos) / (Math.abs(yPos - playerY)));

			float xPosNew = xPos + (distX / distTotal) * pixels;
			float yPosNew = yPos + (distY / distTotal) * pixels;

			update(world, delta, xDir, yDir, xPosNew, yPosNew);

		}
		/*
		 * If player is within 50 pixels and cooldown time has passed then the
		 * monster makes an attack and starts to cooldown.
		 */
		if (distTotal <= INTERACT_RANGE && cooldownTimer <= 0) {
			attackSound.play();
			player.getsDamaged(getRandom(0, maxDamage));
			cooldownTimer = cooldown;
		}
		if (cooldownTimer > 0)
			cooldownTimer -= delta;
	}

	@Override
	public void getsDamaged(int damage) {
		hp -= damage;
		if (hp <= 0) {
			hp = 0;
			deceased = true;
			dieSound.play();
		}
	}
}
