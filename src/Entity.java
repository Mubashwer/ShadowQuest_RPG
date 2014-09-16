import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Entity {
	
	public static final int INTERACT_RANGE = 50;
	/** Name of the entity. */
	protected String name;
	/** x coordinate of entity in map. */
	protected float xPos;
	/** y coordinate of entity in map. */
	protected float yPos;
	/** It is the image of the entity. */
	protected Image image;
	

	public Entity(float xPos, float yPos, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
	}

	public String getName() {
		return name;
	}
	
	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public abstract void render(Graphics g, int camMinX, int camMinY);

	public float getDistance(Entity entity) {
		float distX = (xPos - entity.xPos);
		float distY = (yPos - entity.yPos);
		float distTotal = (float) Math.sqrt((distX * distX) + (distY * distY));
		return distTotal;
	}

}
