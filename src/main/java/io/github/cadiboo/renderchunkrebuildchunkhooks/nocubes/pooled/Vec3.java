package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Simple pooled 3d vector/point with some util methods
 *
 * @author Cadiboo
 */
public class Vec3 implements AutoCloseable {

//	private static int instances = 0;

	public double x;
	public double y;
	public double z;

//	private boolean released;

	private static final ArrayList<Vec3> POOL = new ArrayList<>();

	private Vec3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
//		++instances;
	}

	public Vec3 addOffset(final double x, final double y, final double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	@Nonnull
	public static Vec3 retain(final double x, final double y, final double z) {
		synchronized (POOL) {
			if (!POOL.isEmpty()) {
				Vec3 pooled = POOL.remove(POOL.size() - 1);
				if (pooled != null /*&& pooled.released*/) {
//					pooled.released = false;
					pooled.x = x;
					pooled.y = y;
					pooled.z = z;
					return pooled;
				}
			}
		}
		return new Vec3(x, y, z);
	}

	@Nonnull
	public static Vec3 retain(final double[] vertexAsArray) {
		return retain(
				vertexAsArray[0],
				vertexAsArray[1],
				vertexAsArray[2]
		);
	}

	@Nonnull
	public static Vec3 retain(final float[] vertexAsArray) {
		return retain(
				vertexAsArray[0],
				vertexAsArray[1],
				vertexAsArray[2]
		);
	}

	public Vec3 copy() {
		return new Vec3(
				this.x,
				this.y,
				this.z
		);
	}

	@Override
	public void close() {
//		if (!ModConfig.enablePools) {
//			return;
//		}
		synchronized (POOL) {
//			if (POOL.size() < (ModConfig.enableCollisions ? 2_000_000 : 60_000)) {
			{
				POOL.add(this);
			}
//			this.released = true;
		}
	}

//	public static int getInstances() {
//		return instances;
//	}
//
//	public static int getPoolSize() {
//		return POOL.size();
//	}
//
//	@Override
//	protected void finalize() throws Throwable {
//		super.finalize();
//		--instances;
//	}

}
