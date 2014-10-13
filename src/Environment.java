import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Class for environment in game. It controls rain and day-night alteration.
 * 
 * @author Mubashwer Salman Khurshid(mskh, 601738)
 *
 */
public class Environment {

	/* filters for day-night effects */
	public static final Color COLOUR_MORNING = new Color(1F, 1F, 0.9F, 0.2F);
	public static final Color COLOUR_EVENING = new Color(0.5F, 0, 0.5F, 0.2F);
	public static final Color COLOUR_NIGHT = new Color(0, 0.2F, 0.5F, 0.4F);
	public static final Color COLOUR_DAWN = new Color(0.2F, 0.3F, 0.3F, 0.5F);
	private Color colour;

	/* Durations for rain and a time (such as afternoon) of day */
	public static final int PERIOD_DURATION = 1 * 60 * 1000;
	public static final int RAIN_DURATION = 2 * PERIOD_DURATION;

	public static final String RAIN_IMAGE_LOCATION = RPG.ASSETS_LOCATION
			+ "rain.png";
	private Image rainImage;
	/** Timer for rain */
	private int rainTimer;
	/** It is the time at which it is supposed to rain */
	private int rainTime;
	/** Timer for a single day */
	private int oneDayTimer;

	/**
	 * Create a new environment object.
	 * 
	 * @throws SlickException
	 */
	public Environment() throws SlickException {
		colour = COLOUR_EVENING;
		rainTimer = 0;
		oneDayTimer = 5 * PERIOD_DURATION;
		rainImage = new Image(RAIN_IMAGE_LOCATION);
		rainTime = 4 * PERIOD_DURATION - 30000;
	}

	/**
	 * It updates weather.
	 * 
	 * @param delta
	 *            Milliseconds per frame.
	 */
	public void update(int delta) {
		oneDayTimer -= delta;
		if (oneDayTimer <= 0) {
			oneDayTimer = 5 * PERIOD_DURATION;
			colour = COLOUR_EVENING;
			/* Generate a random time of rain for every day. */
			rainTime = Unit.getRandom(0, PERIOD_DURATION * 5);
		} else if (oneDayTimer < PERIOD_DURATION) {
			colour = null;
		} else if (oneDayTimer < 2 * PERIOD_DURATION) {
			colour = COLOUR_MORNING;
		} else if (oneDayTimer < 3 * PERIOD_DURATION) {
			colour = COLOUR_DAWN;
		} else if (oneDayTimer < 4 * PERIOD_DURATION) {
			colour = COLOUR_NIGHT;
		}

		if (rainTimer > 0)
			rainTimer -= delta;
		// If it is time for rain, timer is set.
		else if (rainTime >= oneDayTimer - (2 * delta)
				&& rainTime <= oneDayTimer + (2 * delta)) {
			rainTimer = RAIN_DURATION;
		}
	}

	/**
	 * It renders rain and time of day filter.
	 * 
	 * @param g
	 *            Graphics object
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException {
		float intensity = 0.1F;
		/** Rain intensifies as it rains */
		if (rainTimer > 0) {
			if (rainTimer < 1 / 10.0 * RAIN_DURATION
					|| rainTimer >= 9 / 10.0 * RAIN_DURATION)
				intensity = 0.1F;
			else if (rainTimer < 2 / 10.0 * RAIN_DURATION
					|| rainTimer >= 8 / 10.0 * RAIN_DURATION)
				intensity = 0.2F;
			else if (rainTimer < 3 / 10.0 * RAIN_DURATION
					|| rainTimer >= 7 / 10.0 * RAIN_DURATION)
				intensity = 0.3F;
			else if (rainTimer < 4 / 10.0 * RAIN_DURATION
					|| rainTimer >= 6 / 10.0 * RAIN_DURATION)
				intensity = 0.4F;
			else
				intensity = 0.5F;

			rainImage.setAlpha(intensity);
			rainImage
					.draw(Unit.getRandom(-RPG.SCREEN_WIDTH / 2,
							RPG.SCREEN_WIDTH / 2), 0);
			rainImage.draw(Unit.getRandom(0, RPG.SCREEN_WIDTH), 0);
		}

		if (colour != null) {
			g.setColor(colour);
			g.fillRect(0, 0, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
		}

	}

}
