package app.sagen.geometry.AStar;

public interface TileMap {

    int getWidth();

    int getHeight();

    boolean isTraversable(TileEntity entity, int x, int y);

    float getMovementCost(TileEntity entity, int sx, int sy, int tx, int ty);
}
