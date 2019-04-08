package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.mesh;

import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.CacheUtil;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.NoCubesTest;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.mesh.generator.OldNoCubes;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.Face;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.FaceList;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.Vec3;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.Vec3b;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache.DensityCache;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache.SmoothableCache;
import io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.pooled.cache.StateCache;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.PooledMutableBlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import java.util.HashMap;

import static io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes.CacheUtil.getIndividualBlockDensity;

/**
 * @author Cadiboo
 */
public class MeshDispatcher {

	@Nonnull
	public HashMap<Vec3b, FaceList> generateChunkMeshOffset(
			@Nonnull final BlockPos chunkPos,
			@Nonnull final IBlockReader blockAccess,
//			@Nonnull final IIsSmoothable isSmoothable,
			@Nonnull final MeshGenerator meshGenerator
	) {
		if (meshGenerator == MeshGenerator.OldNoCubes) {
			return generateChunkMeshOffsetOldNoCubes(chunkPos, blockAccess);
		}

		final HashMap<Vec3b, FaceList> chunkData = generateChunkMeshUnOffset(chunkPos, blockAccess, meshGenerator);
		return offsetChunkMesh(chunkPos, chunkData);
	}

	/**
	 * @param chunkPos
	 * @param blockAccess
	 * @param isSmoothable
	 * @return the un offset vertices for the chunk
	 */
	@Nonnull
	public HashMap<Vec3b, FaceList> generateChunkMeshUnOffset(
			@Nonnull final BlockPos chunkPos,
			@Nonnull final IBlockReader blockAccess,
//			@Nonnull final IIsSmoothable isSmoothable,
			@Nonnull MeshGenerator meshGenerator
	) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("generateChunkMeshUnOffset"))
		{

			try (final PooledMutableBlockPos pooledMutableBlockPos = PooledMutableBlockPos.retain()) {
				final byte meshSizeX;
				final byte meshSizeY;
				final byte meshSizeZ;
				switch (meshGenerator) {
					// Yay, Surface Nets is special and needs an extra +1. Why? No-one knows :/
					case SurfaceNets:
						meshSizeX = 18;
						meshSizeY = 18;
						meshSizeZ = 18;
						break;
					default:
						meshSizeX = 17;
						meshSizeY = 17;
						meshSizeZ = 17;
						break;
					case OldNoCubes:
						throw new UnsupportedOperationException();
				}

				return generateCachesAndChunkData(chunkPos, blockAccess, pooledMutableBlockPos, meshSizeX, meshSizeY, meshSizeZ, meshGenerator);
			}
		}
	}

	@Nonnull
	protected HashMap<Vec3b, FaceList> generateCachesAndChunkData(
			@Nonnull final BlockPos chunkPos,
			@Nonnull final IBlockReader blockAccess,
//			@Nonnull final IIsSmoothable isSmoothable,
			@Nonnull final PooledMutableBlockPos pooledMutableBlockPos,
			final byte meshSizeX, final byte meshSizeY, final byte meshSizeZ,
			@Nonnull final MeshGenerator meshGenerator
	) {
		final int chunkPosX = chunkPos.getX();
		final int chunkPosY = chunkPos.getY();
		final int chunkPosZ = chunkPos.getZ();

		try (final StateCache stateCache = generateMeshStateCache(chunkPosX, chunkPosY, chunkPosZ, meshSizeX, meshSizeY, meshSizeZ, blockAccess, pooledMutableBlockPos)) {
//			NoCubes.getProfiler().start("generateMeshChunkSmoothableCache");
			try (final SmoothableCache smoothableCache = CacheUtil.generateSmoothableCache(stateCache)) {
//				NoCubes.getProfiler().end();
//				NoCubes.getProfiler().start("generateMeshChunkDensityCache");
				try (final DensityCache densityCache = CacheUtil.generateDensityCache(chunkPosX, chunkPosY, chunkPosZ, stateCache, smoothableCache, blockAccess, pooledMutableBlockPos)) {
//					NoCubes.getProfiler().end();
					return meshGenerator.generateChunk(densityCache.getDensityCache(), meshSizeX, meshSizeY, meshSizeZ);
				}
			}
		}
	}

	@Nonnull
	protected HashMap<Vec3b, FaceList> generateChunkMeshOffsetOldNoCubes(@Nonnull final BlockPos chunkPos, @Nonnull final IBlockReader blockAccess) {
		try (PooledMutableBlockPos pooledMutableBlockPos = PooledMutableBlockPos.retain()) {
			return OldNoCubes.generateChunk(chunkPos, blockAccess, pooledMutableBlockPos);
		}
	}

	@Nonnull
	protected StateCache generateMeshStateCache(
			final int startPosX, final int startPosY, final int startPosZ,
			final int meshSizeX, final int meshSizeY, final int meshSizeZ,
			@Nonnull final IBlockReader blockAccess,
			@Nonnull final PooledMutableBlockPos pooledMutableBlockPos
	) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("generateMeshStateCache"))
		{
			// Density takes +1 block on every negative axis into account so we need to start at -1 block
			final int cacheStartPosX = startPosX - 1;
			final int cacheStartPosY = startPosY - 1;
			final int cacheStartPosZ = startPosZ - 1;

			// Density takes +1 block on every negative axis into account so we need to add 1 to the size of the cache (it only takes +1 on NEGATIVE axis)
			final int cacheSizeX = meshSizeX + 1;
			final int cacheSizeY = meshSizeY + 1;
			final int cacheSizeZ = meshSizeZ + 1;

			return CacheUtil.generateStateCache(
					cacheStartPosX, cacheStartPosY, cacheStartPosZ,
					cacheSizeX, cacheSizeY, cacheSizeZ,
					blockAccess,
					pooledMutableBlockPos
			);
		}
	}

	/**
	 * @param pos
	 * @param blockAccess
	 * @param isSmoothable
	 * @return the offset vertices for the block
	 */
	@Nonnull
	public FaceList generateBlockMeshOffset(@Nonnull final BlockPos pos, @Nonnull final IBlockReader blockAccess, @Nonnull final MeshGenerator meshGenerator) {

		if (meshGenerator == MeshGenerator.OldNoCubes) {
			return generateOffsetBlockOldNoCubes(pos, blockAccess);
		}

		final FaceList chunkData = generateBlockMeshUnOffset(pos, blockAccess, meshGenerator);

		final int chunkPosX = (pos.getX() >> 4) << 4;
		final int chunkPosY = (pos.getY() >> 4) << 4;
		final int chunkPosZ = (pos.getZ() >> 4) << 4;

		return offsetBlockMesh(chunkPosX, chunkPosY, chunkPosZ, chunkData);
	}

	/**
	 * @param pos
	 * @param blockAccess
	 * @param isSmoothable
	 * @param meshGenerator
	 * @return the un offset vertices for the block
	 */
	@Nonnull
	public FaceList generateBlockMeshUnOffset(@Nonnull final BlockPos pos, @Nonnull final IBlockReader blockAccess, @Nonnull final MeshGenerator meshGenerator) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("generateBlock"))
