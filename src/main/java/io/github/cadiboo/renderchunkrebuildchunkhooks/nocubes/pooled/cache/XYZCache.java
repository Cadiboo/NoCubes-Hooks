package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache;

/**
 * @author Cadiboo
 */
public class XYZCache {

	public int sizeX;
	public int sizeY;
	public int sizeZ;

	public XYZCache(final int sizeX, final int sizeY, final int sizeZ) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
	}

	public int getIndex(final int x, final int y, final int z) {
		// Flat[x + WIDTH * (y + HEIGHT * z)] = Original[x, y, z]
		return x + sizeX * (y + sizeY * z);
	}

}
