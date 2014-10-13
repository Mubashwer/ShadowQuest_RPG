import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for an entity in game.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public abstract class Entity {

	/** Pixel range for attack or interaction with player */
	public static final int INTERACT_RANGE = 50;
	/** Name of the entity. */
	protected String name;
	/** x coordinate of entity in map. */
	protected float xPos;
	/** y coordinate of entity in map. */
	protected float yPos;
	/** It is the image of the entity. */
	protected Image image;

	/**
	 * Create a new Entity object.
	 * 
	 * @param xPos
	 *            X-coordinate of monster.
	 * @param yPos
	 *            Y-coordinate of monster.
	 * @param image
	 *            Sprite of monster.
	 * @throws SlickException
	 */
	public Entity(float xPos, float yPos, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
	}

	/**
	 * It gets the name of the entity,
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * It gets the x-coordinate of entity.
	 * 
	 * @return xPos
	 */
	public float getxPos() {
		return xPos;
	}

	/**
	 * It changes x-coordinate of entity.
	 * 
	 * @param xPos
	 */
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	/**
	 * It gets the y-coordinate of entity.
	 * 
	 * @return yPos
	 */
	public float getyPos() {
		return yPos;
	}

	/**
	 * It changes y-coordinate of entity.
	 * 
	 * @param yPos
	 */

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	/**
	 * Draw the entity on screen.
	 * 
	 * @param g
	 *            Graphics object.
	 * @param camMinX
	 *            Top-Left x-coordinate of camera.
	 * @param camMinY
	 *            Top-Left y-coordinate of camera.
	 */
	public void render(Graphics g, int camMinX, int camMinY) {
		image.drawCentered((int) xPos - camMinX, (int) yPos - camMinY);

	}

	/**
	 * It gets the distance between this entity and given entity.
	 * 
	 * @param entity
	 *            Another entity.
	 * @return Distance between entities.
	 */
	public float getDistance(Entity entity) {
		float distX = (xPos - entity.xPos);
		float distY = (yPos - entity.yPos);
		float distTotal = (float) Math.sqrt((distX * distX) + (distY * distY));
		return distTotal;
	}

}
