import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Amulet extends Item {

	public static final String NAME = "Amulet of Vitality";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "amulet.png";
	public static final int HP_BUFF = 80;

	public Amulet(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		hpBuff = 80;
	}
}
