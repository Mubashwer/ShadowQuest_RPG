import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for Amulet of Vitality item.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Amulet extends Item {

	public static final String NAME = "Amulet of Vitality";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "amulet.png";
	public static final int HP_BUFF = 80;

	/**
	 * Create a new Amulet object.
	 * 
	 * @param xPos
	 *            X-coordinate in game.
	 * @param yPos
	 *            Y-coordinate in game.
	 * @throws SlickException
	 */
	public Amulet(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		hpBuff = 80;
	}
}
