import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;


public class SimpleMap implements TileBasedMap {
	
	public TiledMap map;
    private String blockProperty;
    
    public SimpleMap(TiledMap map, String blockProperty) {
        this.map = map;
        this.blockProperty = blockProperty;
    }
    
	@Override
	public boolean blocked(PathFindingContext context, int xTile, int yTile) {
		int tileId = map.getTileId(xTile, yTile, 0);
		String block = map.getTileProperty(tileId, blockProperty, "0");
		return block.equals("1");
	}

	@Override
	public float getCost(PathFindingContext context, int xPos, int yPos) {
		return 1.0F;
	}

	@Override
	public int getHeightInTiles() {
		return map.getHeight();
	}

	@Override
	public int getWidthInTiles() {
		return map.getWidth();
	}

	@Override
	public void pathFinderVisited(int arg0, int arg1) {}
	
	public int getTileWidth() {
		return map.getTileWidth();
	}
	
	public int getTileHeight() {
		return map.getTileHeight();
	}
	
	public void render(int x,int y, int sx, int sy, int width, int height) {
		map.render(x, y, sx, sy, width, height);
	}

}
