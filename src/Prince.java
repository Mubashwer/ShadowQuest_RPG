import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


public class Prince extends Villager{
	public static final String IMAGE_LOCATION = UNITS_LOCATION + "prince.png";
	public static final String NAME = "Prince Aldric";
	public static final int HEALTH = 1;
	public static final int DAMAGE = 0;
	public static final int COOLDOWN = 0;
	public static final String WIN_SOUND_LOCATION = UNITS_LOCATION
			+ "winner.wav";
	private boolean questCompleted;
	
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

		if(questCompleted == true) {
			dialogue = "Thank you for your services!";
			return ;
		}
		dialogue = "Please seek out the Elixir of Life to cure the king.";
		Iterator<Item> itemsIt = inventory.iterator();
		
		while(itemsIt.hasNext()) {
			Item item = itemsIt.next();
			if(item.getName() == "Elixir of Life") {
				itemsIt.remove();
				dialogue = "The elixir! My father is cured! Thank you!"; 
				questCompleted = true;
				interactSound.play();
			}
		}
	}	
}