//		final ModProfiler ignored = NoCubes.getProfiler();
		{

//			if(true)
//				return meshGenerator.generateBlock(pos, blockAccess, isSmoothable);

			try (final PooledMutableBlockPos pooledMutableBlockPos = PooledMutableBlockPos.retain()) {
				final int posX = pos.getX();
				final int posY = pos.getY();
				final int posZ = pos.getZ();

				// Convert block pos to relative block pos
				// For example 68 -> 4, 127 -> 15, 4 -> 4, 312312312 -> 8
				final int relativePosX = posX & 15;
				final int relativePosY = posY & 15;
				final int relativePosZ = posZ & 15;

				final byte addX;
				final byte addY;
				final byte addZ;
				final byte subX;
				final byte subY;
				final byte subZ;
				//FFS
				if (meshGenerator == MeshGenerator.MarchingCubes) {
					addX = 1;
					addY = 1;
					addZ = 1;
					subX = 0;
					subY = 0;
					subZ = 0;
				} else if (meshGenerator == MeshGenerator.MarchingTetrahedra) {
					addX = 1;
					addY = 1;
					addZ = 1;
					subX = 0;
					subY = 0;
					subZ = 0;
				} else if (meshGenerator == MeshGenerator.SurfaceNets) {
					addX = 0;
					addY = 0;
					addZ = 0;
					subX = 1;
					subY = 1;
					subZ = 1;
				} else {
					addX = 0;
					addY = 0;
					addZ = 0;
					subX = 0;
					subY = 0;
					subZ = 0;
				}

				final byte meshSizeX = (byte) (2 + addX + subX + meshGenerator.getSizeXExtension());
				final byte meshSizeY = (byte) (2 + addY + subY + meshGenerator.getSizeYExtension());
				final byte meshSizeZ = (byte) (2 + addZ + subZ + meshGenerator.getSizeZExtension());

				final float[] densityData = new float[meshSizeX * meshSizeY * meshSizeZ];

				final int startPosX = posX - subX;
				final int startPosY = posY - subY;
				final int startPosZ = posZ - subZ;

				int index = 0;
				for (int z = 0; z < meshSizeX; ++z) {
					for (int y = 0; y < meshSizeY; ++y) {
						for (int x = 0; x < meshSizeZ; ++x, ++index) {

							float density = 0;
							for (int zOffset = 0; zOffset < 2; ++zOffset) {
								for (int yOffset = 0; yOffset < 2; ++yOffset) {
									for (int xOffset = 0; xOffset < 2; ++xOffset) {

										pooledMutableBlockPos.setPos(startPosX + x - xOffset, startPosY + y - yOffset, startPosZ + z - zOffset);
										final IBlockState state = blockAccess.getBlockState(pooledMutableBlockPos);
										density += getIndividualBlockDensity(NoCubesTest.isSmoothable(state), state, blockAccess, pooledMutableBlockPos);
									}
								}
							}
							densityData[index] = density;

						}
					}
				}

				FaceList finalFaces = FaceList.retain();
				final HashMap<Vec3b, FaceList> vec3bFaceListHashMap = meshGenerator.generateChunk(densityData, meshSizeX, meshSizeY, meshSizeZ);
				for (final FaceList generatedFaceList : vec3bFaceListHashMap.values()) {
					finalFaces.addAll(generatedFaceList);
					generatedFaceList.close();
				}
				for (final Vec3b vec3b : vec3bFaceListHashMap.keySet()) {
					vec3b.close();
				}

				for (Face face : finalFaces) {
					final Vec3 vertex0 = face.getVertex0();
					final Vec3 vertex1 = face.getVertex1();
					final Vec3 vertex2 = face.getVertex2();
					final Vec3 vertex3 = face.getVertex3();

					vertex0.addOffset(relativePosX, relativePosY, relativePosZ);
					vertex1.addOffset(relativePosX, relativePosY, relativePosZ);
					vertex2.addOffset(relativePosX, relativePosY, relativePosZ);
					vertex3.addOffset(relativePosX, relativePosY, relativePosZ);

					vertex0.addOffset(-subX, -subY, -subZ);
					vertex1.addOffset(-subX, -subY, -subZ);
					vertex2.addOffset(-subX, -subY, -subZ);
					vertex3.addOffset(-subX, -subY, -subZ);

				}

				return finalFaces;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	@Nonnull
	public float[] generateNeighbourDensityGrid(@Nonnull final DensityCache densityCache) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("generateNeighbourDensityGrid"))
		{
			final float[] neighbourDensityGrid = new float[8];

			final float[] densityCacheArray = densityCache.getDensityCache();

			int neighbourDensityGridIndex = 0;
			for (int zOffset = 0; zOffset < 2; ++zOffset) {
				for (int yOffset = 0; yOffset < 2; ++yOffset) {
					for (byte xOffset = 0; xOffset < 2; ++xOffset, ++neighbourDensityGridIndex) {
						neighbourDensityGrid[neighbourDensityGridIndex] = densityCacheArray[densityCache.getIndex(xOffset, yOffset, zOffset)];
					}
				}
			}
			return neighbourDensityGrid;
		}
	}

	@Nonnull
	protected FaceList generateOffsetBlockOldNoCubes(@Nonnull final BlockPos blockPos, @Nonnull final IBlockReader blockAccess) {
		try (PooledMutableBlockPos pooledMutableBlockPos = PooledMutableBlockPos.retain()) {
			return OldNoCubes.generateBlock(blockPos, blockAccess, pooledMutableBlockPos);
		}
	}

	/**
	 * Modifies the chunk data mesh in! Returns the offset mesh for convenience
	 * Offsets the data from relative pos to real pos and applies offsetVertices
	 */
	@Nonnull
	public HashMap<Vec3b, FaceList> offsetChunkMesh(@Nonnull final BlockPos chunkPos, @Nonnull final HashMap<Vec3b, FaceList> chunkData) {
		for (FaceList faces : chunkData.values()) {
			offsetBlockMesh(chunkPos.getX(), chunkPos.getY(), chunkPos.getZ(), faces);
		}
		return chunkData;
	}

	/**
	 * Modifies the block mesh passed in! Returns the offset mesh for convenience
	 * Offsets the data from relative pos to real pos and applies offsetVertices
	 */
	@Nonnull
	public FaceList offsetBlockMesh(final int chunkPosX, final int chunkPosY, final int chunkPosZ, @Nonnull final FaceList faces) {
		for (Face face : faces) {
			final Vec3 vertex0 = face.getVertex0();
			final Vec3 vertex1 = face.getVertex1();
			final Vec3 vertex2 = face.getVertex2();
			final Vec3 vertex3 = face.getVertex3();

			vertex0.addOffset(chunkPosX, chunkPosY, chunkPosZ);
			vertex1.addOffset(chunkPosX, chunkPosY, chunkPosZ);
			vertex2.addOffset(chunkPosX, chunkPosY, chunkPosZ);
			vertex3.addOffset(chunkPosX, chunkPosY, chunkPosZ);

//			if (ModConfig.offsetVertices) {
//				ModUtil.offsetVertex(vertex0);
//				ModUtil.offsetVertex(vertex1);
//				ModUtil.offsetVertex(vertex2);
//				ModUtil.offsetVertex(vertex3);
//			}
		}
		return faces;
	}

}
