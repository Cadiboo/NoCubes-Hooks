package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes;

import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache.PackedLightCache;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache.StateCache;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nonnull;

/**
 * @author Cadiboo
 */
public final class ClientCacheUtil {

	public static PackedLightCache generatePackedLightCache(
			final int startPosX, final int startPosY, final int startPosZ,
			@Nonnull final StateCache stateCache,
			@Nonnull final IWorldReader cache,
			@Nonnull BlockPos.PooledMutableBlockPos pooledMutableBlockPos
	) {
		final int cacheSizeX = stateCache.sizeX;
		final int cacheSizeY = stateCache.sizeY;
		final int cacheSizeZ = stateCache.sizeZ;

//		try (ModProfiler ignored = NoCubes.getProfiler().start("generate packedLightCache"))
		{
			final PackedLightCache packedLightCache = PackedLightCache.retain(cacheSizeX, cacheSizeY, cacheSizeZ);
			final int[] packedLightCacheArray = packedLightCache.getPackedLightCache();
			final IBlockState[] stateCacheArray = stateCache.getBlockStateCache();

			int index = 0;
			for (int z = 0; z < cacheSizeZ; ++z) {
				for (int y = 0; y < cacheSizeY; ++y) {
					for (int x = 0; x < cacheSizeX; ++x, ++index) {
						packedLightCacheArray[index] = stateCacheArray[stateCache.getIndex(x, y, z)].getPackedLightmapCoords(cache, pooledMutableBlockPos.setPos(startPosX + x, startPosY + y, startPosZ + z));
//						try {
//							pooledMutableBlockPos.setPos(startPosX + x, startPosY + y, startPosZ + z);
//							packedLightCacheArray[index] = cache.getCombinedLight(pooledMutableBlockPos, stateCacheArray[stateCache.getIndex(x, y, z)].getBlock().getLightValue(null));
//							packedLightCacheArray[index] = DynamicLights.getCombinedLight(pooledMutableBlockPos, 0);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					}
				}
			}
			return packedLightCache;
		}
	}

}
