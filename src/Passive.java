import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Passive Monster class. It is designed to be inherited by specific monsters.
 * Passive Monsters do not attack.
 */
public abstract class Passive extends Monster {

	/** Speed of monster in pixels per millisecond. */
	public static final float SPEED = 0.20F;
	/** Time needed to escape after being attacked. */
	public static final int MIN_RUN_AWAY_TIME = 5000;
	/** The monster changes direction after this interval of time. */
	public static final int MIN_DIR_CHANGE_TIME = 3000;
	/* Timers for changing direction. */
	private int dirChangeTimer;
	/* Timer for running away. */
	private int runAwayTimer;

	/**
	 * Create a new Passive object.
	 * 
	 * @param xPos
	 *            X-coordinate of monster.
	 * @param yPos
	 *            Y-coordinate of monster.
	 * @param image
	 *            Sprite of monster.
	 * @throws SlickException
	 */
	public Passive(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		dirChangeTimer = 0;
		runAwayTimer = 0;
		speed = SPEED;
	}

	@Override
	public void move(World world, int delta, Player player) {
		float playerX = player.getxPos();
		float playerY = player.getyPos();

		// When monster is safe from player.
		if (runAwayTimer <= 0) {

			// When it is time to change direction.
			if (dirChangeTimer <= 0) {
				xDir = getRandom(-1, 1);
				yDir = getRandom(-1, 1);
				runAwayTimer = 0;
				dirChangeTimer = MIN_DIR_CHANGE_TIME;
			} else
				dirChangeTimer -= delta;
		} else {
			// When monster is hit, it runs away from player until it is safe.
			runAwayTimer -= delta;
			xDir = (int) ((xPos - playerX) / (Math.abs(xPos - playerX)));
			yDir = (int) ((yPos - playerY) / (Math.abs(yPos - playerY)));
		}

		// Amount of pixels to move.
		float pixels = speed * delta;

		// Monster position after a possible move.
		float xPosNew = xPos + (xDir * pixels);
		float yPosNew = yPos + (yDir * pixels);

		// Make the changes in position if path is not totally blocked.
		update(world, delta, xDir, yDir, xPosNew, yPosNew);
	}

	@Override
	public void getsDamaged(int damage) {
		hp -= damage;
		if (hp <= 0) {
			hp = 0;
			deceased = true;
			dieSound.play();
		}
		// Timer is set after being attacked.
		runAwayTimer = MIN_RUN_AWAY_TIME;
	}
}
