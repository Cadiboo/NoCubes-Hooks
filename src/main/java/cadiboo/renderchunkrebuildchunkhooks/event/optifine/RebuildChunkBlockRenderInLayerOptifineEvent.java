package cadiboo.renderchunkrebuildchunkhooks.event.optifine;

import cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockRenderInLayerEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;
import net.optifine.override.ChunkCacheOF;

/**
 * Called when a {@link RenderChunk#rebuildChunk RenderChunk.rebuildChunk} is called.<br>
 * This event is fired on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS EVENT_BUS} for every block inside the chunk to be rebuilt and for every {@link BlockRenderLayer BlockRenderLayer} the block renders in.<br>
 * Setting the result of this event to {@link Result#DENY} prevents the parts of the block in this {@link BlockRenderLayer BlockRenderLayer} from being rebuilt to the chunk (and therefore rendered).<br>
 * You can perform your own rendering in this event.<br>
 *
 * @author Cadiboo
 * @see RenderChunk#rebuildChunk(float, float, float, ChunkCompileTaskGenerator)
 */
@HasResult
public class RebuildChunkBlockRenderInLayerOptifineEvent extends RebuildChunkBlockRenderInLayerEvent {

	final ChunkCacheOF chunkCacheOF;

	/**
	 * @param renderChunk               the instance of {@link RenderChunk} the event is being fired for
	 * @param chunkCacheOF                 the {@link ChunkCacheOF} passed in from RenderChunk#rebuildChunk
	 * @param chunkCompileTaskGenerator the {@link ChunkCompileTaskGenerator} passed in from RenderChunk#rebuildChunk
	 * @param compiledChunk             the {@link CompiledChunk} passed in from RenderChunk#rebuildChunk
	 * @param blockRendererDispatcher   the {@link BlockRendererDispatcher} passed in from RenderChunk#rebuildChunk
	 * @param renderChunkPosition       the {@link MutableBlockPos position} passed in from RenderChunk#rebuildChunk
	 * @param visGraph                  the {@link VisGraph} passed in from RenderChunk#rebuildChunk
	 * @param blockPos                  the {@link MutableBlockPos position} of the block being assessed
	 * @param blockState                the {@link IBlockState state} of the block being assessed
	 * @param blockRenderLayer          the {@link BlockRenderLayer} of the block being assessed
	 */
	public RebuildChunkBlockRenderInLayerOptifineEvent(RenderChunk renderChunk, ChunkCacheOF chunkCacheOF, ChunkCompileTaskGenerator chunkCompileTaskGenerator, CompiledChunk compiledChunk, BlockRendererDispatcher blockRendererDispatcher, MutableBlockPos renderChunkPosition, VisGraph visGraph, MutableBlockPos blockPos, IBlockState blockState, BlockRenderLayer blockRenderLayer) {

		super(renderChunk, chunkCacheOF.chunkCache, chunkCompileTaskGenerator, compiledChunk, blockRendererDispatcher, renderChunkPosition, visGraph, blockPos, blockState, blockRenderLayer);
		this.chunkCacheOF = chunkCacheOF;
	}

	public ChunkCacheOF getChunkCacheOF() {

		return chunkCacheOF;
	}

}
