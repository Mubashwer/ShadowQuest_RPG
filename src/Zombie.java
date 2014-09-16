import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Zombie extends Aggressive {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "zombie.png";
	public static final String NAME = "Zombie";
	/* Unit Stats. */
	public static final int HEALTH = 60;
	public static final int DAMAGE = 10;
	public static final int COOLDOWN = 800;
	public static final String DIE_SOUND_LOCATION = UNITS_LOCATION
			+ "zombie-die.ogg";

	/**
	 * Create a new Zombie object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @throws SlickException
	 */
	public Zombie(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		dieSound = new Sound(DIE_SOUND_LOCATION);
	}
}