import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public abstract class Item extends Entity {

	public static final String ITEMS_LOCATION = RPG.ASSETS_LOCATION + "items/";
	public static final String PICK_UP_SOUND_LOCATION = ITEMS_LOCATION
			+ "item.ogg";
	protected boolean pickedUp;
	protected Sound pickUpSound;
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

	public void interact(Player player) {
		player.setHp(player.getHp() + hpBuff);
		player.setMaxHp(player.getMaxHp() + hpBuff);
		player.setMaxDamage(player.getMaxDamage() + damageBuff);
		player.setCooldown(player.getCooldown() + cooldownBuff);
		pickedUp = true;
		pickUpSound.play();
	}

	public void render(Graphics g, int camMinX, int camMinY) {
		image.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);

	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void renderOnPanel(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		image.draw(xPos, yPos);
	}
}
