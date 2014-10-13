import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for items.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public abstract class Item extends Entity {

	public static final String ITEMS_LOCATION = RPG.ASSETS_LOCATION + "items/";
	public static final String PICK_UP_SOUND_LOCATION = ITEMS_LOCATION
			+ "item.ogg";
	/** Flag for determining whether item is picked up by unit or not. */
	protected boolean pickedUp;
	/** Sound produced when an item is picked up. */
	protected Sound pickUpSound;
	/* Status Effects */
	protected int hpBuff;
	protected int damageBuff;
	protected int cooldownBuff;

	public Item(float xPos, float yPos, Image image) throws SlickException {
		super(xPos, yPos, image);
		pickedUp = false;
		pickUpSound = new Sound(PICK_UP_SOUND_LOCATION);
		hpBuff = 0;
		damageBuff = 0;
		cooldownBuff = 0;
	}

	/**
	 * Status effects are applied to player as item is picked up.
	 * 
	 * @param player
	 *            Main unit of game.
	 */
	public void interact(Player player) {
		player.setHp(player.getHp() + hpBuff);
		player.setMaxHp(player.getMaxHp() + hpBuff);
		player.setMaxDamage(player.getMaxDamage() + damageBuff);
		player.setCooldown(player.getCooldown() + cooldownBuff);
		pickedUp = true;
		pickUpSound.play();
	}

	/**
	 * It checks whether item is picked up or not.
	 * 
	 * @return pickedUp;
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**
	 * Item is rendered on panel after it is picked up.
	 * 
	 * @param xPos
	 *            X-coordinate on panel.
	 * @param yPos
	 *            Y-coordinate on panel.
	 */
	public void renderOnPanel(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		image.draw(xPos, yPos);
	}
}
