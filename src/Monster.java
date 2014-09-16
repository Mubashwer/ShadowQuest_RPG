import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Monster class. It is designed to be inherited by specific
 * type of monsters such as active or passive.
 */
public abstract class Monster extends Unit{
	
	/** The horizontal direction of monster. */
	protected int xDir;
	/** The vertical direction of monster. */
	protected int yDir;
	/** The sound the monster makes when it dies. */
	protected Sound dieSound;
	
	/**
	 * Create a new Monster object.
	 * 
	 * @param xPos
	 *            X-coordinate of monster.
	 * @param yPos
	 *            Y-coordinate of monster.
	 * @param image
	 *            Sprite of monster.
	 * @throws SlickException
	 */
	public Monster(float xPos, float yPos, Image image) {
		super(xPos, yPos, image);
		xDir = 0;
		yDir = 0;
	}

}
