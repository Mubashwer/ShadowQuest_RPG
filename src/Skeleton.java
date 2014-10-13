import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for Skeleton.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public class Skeleton extends Aggressive {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "skeleton.png";
	public static final String NAME = "Skeleton";
	/* Unit Stats. */
	public static final int HEALTH = 100;
	public static final int DAMAGE = 16;
	public static final int COOLDOWN = 500;
	public static final String DIE_SOUND_LOCATION = UNITS_LOCATION
			+ "skeleton-die.ogg";

	/**
	 * Create a new Skeleton object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @throws SlickException
	 */
	public Skeleton(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		dieSound = new Sound(DIE_SOUND_LOCATION);
	}
}