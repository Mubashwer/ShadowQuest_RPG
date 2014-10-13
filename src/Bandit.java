import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 * Class for Bandit.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public class Bandit extends Aggressive {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "bandit.png";
	public static final String NAME = "Bandit";
	/* Unit Stats. */
	public static final int HEALTH = 40;
	public static final int DAMAGE = 8;
	public static final int COOLDOWN = 200;
	public static final String DIE_SOUND_LOCATION = UNITS_LOCATION
			+ "bandit-die.ogg";

	/**
	 * Create a new Bandit object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @throws SlickException
	 */
	public Bandit(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		dieSound = new Sound(DIE_SOUND_LOCATION);
	}
}