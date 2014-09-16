/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {

	/** The game map */
	private SimpleMap map;
	/** Screen width in tiles */
	private final int screenWidthTiles;
	/** Screen height in tiles */
	private final int screenHeightTiles;
	/** The main unit of the game */
	private Player player;
	/** The camera which follows the player */
	private Camera camera;
	/** A series of steps from the starting location to the target location. */
	private Path path;
	/** It is the index of a path which contains x and y positions in tiles. */
	private int step;
	private Image panel;

	List<Unit> units;
	List<Item> items;
	List<Item> inventory;

	/**
	 * Create a new World object.
	 */
	public World() throws SlickException {
		map = new SimpleMap(
				new TiledMap(RPG.MAP_LOCATION, RPG.ASSETS_LOCATION),
				RPG.MAP_BLOCK_PROPERTY);
		units = new ArrayList<Unit>();
		items = new ArrayList<Item>();
		inventory = new ArrayList<Item>();

		player = new Player(inventory);
		camera = new Camera(player, map, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT
				- RPG.PANEL_HEIGHT);
		panel = new Image(RPG.PANEL_IMAGE_LOCATION);

		/* Add items to the world */
		items.add(new Amulet(700, 500));// 965, 3563
		items.add(new Sword(780, 500)); // 4791, 1253
		items.add(new Tome(800, 400));
		items.add(new Elixir(850, 550)); // 1976, 402

		units.add(new Prince(467, 679));
		units.add(new Elvira(738, 549));
		units.add(new Garth(756, 870));

		units.add(new GiantBat(1431, 864));
		units.add(new GiantBat(1154, 1321));
		units.add(new GiantBat(807, 2315));
		units.add(new GiantBat(833, 2657));
		units.add(new GiantBat(1090, 3200));
		units.add(new GiantBat(676, 3187));
		units.add(new GiantBat(836, 3966));
		units.add(new GiantBat(653, 4367));
		units.add(new GiantBat(1343, 2731));
		units.add(new GiantBat(1835, 3017));
		units.add(new GiantBat(1657, 3954));
		units.add(new GiantBat(1054, 5337));
		units.add(new GiantBat(801, 5921));
		units.add(new GiantBat(560, 6682));
		units.add(new GiantBat(1275, 5696));
		units.add(new GiantBat(1671, 5991));
		units.add(new GiantBat(2248, 6298));
		units.add(new GiantBat(2952, 6373));
		units.add(new GiantBat(3864, 6695));
		units.add(new GiantBat(4554, 6443));
		units.add(new GiantBat(4683, 6228));
		units.add(new GiantBat(5312, 6606));
		units.add(new GiantBat(5484, 5946));
		units.add(new GiantBat(6371, 5634));
		units.add(new GiantBat(5473, 3544));
		units.add(new GiantBat(5944, 3339));
		units.add(new GiantBat(6301, 3414));
		units.add(new GiantBat(6388, 1994));
		units.add(new GiantBat(6410, 1584));
		units.add(new GiantBat(5314, 274));
		
		units.add(new Zombie(681, 3201));
		units.add(new Zombie(691, 4360));
		units.add(new Zombie(2166, 2650));
		units.add(new Zombie(2122, 2725));
		units.add(new Zombie(2284, 2962));
		units.add(new Zombie(2072, 4515));
		units.add(new Zombie(2006, 4568));
		units.add(new Zombie(2385, 4629));
		units.add(new Zombie(2446, 4590));
		units.add(new Zombie(2517, 4532));
		units.add(new Zombie(4157, 6730));
		units.add(new Zombie(4247, 6620));
		units.add(new Zombie(4137, 6519));
		units.add(new Zombie(4234, 6449));
		units.add(new Zombie(5879, 4994));
		units.add(new Zombie(5954, 4928));
		units.add(new Zombie(6016, 4866));
		units.add(new Zombie(5860, 4277));
		units.add(new Zombie(5772, 4277));
		units.add(new Zombie(5715, 4277));
		units.add(new Zombie(5653, 4277));
		units.add(new Zombie(5787, 797));
		units.add(new Zombie(5668, 720));
		units.add(new Zombie(5813, 454));
		units.add(new Zombie(5236, 917));
		units.add(new Zombie(5048, 1062));
		units.add(new Zombie(4845, 996));
		units.add(new Zombie(4496, 575));
		units.add(new Zombie(3457, 273));
		units.add(new Zombie(3506, 779));
		units.add(new Zombie(3624, 1192));
		units.add(new Zombie(2931, 1396));
		units.add(new Zombie(2715, 1326));
		units.add(new Zombie(2442, 1374));
		units.add(new Zombie(2579, 1159));
		units.add(new Zombie(2799, 1269));
		units.add(new Zombie(2768, 739));
		units.add(new Zombie(2099, 956));
		
		units.add(new Bandit(1889, 2581));
		units.add(new Bandit(4502, 6283));
		units.add(new Bandit(5248, 6581));
		units.add(new Bandit(5345, 6678));
		units.add(new Bandit(5940, 3412));
		units.add(new Bandit(6335, 3459));
		units.add(new Bandit(6669, 260));
		units.add(new Bandit(6598, 339));
		units.add(new Bandit(6598, 528));
		units.add(new Bandit(6435, 528));
		units.add(new Bandit(6435, 678));
		units.add(new Bandit(5076, 1082));
		units.add(new Bandit(5191, 1187));
		units.add(new Bandit(4940, 1175));
		units.add(new Bandit(4760, 1039));
		units.add(new Bandit(4883, 889));
		units.add(new Bandit(4427, 553));
		units.add(new Bandit(3559, 162));
		units.add(new Bandit(3779, 1553));
		units.add(new Bandit(3573, 1553));
		units.add(new Bandit(3534, 2464));
		units.add(new Bandit(3635, 2464));
		units.add(new Bandit(3402, 2861));
		units.add(new Bandit(3151, 2857));
		units.add(new Bandit(3005, 2997));
		units.add(new Bandit(2763, 2263));
		units.add(new Bandit(2648, 2263));
		units.add(new Bandit(2621, 1337));
		units.add(new Bandit(2907, 1270));
		units.add(new Bandit(2331, 598));
		units.add(new Bandit(2987, 394));
		units.add(new Bandit(1979, 394));
		units.add(new Bandit(2045, 693));
		units.add(new Bandit(2069, 1028));
		
		units.add(new Skeleton(1255, 2924));
		units.add(new Skeleton(2545, 4708));
		units.add(new Skeleton(4189, 6585));
		units.add(new Skeleton(5720, 622));
		units.add(new Skeleton(5649, 767));
		units.add(new Skeleton(5291, 312));
		units.add(new Skeleton(5256, 852));
		units.add(new Skeleton(4790, 976));
		units.add(new Skeleton(4648, 401));
		units.add(new Skeleton(3628, 1181));
		units.add(new Skeleton(3771, 1181));
		units.add(new Skeleton(3182, 2892));
		units.add(new Skeleton(3116, 3033));
		units.add(new Skeleton(2803, 2901));
		units.add(new Skeleton(2850, 2426));
		units.add(new Skeleton(2005, 1524));
		units.add(new Skeleton(2132, 1427));
		units.add(new Skeleton(2242, 1343));
		units.add(new Skeleton(2428, 771));
		units.add(new Skeleton(2427, 907));
		units.add(new Skeleton(2770, 613));
		units.add(new Skeleton(2915, 477));
		units.add(new Skeleton(1970, 553));
		units.add(new Skeleton(2143, 1048));

		units.add(new Draelic(2069, 510));


		// Get screen size in tiles (2 extra tiles for offset).
		screenWidthTiles = (camera.screenwidth / getTileWidth()) + 2;
		screenHeightTiles = (camera.screenheight / getTileHeight()) + 2;

		Music theme = new Music(RPG.THEME_LOCATION);
		theme.loop();

		path = null;
		step = 0;
	}

	/**
	 * It returns width of map in number of tiles.
	 */
	public int getWidth() {
		return map.getWidthInTiles();
	}

	/**
	 * It returns height of map in number of tiles.
	 */
	public int getHeight() {
		return map.getHeightInTiles();
	}

	/**
	 * It returns width of a tile in map.
	 */
	public int getTileWidth() {
		return map.getTileWidth();
	}

	/**
	 * It returns height of a tile in map.
	 */
	public int getTileHeight() {
		return map.getTileHeight();
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param xDir
	 *            The 's movement in the x axis (-1, 0 or 1).
	 * @param yDir
	 *            The 's movement in the y axis (-1, 0 or 1).
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 * @param mousePressed
	 *            True if mouse is pressed.
	 * @param mouseScreenX
	 *            X coordinate in screen where mouse is clicked.
	 * @param mouseScreenY
	 *            Y coordinate in screen where mouse is clicked.
	 * @param arrowKeyPressed
	 *            True if an arrow key is pressed.
	 */
	public void update(float xDir, float yDir, int delta, boolean mousePressed,
			int mouseScreenX, int mouseScreenY, boolean arrowKeyPressed, boolean interactPressed, boolean attackPressed )
			throws SlickException {
		
		if(player.isDeceased() == true) {
			player.respawn();
			this.path = null;
			step = 0;
		}
		AStarPathFinder pathFinder = new AStarPathFinder(map, getWidth()
				* getHeight(), false);
		
		// Player position in tiles.
		int xPosTile = (int) (player.getxPos() / getTileWidth());
		int yPosTile = (int) (player.getyPos() / getTileHeight());
		
		// User pressed an arrow key, so disregard previous mouse click.
		if (arrowKeyPressed == true) {
			this.path = null;
			step = 0;
		}
		// User pressed mouse button only, an attempt is made to find path.
		else if (mousePressed == true) {
			int mouseTileX = (int) ((mouseScreenX + camera.getMinX()) / getTileWidth());
			int mouseTileY = (int) ((mouseScreenY + camera.getMinY()) / getTileHeight());

			/*
			 * It finds path from the player's position to the position where
			 * mouse was clicked and produces a series of steps.
			 */
			this.path = pathFinder.findPath(player, xPosTile, yPosTile,
					mouseTileX, mouseTileY);
			step = 0;
		}
		/*
		 * If the player is in the middle of an auto-move when path was found in
		 * a previous update.
		 */
		else if (path != null) {

			// Direction is towards the next step.
			if (xPosTile > path.getX(step))
				xDir--;
			else if (xPosTile < path.getX(step))
				xDir++;
			if (yPosTile < path.getY(step))
				yDir++;
			else if (yPosTile > path.getY(step))
				yDir--;
			/*
			 * When the player reaches a step, the player should try to reach
			 * the next step during next update call.
			 */
			if (xPosTile == path.getX(step) && yPosTile == path.getY(step))
				step++;

			// When player finally reaches the target...
			if (step >= path.getLength()) {
				path = null;
				step = 0;
			}
		}
		Iterator<Item> itemsIt = items.iterator();
		while (itemsIt.hasNext()) {
			Item item = itemsIt.next();
			if (item.isPickedUp() == true) {
				itemsIt.remove();
			}
		}

		Iterator<Unit> unitsIt = units.iterator();
		while (unitsIt.hasNext()) {
			Unit npc = unitsIt.next();
			npc.move(this, delta, player);
			if (npc.isDeceased() == true)
				unitsIt.remove();
		}
		
		
		// Attempt to move player
		boolean moved = player.move(this, delta, xDir, yDir, units, items,
				interactPressed, attackPressed);

		// If player gets trapped due to any bug, auto-move is cancelled.
		if (moved == false) {
			path = null;
			step = 0;
		}
		camera.update();



	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException {
		// Camera position in tiles
		int xTile = camera.getMinX() / getTileWidth();
		int yTile = camera.getMinY() / getTileHeight();

		// Pixels offset after camera is positioned in tiles
		int xOffset = camera.getMinX() % getTileWidth();
		int yOffset = camera.getMinY() % getTileHeight();

		int camMinX = camera.getMinX();
		int camMinY = camera.getMinY();

		map.render(-xOffset, -yOffset, xTile, yTile, screenWidthTiles,
				screenHeightTiles);
		for (Item item : items)
			item.render(g, camMinX, camMinY);

		for (Unit unit : units)
			unit.render(g, camMinX, camMinY);

		player.render(g, camMinX, camMinY);
		renderPanel(g);
	}

	/**
	 * Checks whether given location in map is blocked or not.
	 * 
	 * @param xPos
	 *            x coordinate of unit in map.
	 * @param yPos
	 *            y coordinate of unit in map.
	 * @return boolean value true if tile is blocked otherwise false
	 */
	public boolean blocked(float xPos, float yPos) {
		// Checks if new position is inside map.
		if (xPos < 0 || xPos >= getWidth() * getTileWidth()) {
			return true;
		}
		if (yPos < 0 || yPos >= getHeight() * getTileHeight()) {
			return true;
		}

		int xTile = (int) (xPos / getTileWidth());
		int yTile = (int) (yPos / getTileHeight());

		// checks for terrain blocking.
		return (map.blocked(null, xTile, yTile));
	}

	/**
	 * Renders the player's status panel.
	 * 
	 * @param g
	 *            The current Slick graphics context.
	 */
	public void renderPanel(Graphics g) {
		// Panel colours
		Color LABEL = new Color(0.9f, 0.9f, 0.4f); // Gold
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red, transp

		// Variables for layout
		String text; // Text to display
		int text_x, text_y; // Coordinates to draw text
		int bar_x, bar_y; // Coordinates to draw rectangles
		int bar_width, bar_height; // Size of rectangle to draw
		int hp_bar_width; // Size of red (HP) rectangle
		int inv_x, inv_y; // Coordinates to draw inventory item

		float health_percent; // Player's health, as a percentage

		// Panel background image
		panel.draw(0, RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT, RPG.SCREEN_WIDTH,
				panel.getHeight(), VALUE);

		// Display the player's health
		text_x = 15;
		text_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 25;
		g.setColor(LABEL);
		g.drawString("Health:", text_x, text_y);
		text = Integer.toString(player.getHp()) + '/' + player.getMaxHp();

		bar_x = 90;
		bar_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 20;
		bar_width = 90;
		bar_height = 30;
		health_percent = (float) player.getHp() / player.getMaxHp();
		hp_bar_width = (int) (bar_width * health_percent);
		text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);

		// Display the player's damage and cooldown
		text_x = 200;
		g.setColor(LABEL);
		g.drawString("Damage:", text_x, text_y);
		text_x += 80;
		text = Integer.toString(player.getMaxDamage());
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);
		text_x += 40;
		g.setColor(LABEL);
		g.drawString("Rate:", text_x, text_y);
		text_x += 55;
		text = Integer.toString(player.getCooldown());
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);

		// Display the player's inventory
		g.setColor(LABEL);
		g.drawString("Items:", 420, text_y);
		bar_x = 490;
		bar_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 10;
		bar_width = 288;
		bar_height = bar_height + 20;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);

		inv_x = 490;
		inv_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT
				+ ((RPG.PANEL_HEIGHT - 72) / 2);
		for (Item item : inventory) {
			item.renderOnPanel(inv_x, inv_y);
			inv_x += 72;
		}
	}
}
