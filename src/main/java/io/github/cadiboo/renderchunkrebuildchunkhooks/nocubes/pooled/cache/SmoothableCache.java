package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache;

import javax.annotation.Nonnull;

/**
 * @author Cadiboo
 */
public class SmoothableCache extends XYZCache implements AutoCloseable {

	private static final ThreadLocal<SmoothableCache> POOL = ThreadLocal.withInitial(() -> new SmoothableCache(0, 0, 0));
	private static int instances = 0;
	@Nonnull
	private boolean[] cache;

	private SmoothableCache(final int sizeX, final int sizeY, final int sizeZ) {
		super(sizeX, sizeY, sizeZ);
		cache = new boolean[sizeX * sizeY * sizeZ];
		++instances;
	}

	@Nonnull
	public static SmoothableCache retain(final int sizeX, final int sizeY, final int sizeZ) {

		final SmoothableCache pooled = POOL.get();

		if (pooled.sizeX == sizeX && pooled.sizeY == sizeY && pooled.sizeZ == sizeZ) {
			return pooled;
		}

		pooled.sizeX = sizeX;
		pooled.sizeY = sizeY;
		pooled.sizeZ = sizeZ;

		final int size = sizeX * sizeY * sizeZ;

		if (pooled.cache.length < size || pooled.cache.length > size * 1.25F) {
			pooled.cache = new boolean[size];
		}

		return pooled;
	}

	public static int getInstances() {
		return instances;
	}

	@Nonnull
	public boolean[] getSmoothableCache() {
		return cache;
	}

	@Override
	public void close() {
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		--instances;
	}

}
