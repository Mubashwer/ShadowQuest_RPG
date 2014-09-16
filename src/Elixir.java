import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Elixir extends Item {

	public static final String NAME = "Elixir of Life";
	public static final String IMAGE_LOCATION = ITEMS_LOCATION + "elixir.png";

	public Elixir(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
	}
}
