import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for Garth.
 * 
 * @author Mubashwer Salman Khurshid (mskh, 601738)
 *
 */
public class Garth extends Villager {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "peasant.png";
	public static final String NAME = "Garth";
	/* Unit Stats. */
	public static final int HEALTH = 1;
	public static final int DAMAGE = 0;
	public static final int COOLDOWN = 0;
	public static final String WIN_SOUND_LOCATION = UNITS_LOCATION
			+ "winner.wav";
	/** Flag for determining status of quest. */
	private boolean questCompleted;

	/**
	 * Create a new Garth object.
	 * 
	 * @param xPos
	 *            X-coordinate of unit.
	 * @param yPos
	 *            Y-coordinate of unit.
	 */
	public Garth(float xPos, float yPos) throws SlickException {
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

		int amuletMissing = 1, swordMissing = 1, tomeMissing = 1;
		for (Item item : inventory) {
			if (item.getName() == "Amulet of Vitality")
				amuletMissing = 0;
			if (item.getName() == "Sword of Strength")
				swordMissing = 0;
			if (item.getName() == "Tome of Agility")
				tomeMissing = 0;
		}
		// Give hints for any missing item.
		if (amuletMissing == 1)
			dialogue = "Find the Amulet of Vitality, across the river to the west.";
		else if (swordMissing == 1)
			dialogue = "Find the Sword of Strength - cross the river and back, on the east side.";
		else if (tomeMissing == 1)
			dialogue = "Find the Tome of Agility, in the Land of Shadows.";
		else {
			dialogue = "You have found all the treasure I know of.";
			if (questCompleted == false) {
				questCompleted = true;
				interactSound.play();
			}
		}
	}

}
