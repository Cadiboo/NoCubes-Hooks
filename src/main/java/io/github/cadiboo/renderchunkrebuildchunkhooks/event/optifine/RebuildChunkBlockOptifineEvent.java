package io.github.cadiboo.renderchunkrebuildchunkhooks.event.optifine;

import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockEvent;
import io.github.cadiboo.renderchunkrebuildchunkhooks.hooks.RenderChunkRebuildChunkHooksHooksOptifine;
import io.github.cadiboo.renderchunkrebuildchunkhooks.mod.EnumEventType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.optifine.BlockPosM;
import net.optifine.override.ChunkCacheOF;

import java.util.HashSet;

/**
 * Called when a {@link RenderChunk#rebuildChunk RenderChunk.rebuildChunk} is called when Optifine is present.<br>
 * This event is fired on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS EVENT_BUS} for every block inside the chunk to be rebuilt and for every {@link BlockRenderLayer BlockRenderLayer} the block renders in.<br>
 * Canceling this event prevents the block from being rebuilt to the chunk (and therefore rendered).<br>
 * You can perform your own rendering in this event.<br>
 *
 * @author Cadiboo
 * @see RenderChunk#rebuildChunk(float, float, float, ChunkCompileTaskGenerator)
 */
@Cancelable
public class RebuildChunkBlockOptifineEvent extends RebuildChunkBlockEvent {

	private final ChunkCacheOF chunkCacheOF;
	private final BlockPosM blockPosM;

	/**
	 * @param renderChunk                     the instance of {@link RenderChunk} the event is being fired for
	 * @param renderGlobal                    the {@link RenderGlobal} passed in
	 * @param chunkCacheOF                    the {@link ChunkCacheOF} passed in from RenderChunk#rebuildChunk
	 * @param generator                       the {@link ChunkCompileTaskGenerator} passed in
	 * @param compiledchunk                   the {@link CompiledChunk} passed in
	 * @param blockRendererDispatcher         the {@link BlockRendererDispatcher} passed in
	 * @param blockState                      the {@link IBlockState state} of the block being rendered
	 * @param blockPosM                       the {@link BlockPosM position} of the block being rendered
	 * @param bufferBuilder                   the {@link BufferBuilder} for the BlockRenderLayer
	 * @param renderChunkPosition             the {@link MutableBlockPos position} passed in
	 * @param usedBlockRenderLayers           the array of {@link BlockRenderLayer} that are being used
	 * @param blockRenderLayer                the {@link BlockRenderLayer} of the block being rendered
	 * @param x                               the translation X passed in
	 * @param y                               the translation Y passed in
	 * @param z                               the translation Z passed in
	 * @param tileEntitiesWithGlobalRenderers the {@link HashSet} of {@link TileEntity TileEntities} with global renderers passed in
	 * @param visGraph                        the {@link VisGraph} passed in
	 */
	public RebuildChunkBlockOptifineEvent(RenderChunk renderChunk, RenderGlobal renderGlobal, ChunkCacheOF chunkCacheOF, ChunkCompileTaskGenerator generator, CompiledChunk compiledchunk, BlockRendererDispatcher blockRendererDispatcher, IBlockState blockState, BlockPosM blockPosM, BufferBuilder bufferBuilder, MutableBlockPos renderChunkPosition, boolean[] usedBlockRenderLayers, BlockRenderLayer blockRenderLayer, float x, float y, float z, HashSet<TileEntity> tileEntitiesWithGlobalRenderers,
	                                      VisGraph visGraph) {
		super(renderChunk, renderGlobal, RenderChunkRebuildChunkHooksHooksOptifine.getChunkCacheFromChunkCacheOF(chunkCacheOF), generator, compiledchunk, blockRendererDispatcher, blockState, new MutableBlockPos(blockPosM), bufferBuilder, renderChunkPosition, usedBlockRenderLayers, blockRenderLayer, x, y, z, tileEntitiesWithGlobalRenderers, visGraph);
		this.chunkCacheOF = chunkCacheOF;
		this.blockPosM = blockPosM;
	}

	/**
	 * @return the type of event
	 */
	public EnumEventType getType() {
		return EnumEventType.FORGE_OPTIFINE;
	}

	/**
	 * @return the {@link ChunkCacheOF} passed in
	 */
	public ChunkCacheOF getChunkCacheOF() {
		return chunkCacheOF;
	}

	/**
	 * @return the {@link BlockPosM} passed in
	 */
	public BlockPosM getBlockPosM() {
		return blockPosM;
	}

}
