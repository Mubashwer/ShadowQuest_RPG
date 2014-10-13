import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for Giant Bat.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public class GiantBat extends Passive {

	public static final String IMAGE_LOCATION = UNITS_LOCATION + "dreadbat.png";
	public static final String NAME = "Giant Bat";
	/* Unit Stats. */
	public static final int HEALTH = 40;
	public static final int DAMAGE = 0;
	public static final int COOLDOWN = 0;
	public static final String DIE_SOUND_LOCATION = UNITS_LOCATION
			+ "bat-die.ogg";

	/**
	 * Create a new GiantBat object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @throws SlickException
	 */
	public GiantBat(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		dieSound = new Sound(DIE_SOUND_LOCATION);
	}
}
