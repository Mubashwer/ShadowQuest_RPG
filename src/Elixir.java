import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 * Class for Elixir of Life item.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Elixir extends Item {

	public static final String NAME = "Elixir of Life";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "elixir.png";
	
	/**
	 * Create a new Elixir object.
	 * 
	 * @param xPos
	 *            X-coordinate in game.
	 * @param yPos
	 *            Y-coordinate in game.
	 * @throws SlickException
	 */
	public Elixir(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
	}
}
