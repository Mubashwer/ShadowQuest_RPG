import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for Tome of Agility item.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Tome extends Item {

	public static final String NAME = "Tome of Agility";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "book.png";
	public static final int COOLDOWN_BUFF = 80;
	
	/**
	 * Create a new Tome object.
	 * 
	 * @param xPos
	 *            X-coordinate in game.
	 * @param yPos
	 *            Y-coordinate in game.
	 * @throws SlickException
	 */
	public Tome(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		cooldownBuff = -300;
	}
}