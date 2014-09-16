import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Elvira extends Villager {
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "shaman.png";
	public static final String NAME = "Elvira";
	public static final int HEALTH = 1;
	public static final int DAMAGE = 0;
	public static final int COOLDOWN = 0;
	public static final String HEAL_SOUND_LOCATION = UNITS_LOCATION
			+ "heal.ogg";

	public Elvira(float xPos, float yPos) throws SlickException {
		super(xPos, yPos, new Image(IMAGE_LOCATION));
		name = NAME;
		maxHp = HEALTH;
		hp = HEALTH;
		maxDamage = DAMAGE;
		cooldown = COOLDOWN;
		interactSound = new Sound(HEAL_SOUND_LOCATION);
	}

	@Override
	public void interact(List<Item> Inventory, Player player) {

		if (player.getHp() < player.getMaxHp()) {
			player.setHp(player.getMaxHp());
			interactSound.play();
			dialogue = "You're looking much healthier now.";
		} else
			dialogue = "Return to me if you ever need healing.";
	}

}
