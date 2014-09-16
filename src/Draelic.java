import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Draelic extends Aggressive {
	public static final String IMAGE_LOCATION = UNITS_LOCATION
			+ "necromancer.png";
	public static final String NAME = "Draelic";
	/* Unit Stats. */
	public static final int HEALTH = 140;
	public static final int DAMAGE = 30;
	public static final int COOLDOWN = 400;
	public static final String ATTACK_SOUND_LOCATION = UNITS_LOCATION
			+ "fireball.ogg";
	public static final String DIE_SOUND_LOCATION = UNITS_LOCATION
			+ "draelic-die.ogg";

	/**
	 * Create a new Draelic object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @throws SlickException
	 */
	public Draelic(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		attackSound = new Sound(ATTACK_SOUND_LOCATION);
		dieSound = new Sound(DIE_SOUND_LOCATION);
	}
}
