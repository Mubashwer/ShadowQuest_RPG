/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Mubashwer Salman Khurshid (mskh, 601738)
 */

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * A property-based tile map which provides context for path finding generic
 * tools.
 */
public class SimpleMap implements TileBasedMap {

	/** The game map */
	public TiledMap map;
	/** The property which determines if a tile is blocked or not. */
	private final String blockProperty;

	/**
	 * Creates a new SimpleMap object.
	 * 
	 * @param map
	 *            a TiledMap object.
	 * @param blockProperty
	 *            The property which determines if a tile is blocked or not.
	 */
	public SimpleMap(TiledMap map, String blockProperty) {
		this.map = map;
		this.blockProperty = blockProperty;
	}

	/**
	 * Checks if the given location is blocked, i.e. blocks movement of the
	 * supplied mover.
	 * 
	 * @param context
	 *            The context describing the path finding at the time of this
	 *            request.
	 * @param xTile
	 *            The x coordinate of the tile we're moving to.
	 * @param yTile
	 *            The y coordinate of the tile we're moving to.
	 * @return True if the location is blocked.
	 * 
	 */
	@Override
	public boolean blocked(PathFindingContext context, int xTile, int yTile) {
		int tileId = map.getTileId(xTile, yTile, 0);
		String block = map.getTileProperty(tileId, blockProperty, "0");
		return block.equals("1");
	}

	/**
	 * Gets the cost of moving through the given tile.
	 * 
	 * @param context
	 *            The context describing the path finding at the time of this
	 *            request.
	 * @param xTile
	 *            The x coordinate of the tile we're moving to.
	 * @param yTile
	 *            The y coordinate of the tile we're moving to.
	 * @return The relative cost of moving across the given tile.
	 * 
	 */
	@Override
	public float getCost(PathFindingContext context, int xTile, int yTile) {
		return 1.0F;
	}

	/**
	 * Gets the height of the map in number of tiles.
	 */
	@Override
	public int getHeightInTiles() {
		return map.getHeight();
	}

	/**
	 * Gets the width of the map in number of tiles.
	 */
	@Override
	public int getWidthInTiles() {
		return map.getWidth();
	}

	/**
	 * Notification that the path finder visited a given tile.
	 * 
	 * @param xTile
	 *            The x coordinate of the tile that was visited
	 * @param yTile
	 *            The y coordinate of the tile that was visited
	 */
	@Override
	public void pathFinderVisited(int xTile, int yTile) {
	}

	/**
	 * Gets the width of a tile in map.
	 */
	public int getTileWidth() {
		return map.getTileWidth();
	}

	/**
	 * Gets the height of a tile in map.
	 */
	public int getTileHeight() {
		return map.getTileHeight();
	}

	/**
	 * Renders a section of the tile map.
	 * 
	 * @param x
	 *            The x location to render at.
	 * @param y
	 *            The y location to render at.
	 * @param sx
	 *            The x tile location to start rendering.
	 * @param sy
	 *            The y tile location to start rendering.
	 * @param width
	 *            The width of the section to render (in tiles).
	 * @param height
	 *            The height of the section to render (in tiles).
	 */
	public void render(int x, int y, int sx, int sy, int width, int height) {
		map.render(x, y, sx, sy, width, height);
	}
}
