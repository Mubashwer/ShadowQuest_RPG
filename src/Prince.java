import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for Prince Aldric.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Prince extends Villager {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "prince.png";
	public static final String NAME = "Prince Aldric";
	/* Unit Stats. */
	public static final int HEALTH = 1;
	public static final int DAMAGE = 0;
	public static final int COOLDOWN = 0;
	public static final String WIN_SOUND_LOCATION = UNITS_LOCATION
			+ "winner.wav";
	/** Flag for determining status of quest. */
	private boolean questCompleted;

	/**
	 * Create a new Prince object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 * @param image
	 *            Sprite of unit.
	 */
	public Prince(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		interactSound = new Sound(WIN_SOUND_LOCATION);
		questCompleted = false;
	}

	@Override
	public void interact(List<Item> inventory, Player player) {

		if (questCompleted == true) {
			dialogue = "Thank you for your services!";
			return;
		}
		dialogue = "Please seek out the Elixir of Life to cure the king.";
		Iterator<Item> itemsIt = inventory.iterator();

		/*
		 * If elixir is present in inventory it is taken and dialogue is
		 * changed.
		 */
		while (itemsIt.hasNext()) {
			Item item = itemsIt.next();
			if (item.getName() == "Elixir of Life") {
				itemsIt.remove();
				dialogue = "The elixir! My father is cured! Thank you!";
				questCompleted = true;
				interactSound.play();
			}
		}
	}
}
