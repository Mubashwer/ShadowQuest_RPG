import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for Sword of Strength item.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Sword extends Item {

	public static final String NAME = "Sword of Strength";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "sword.png";
	public static final int DAMAGE_BUFF = 80;
	
	/**
	 * Create a new Sword object.
	 * 
	 * @param xPos
	 *            X-coordinate in game.
	 * @param yPos
	 *            Y-coordinate in game.
	 * @throws SlickException
	 */
	public Sword(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		damageBuff = 30;
	}
}
