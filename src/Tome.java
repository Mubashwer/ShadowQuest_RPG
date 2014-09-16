import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tome extends Item {

	public static final String NAME = "Tome of Agility";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "book.png";
	public static final int COOLDOWN_BUFF = 80;

	public Tome(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		cooldownBuff = -300;
	}
}