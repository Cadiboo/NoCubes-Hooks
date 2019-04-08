package io.github.cadiboo.renderchunkrebuildchunkhooks.nocubes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.property.IExtendedBlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static net.minecraft.block.BlockDirtSnowy.SNOWY;
import static net.minecraft.init.Blocks.GRASS_BLOCK;
import static net.minecraft.init.Blocks.PODZOL;
import static net.minecraft.init.Blocks.SNOW;
import static net.minecraft.util.EnumFacing.DOWN;
import static net.minecraft.util.EnumFacing.EAST;
import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;
import static net.minecraft.util.EnumFacing.UP;
import static net.minecraft.util.EnumFacing.WEST;
import static net.minecraft.util.math.MathHelper.getPositionRandom;

/**
 * @author Cadiboo
 */
public class ModelHelper {

	/**
	 * The order of {@link EnumFacing} and null used in getQuads
	 */
	public static final EnumFacing[] ENUMFACING_QUADS_ORDERED = {
			UP, null, DOWN, NORTH, EAST, SOUTH, WEST,
	};

	/**
	 * Gets The first quad of a model for a pos & state or null if the model has no quads
	 *
	 * @param state                   the state
	 * @param pos                     the position used in {@link MathHelper#getPositionRandom(Vec3i)}
	 * @param blockRendererDispatcher the {@link BlockRendererDispatcher} to get the model from
	 * @return The first quad or null if the model has no quads
	 */
	@Nullable
	public static BakedQuad getQuadFromFacingsOrdered(final IBlockState state, final BlockPos pos, final BlockRendererDispatcher blockRendererDispatcher, final Random random) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getQuadFromFacingsOrdered"))
		{
			final IBakedModel model = getModel(state, blockRendererDispatcher);
			return getModelQuadsFromFacings(state, random, model, ENUMFACING_QUADS_ORDERED);
		}
	}

	/**
	 * Gets The first quad of a model for a pos & state or null if the model has no quads
	 *
	 * @param state                   the state
	 * @param pos                     the position used in {@link MathHelper#getPositionRandom(Vec3i)}
	 * @param blockRendererDispatcher the {@link BlockRendererDispatcher} to get the model from
	 * @param facing                  the {@link EnumFacing to check first}
	 * @return The first quad or null if the model has no quads
	 */
	@Nullable
	public static BakedQuad getQuadFromFacingOrFacingsOrdered(final IBlockState state, final BlockPos pos, final BlockRendererDispatcher blockRendererDispatcher, final EnumFacing facing, final Random rand) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getQuadFromFacingOrFacingsOrdered"))
		{
			final IBakedModel model = getModel(state, blockRendererDispatcher);
			final BakedQuad quad = getModelQuadsFromFacings(state, rand, model, facing);
			if (quad != null) {
				return quad;
			} else {
				return getModelQuadsFromFacings(state, rand, model, ENUMFACING_QUADS_ORDERED);
			}
		}
	}

	@Nullable
	public static BakedQuad getModelQuadsFromFacings(final IBlockState state, final Random posRand, final IBakedModel model, final EnumFacing... facings) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getModelQuadsFromFacings"))
		{
			for (EnumFacing facing : facings) {
				final List<BakedQuad> quads = model.getQuads(state, facing, posRand);
				if (!quads.isEmpty()) {
					return quads.get(0);
				}
			}
			return null;
		}
	}

	//get model with pos
	//get override with pos

	@Nullable
	public static BakedQuad getQuad(IBlockState state, final BlockPos pos, final BufferBuilder bufferBuilder, final IBlockReader blockAccess, final BlockRendererDispatcher blockRendererDispatcher, final BlockRenderLayer blockRenderLayer) {

//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getActualState")) {
//			try {
//				state = state.getActualState(blockAccess, pos);
//			} catch (Exception ignored1) {
//			}
//		}

		IBakedModel model = getModel(state, blockRendererDispatcher);

		Object renderEnv = null;

//		if (OPTIFINE_INSTALLED) {
////		    RenderEnv renderEnv = bufferBuilder.getRenderEnv(blockAccess, state, pos);
//			renderEnv = BufferBuilderOF.getRenderEnv(bufferBuilder, blockAccess, state, pos);
//
//			model = BlockModelCustomizer.getRenderModel(model, state, renderEnv);
//		}
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getExtendedState"))
		{
			state = state.getBlock().getExtendedState(state, blockAccess, pos);
		}

		final Random posRand = new Random(getPositionRandom(pos));

		for (EnumFacing facing : ENUMFACING_QUADS_ORDERED) {
			List<BakedQuad> quads = model.getQuads(state, facing, posRand);
			if (quads.isEmpty()) {
				continue;
			}

//			if (OPTIFINE_INSTALLED) {
//				try (final ModProfiler ignored = NoCubes.getProfiler().start("getRenderQuads")) {
//					quads = BlockModelCustomizer.getRenderQuads(quads, blockAccess, state, pos, facing, blockRenderLayer, posRand, renderEnv);
//					if (quads.isEmpty()) {
//						continue;
//					}
//				}
//			}

			return quads.get(0);
		}

		return null;
	}

	/**
	 * Returns the model or the missing model if there isn't one
	 */
	@Nonnull
	public static IBakedModel getModel(final IBlockState state, final BlockRendererDispatcher blockRendererDispatcher) {
//		try (final ModProfiler ignored = NoCubes.getProfiler().start("getModel"))
		{
			IBlockState unextendedState = state;
			if (state instanceof IExtendedBlockState) {
				unextendedState = ((IExtendedBlockState) state).getClean();
			}
//			if (DynamicTreesCompatibility.isRootyBlock(unextendedState)) {
//				return blockRendererDispatcher.getModelForState(Blocks.GRASS.getDefaultState());
//			}
			if (unextendedState == GRASS_BLOCK.getDefaultState().with(SNOWY, true)) {
				return blockRendererDispatcher.getModelForState(SNOW.getDefaultState());
			}
			if (unextendedState == PODZOL.getDefaultState().with(SNOWY, true)) {
				return blockRendererDispatcher.getModelForState(SNOW.getDefaultState());
			}
			return blockRendererDispatcher.getModelForState(state);
		}
	}

}
