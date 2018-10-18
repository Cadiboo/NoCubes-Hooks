package cadiboo.renderchunkrebuildchunkhooks;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.launchwrapper.IClassTransformer;

public class RenderChunkRebuildChunkHooksRenderChunkClassTransformerCheat implements IClassTransformer {

	public static final List<String> IGNORED_PREFIXES = ImmutableList.of("cpw", "net.minecraftforge", "io", "org", "gnu", "com", "joptsimple");

	public static final int FLAGS = ClassWriter.COMPUTE_FRAMES;// ClassWriter.COMPUTE_MAXS;

	public boolean isDeobfuscated = false;

	@Override
	public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
		if (!name.equals("net.minecraft.client.renderer.chunk.RenderChunk") && !name.equals("cws")) {
			return basicClass;
		}

		this.isDeobfuscated = name.equals("net.minecraft.client.renderer.chunk.RenderChunk");

		final ClassReader classReader = new ClassReader(basicClass);
		final ClassWriter classWriter = new ClassWriter(classReader, FLAGS);

		LogManager.getLogger().info("creating custom method visitor to make the following hooks. If you don't see output that the hook was created theres an error");
		LogManager.getLogger().info("RebuildChunkEvent hook (rewrite method)");
		LogManager.getLogger().info("RebuildChunkBlocksEvent hook (rewrite method)");
		LogManager.getLogger().info("RebuildChunkBlockEvent hook (rewrite method)");

		final ClassVisitor classVisitor = new RebuildChunkHooksClassVisitor(classWriter);

		LogManager.getLogger().info("Attempting to make hooks...");

		try {
			classReader.accept(classVisitor, 0);
			LogManager.getLogger().info("Made hooks!");
			return classWriter.toByteArray();
		} catch (final Exception e) {
			LogManager.getLogger().error("FAILED to make hooks!!!");
		}

		return basicClass;
	}

	public class RebuildChunkHooksClassVisitor extends ClassVisitor {

		public final Type	REBUILD_CHUNK_TYPE			= Type.getMethodType(Type.VOID_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.getObjectType(Type.getInternalName(ChunkCompileTaskGenerator.class)));
		public final String	REBUILD_CHUNK_DESCRIPTOR	= this.REBUILD_CHUNK_TYPE.getDescriptor();

		public RebuildChunkHooksClassVisitor(final ClassVisitor classVisitor) {
			super(Opcodes.ASM5, classVisitor);
		}

		@Override
		public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {

			final MethodVisitor originalVisitor = super.visitMethod(access, name, desc, signature, exceptions);

			if (!name.equals("rebuildChunk") && !name.equals("func_178581_b")) {
				return originalVisitor;
			}

			return new RebuildChunkHooksMethodVisitor(originalVisitor);

		}

	}

	public class RebuildChunkHooksMethodVisitor extends MethodVisitor {

		public RebuildChunkHooksMethodVisitor(final MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {

			final int ASTORE = Opcodes.ASTORE;
			final int NEW = Opcodes.NEW;
			final int DUP = Opcodes.DUP;
			final int INVOKESPECIAL = Opcodes.INVOKESPECIAL;
			final int ICONST_1 = Opcodes.ICONST_1;
			final int ISTORE = Opcodes.ISTORE;
			final int GETFIELD = Opcodes.GETFIELD;
			final int ALOAD = Opcodes.ALOAD;
			final int BIPUSH = Opcodes.BIPUSH;
			final int INVOKEVIRTUAL = Opcodes.INVOKEVIRTUAL;
			final int GETSTATIC = Opcodes.GETSTATIC;
			final int IF_ACMPEQ = Opcodes.IF_ACMPEQ;
			final int RETURN = Opcodes.RETURN;
			final int ATHROW = Opcodes.ATHROW;
			final int GOTO = Opcodes.GOTO;
			final int FLOAD = Opcodes.FLOAD;
			final int INVOKESTATIC = Opcodes.INVOKESTATIC;
			final int IFEQ = Opcodes.IFEQ;
			final int IFNE = Opcodes.IFNE;
			final int IADD = Opcodes.IADD;
			final int INVOKEINTERFACE = Opcodes.INVOKEINTERFACE;
			final int IFNULL = Opcodes.IFNULL;
			final int CHECKCAST = Opcodes.CHECKCAST;
			final int ILOAD = Opcodes.ILOAD;
			final int PUTSTATIC = Opcodes.PUTSTATIC;
			final int ARRAYLENGTH = Opcodes.ARRAYLENGTH;
			final int POP = Opcodes.POP;
			final int AALOAD = Opcodes.AALOAD;
			final int ICONST_0 = Opcodes.ICONST_0;
			final int BALOAD = Opcodes.BALOAD;
			final int DUP2 = Opcodes.DUP2;
			final int IF_ICMPLT = Opcodes.IF_ICMPLT;
			final int IOR = Opcodes.IOR;
			final int BASTORE = Opcodes.BASTORE;
			final int ACONST_NULL = Opcodes.ACONST_NULL;

			// TODO: obfuscated environment (actually don't, write the mod properly and don't take this cheaty way!)

//			this.mv.visitCode();
			final Label l0 = new Label();
			final Label l1 = new Label();
			final Label l2 = new Label();
			this.mv.visitTryCatchBlock(l0, l1, l2, null);
			final Label l3 = new Label();
			this.mv.visitTryCatchBlock(l3, l2, l2, null);
			final Label l4 = new Label();
			final Label l5 = new Label();
			this.mv.visitTryCatchBlock(l4, l5, l5, null);
			final Label l6 = new Label();
			this.mv.visitLabel(l6);
			this.mv.visitLineNumber(116, l6);
			this.mv.visitTypeInsn(NEW, "net/minecraft/client/renderer/chunk/CompiledChunk");
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "<init>", "()V", false);
			this.mv.visitVarInsn(ASTORE, 5);
			final Label l7 = new Label();
			this.mv.visitLabel(l7);
			this.mv.visitLineNumber(117, l7);
			this.mv.visitInsn(ICONST_1);
			this.mv.visitVarInsn(ISTORE, 6);
			final Label l8 = new Label();
			this.mv.visitLabel(l8);
			this.mv.visitLineNumber(118, l8);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "position", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;");
			this.mv.visitVarInsn(ASTORE, 7);
			final Label l9 = new Label();
			this.mv.visitLabel(l9);
			this.mv.visitLineNumber(119, l9);
			this.mv.visitVarInsn(ALOAD, 7);
			this.mv.visitIntInsn(BIPUSH, 15);
			this.mv.visitIntInsn(BIPUSH, 15);
			this.mv.visitIntInsn(BIPUSH, 15);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/util/math/BlockPos", "add", "(III)Lnet/minecraft/util/math/BlockPos;", false);
			this.mv.visitVarInsn(ASTORE, 8);
			final Label l10 = new Label();
			this.mv.visitLabel(l10);
			this.mv.visitLineNumber(120, l10);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "lock", "()V", false);
			this.mv.visitLabel(l0);
			this.mv.visitLineNumber(123, l0);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getStatus", "()Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator$Status;", false);
			this.mv.visitFieldInsn(GETSTATIC, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator$Status", "COMPILING", "Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator$Status;");
			this.mv.visitJumpInsn(IF_ACMPEQ, l3);
			this.mv.visitLabel(l1);
			this.mv.visitLineNumber(129, l1);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "unlock", "()V", false);
			final Label l11 = new Label();
			this.mv.visitLabel(l11);
			this.mv.visitLineNumber(124, l11);
			this.mv.visitInsn(RETURN);
			this.mv.visitLabel(l3);
			this.mv.visitLineNumber(127, l3);
			this.mv.visitFrame(Opcodes.F_FULL, 9, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "setCompiledChunk", "(Lnet/minecraft/client/renderer/chunk/CompiledChunk;)V", false);
			final Label l12 = new Label();
			this.mv.visitLabel(l12);
			this.mv.visitLineNumber(128, l12);
			final Label l13 = new Label();
			this.mv.visitJumpInsn(GOTO, l13);
			this.mv.visitLabel(l2);
			this.mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { "java/lang/Throwable" });
			this.mv.visitVarInsn(ASTORE, 9);
			final Label l14 = new Label();
			this.mv.visitLabel(l14);
			this.mv.visitLineNumber(129, l14);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "unlock", "()V", false);
			final Label l15 = new Label();
			this.mv.visitLabel(l15);
			this.mv.visitLineNumber(130, l15);
			this.mv.visitVarInsn(ALOAD, 9);
			this.mv.visitInsn(ATHROW);
			this.mv.visitLabel(l13);
			this.mv.visitLineNumber(129, l13);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "unlock", "()V", false);
			final Label l16 = new Label();
			this.mv.visitLabel(l16);
			this.mv.visitLineNumber(131, l16);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "renderGlobal", "Lnet/minecraft/client/renderer/RenderGlobal;");
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "position", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;");
			this.mv.visitVarInsn(FLOAD, 1);
			this.mv.visitVarInsn(FLOAD, 2);
			this.mv.visitVarInsn(FLOAD, 3);
			this.mv.visitMethodInsn(INVOKESTATIC, "cadiboo/renderchunkrebuildchunkhooks/hooks/RenderChunkRebuildChunkHooksHooks", "onRebuildChunkEvent", "(Lnet/minecraft/client/renderer/RenderGlobal;Lnet/minecraft/world/ChunkCache;Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;Lnet/minecraft/client/renderer/chunk/CompiledChunk;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;FFF)Z", false);
			final Label l17 = new Label();
			this.mv.visitJumpInsn(IFEQ, l17);
			final Label l18 = new Label();
			this.mv.visitLabel(l18);
			this.mv.visitLineNumber(132, l18);
			this.mv.visitInsn(RETURN);
			this.mv.visitLabel(l17);
			this.mv.visitLineNumber(134, l17);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitTypeInsn(NEW, "net/minecraft/client/renderer/chunk/VisGraph");
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/renderer/chunk/VisGraph", "<init>", "()V", false);
			this.mv.visitVarInsn(ASTORE, 9);
			final Label l19 = new Label();
			this.mv.visitLabel(l19);
			this.mv.visitLineNumber(135, l19);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Sets", "newHashSet", "()Ljava/util/HashSet;", false);
			this.mv.visitVarInsn(ASTORE, 10);
			final Label l20 = new Label();
			this.mv.visitLabel(l20);
			this.mv.visitLineNumber(137, l20);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/ChunkCache", "isEmpty", "()Z", false);
			final Label l21 = new Label();
			this.mv.visitJumpInsn(IFNE, l21);
			final Label l22 = new Label();
			this.mv.visitLabel(l22);
			this.mv.visitLineNumber(138, l22);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "renderGlobal", "Lnet/minecraft/client/renderer/RenderGlobal;");
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 7);
			this.mv.visitVarInsn(ALOAD, 8);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/math/BlockPos", "getAllInBoxMutable", "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;", false);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/Minecraft", "getMinecraft", "()Lnet/minecraft/client/Minecraft;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", "getBlockRendererDispatcher", "()Lnet/minecraft/client/renderer/BlockRendererDispatcher;", false);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "position", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;");
			this.mv.visitVarInsn(FLOAD, 1);
			this.mv.visitVarInsn(FLOAD, 2);
			this.mv.visitVarInsn(FLOAD, 3);
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitVarInsn(ALOAD, 9);
			this.mv.visitMethodInsn(INVOKESTATIC, "cadiboo/renderchunkrebuildchunkhooks/hooks/RenderChunkRebuildChunkHooksHooks", "onRebuildChunkBlocksEvent",
					"(Lnet/minecraft/client/renderer/RenderGlobal;Lnet/minecraft/world/ChunkCache;Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;Lnet/minecraft/client/renderer/chunk/CompiledChunk;Ljava/lang/Iterable;Lnet/minecraft/client/renderer/BlockRendererDispatcher;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;FFFLjava/util/HashSet;Lnet/minecraft/client/renderer/chunk/VisGraph;)Lcadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent;",
					false);
			this.mv.visitVarInsn(ASTORE, 11);
			final Label l23 = new Label();
			this.mv.visitLabel(l23);
			this.mv.visitLineNumber(139, l23);
			this.mv.visitFieldInsn(GETSTATIC, "net/minecraft/client/renderer/chunk/RenderChunk", "renderChunksUpdated", "I");
			this.mv.visitInsn(ICONST_1);
			this.mv.visitInsn(IADD);
			this.mv.visitFieldInsn(PUTSTATIC, "net/minecraft/client/renderer/chunk/RenderChunk", "renderChunksUpdated", "I");
			final Label l24 = new Label();
			this.mv.visitLabel(l24);
			this.mv.visitLineNumber(140, l24);
			this.mv.visitVarInsn(ALOAD, 11);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "getUsedBlockRenderLayers", "()[Z", false);
			this.mv.visitVarInsn(ASTORE, 12);
			final Label l25 = new Label();
			this.mv.visitLabel(l25);
			this.mv.visitLineNumber(141, l25);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/client/Minecraft", "getMinecraft", "()Lnet/minecraft/client/Minecraft;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", "getBlockRendererDispatcher", "()Lnet/minecraft/client/renderer/BlockRendererDispatcher;", false);
			this.mv.visitVarInsn(ASTORE, 13);
			final Label l26 = new Label();
			this.mv.visitLabel(l26);
			this.mv.visitLineNumber(143, l26);
			this.mv.visitVarInsn(ALOAD, 7);
			this.mv.visitVarInsn(ALOAD, 8);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/math/BlockPos", "getAllInBoxMutable", "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Ljava/lang/Iterable;", false);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Iterable", "iterator", "()Ljava/util/Iterator;", true);
			this.mv.visitVarInsn(ASTORE, 15);
			final Label l27 = new Label();
			this.mv.visitJumpInsn(GOTO, l27);
			final Label l28 = new Label();
			this.mv.visitLabel(l28);
			this.mv.visitFrame(Opcodes.F_FULL, 16, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", Opcodes.TOP, "java/util/Iterator" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 15);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
			this.mv.visitTypeInsn(CHECKCAST, "net/minecraft/util/math/BlockPos$MutableBlockPos");
			this.mv.visitVarInsn(ASTORE, 14);
			final Label l29 = new Label();
			this.mv.visitLabel(l29);
			this.mv.visitLineNumber(144, l29);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/ChunkCache", "getBlockState", "(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;", false);
			this.mv.visitVarInsn(ASTORE, 16);
			final Label l30 = new Label();
			this.mv.visitLabel(l30);
			this.mv.visitLineNumber(145, l30);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/block/state/IBlockState", "getBlock", "()Lnet/minecraft/block/Block;", true);
			this.mv.visitVarInsn(ASTORE, 17);
			final Label l31 = new Label();
			this.mv.visitLabel(l31);
			this.mv.visitLineNumber(147, l31);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/block/state/IBlockState", "isOpaqueCube", "()Z", true);
			final Label l32 = new Label();
			this.mv.visitJumpInsn(IFEQ, l32);
			final Label l33 = new Label();
			this.mv.visitLabel(l33);
			this.mv.visitLineNumber(148, l33);
			this.mv.visitVarInsn(ALOAD, 9);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/VisGraph", "setOpaqueCube", "(Lnet/minecraft/util/math/BlockPos;)V", false);
			this.mv.visitLabel(l32);
			this.mv.visitLineNumber(151, l32);
			this.mv.visitFrame(Opcodes.F_FULL, 18, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 17);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "hasTileEntity", "(Lnet/minecraft/block/state/IBlockState;)Z", false);
			final Label l34 = new Label();
			this.mv.visitJumpInsn(IFEQ, l34);
			final Label l35 = new Label();
			this.mv.visitLabel(l35);
			this.mv.visitLineNumber(152, l35);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitFieldInsn(GETSTATIC, "net/minecraft/world/chunk/Chunk$EnumCreateEntityType", "CHECK", "Lnet/minecraft/world/chunk/Chunk$EnumCreateEntityType;");
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/world/ChunkCache", "getTileEntity", "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/chunk/Chunk$EnumCreateEntityType;)Lnet/minecraft/tileentity/TileEntity;", false);
			this.mv.visitVarInsn(ASTORE, 18);
			final Label l36 = new Label();
			this.mv.visitLabel(l36);
			this.mv.visitLineNumber(154, l36);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitJumpInsn(IFNULL, l34);
			final Label l37 = new Label();
			this.mv.visitLabel(l37);
			this.mv.visitLineNumber(155, l37);
			this.mv.visitFieldInsn(GETSTATIC, "net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher", "instance", "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;");
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher", "getRenderer", "(Lnet/minecraft/tileentity/TileEntity;)Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;", false);
			this.mv.visitVarInsn(ASTORE, 19);
			final Label l38 = new Label();
			this.mv.visitLabel(l38);
			this.mv.visitLineNumber(157, l38);
			this.mv.visitVarInsn(ALOAD, 19);
			this.mv.visitJumpInsn(IFNULL, l34);
			final Label l39 = new Label();
			this.mv.visitLabel(l39);
			this.mv.visitLineNumber(159, l39);
			this.mv.visitVarInsn(ALOAD, 19);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer", "isGlobalRenderer", "(Lnet/minecraft/tileentity/TileEntity;)Z", false);
			final Label l40 = new Label();
			this.mv.visitJumpInsn(IFEQ, l40);
			final Label l41 = new Label();
			this.mv.visitLabel(l41);
			this.mv.visitLineNumber(160, l41);
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashSet", "add", "(Ljava/lang/Object;)Z", false);
			this.mv.visitInsn(POP);
			final Label l42 = new Label();
			this.mv.visitLabel(l42);
			this.mv.visitLineNumber(161, l42);
			this.mv.visitJumpInsn(GOTO, l34);
			this.mv.visitLabel(l40);
			this.mv.visitLineNumber(162, l40);
			this.mv.visitFrame(Opcodes.F_APPEND, 2, new Object[] { "net/minecraft/tileentity/TileEntity", "net/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer" }, 0, null);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "addTileEntity", "(Lnet/minecraft/tileentity/TileEntity;)V", false);
			this.mv.visitLabel(l34);
			this.mv.visitLineNumber(168, l34);
			this.mv.visitFrame(Opcodes.F_CHOP, 2, null, 0, null);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/BlockRenderLayer", "values", "()[Lnet/minecraft/util/BlockRenderLayer;", false);
			this.mv.visitInsn(DUP);
			this.mv.visitVarInsn(ASTORE, 21);
			this.mv.visitInsn(ARRAYLENGTH);
			this.mv.visitVarInsn(ISTORE, 20);
			this.mv.visitInsn(ICONST_0);
			this.mv.visitVarInsn(ISTORE, 19);
			final Label l43 = new Label();
			this.mv.visitJumpInsn(GOTO, l43);
			final Label l44 = new Label();
			this.mv.visitLabel(l44);
			this.mv.visitFrame(Opcodes.F_FULL, 22, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 21);
			this.mv.visitVarInsn(ILOAD, 19);
			this.mv.visitInsn(AALOAD);
			this.mv.visitVarInsn(ASTORE, 18);
			final Label l45 = new Label();
			this.mv.visitLabel(l45);
			this.mv.visitLineNumber(169, l45);
			this.mv.visitVarInsn(ALOAD, 17);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "canRenderInLayer", "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockRenderLayer;)Z", false);
			final Label l46 = new Label();
			this.mv.visitJumpInsn(IFNE, l46);
			final Label l47 = new Label();
			this.mv.visitLabel(l47);
			this.mv.visitLineNumber(170, l47);
			final Label l48 = new Label();
			this.mv.visitJumpInsn(GOTO, l48);
			this.mv.visitLabel(l46);
			this.mv.visitLineNumber(172, l46);
			this.mv.visitFrame(Opcodes.F_FULL, 22, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", "net/minecraft/util/BlockRenderLayer", Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraftforge/client/ForgeHooksClient", "setRenderLayer", "(Lnet/minecraft/util/BlockRenderLayer;)V", false);
			final Label l49 = new Label();
			this.mv.visitLabel(l49);
			this.mv.visitLineNumber(173, l49);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/util/BlockRenderLayer", "ordinal", "()I", false);
			this.mv.visitVarInsn(ISTORE, 22);
			final Label l50 = new Label();
			this.mv.visitLabel(l50);
			this.mv.visitLineNumber(175, l50);
			this.mv.visitVarInsn(ALOAD, 17);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/block/Block", "getDefaultState", "()Lnet/minecraft/block/state/IBlockState;", false);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/block/state/IBlockState", "getRenderType", "()Lnet/minecraft/util/EnumBlockRenderType;", true);
			this.mv.visitFieldInsn(GETSTATIC, "net/minecraft/util/EnumBlockRenderType", "INVISIBLE", "Lnet/minecraft/util/EnumBlockRenderType;");
			this.mv.visitJumpInsn(IF_ACMPEQ, l48);
			final Label l51 = new Label();
			this.mv.visitLabel(l51);
			this.mv.visitLineNumber(176, l51);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getRegionRenderCacheBuilder", "()Lnet/minecraft/client/renderer/RegionRenderCacheBuilder;", false);
			this.mv.visitVarInsn(ILOAD, 22);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/RegionRenderCacheBuilder", "getWorldRendererByLayerId", "(I)Lnet/minecraft/client/renderer/BufferBuilder;", false);
			this.mv.visitVarInsn(ASTORE, 23);
			final Label l52 = new Label();
			this.mv.visitLabel(l52);
			this.mv.visitLineNumber(178, l52);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "isLayerStarted", "(Lnet/minecraft/util/BlockRenderLayer;)Z", false);
			final Label l53 = new Label();
			this.mv.visitJumpInsn(IFNE, l53);
			final Label l54 = new Label();
			this.mv.visitLabel(l54);
			this.mv.visitLineNumber(179, l54);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 18);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "setLayerStarted", "(Lnet/minecraft/util/BlockRenderLayer;)V", false);
			final Label l55 = new Label();
			this.mv.visitLabel(l55);
			this.mv.visitLineNumber(180, l55);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitVarInsn(ALOAD, 23);
			this.mv.visitVarInsn(ALOAD, 7);
			this.mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/renderer/chunk/RenderChunk", "preRenderBlocks", "(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/util/math/BlockPos;)V", false);
			this.mv.visitLabel(l53);
			this.mv.visitLineNumber(182, l53);
			this.mv.visitFrame(Opcodes.F_APPEND, 2, new Object[] { Opcodes.INTEGER, "net/minecraft/client/renderer/BufferBuilder" }, 0, null);
			this.mv.visitVarInsn(ALOAD, 11);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "isCanceled", "()Z", false);
			this.mv.visitJumpInsn(IFNE, l48);
			final Label l56 = new Label();
			this.mv.visitLabel(l56);
			this.mv.visitLineNumber(183, l56);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "renderGlobal", "Lnet/minecraft/client/renderer/RenderGlobal;");
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 13);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitVarInsn(ALOAD, 23);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "position", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;");
			this.mv.visitVarInsn(FLOAD, 1);
			this.mv.visitVarInsn(FLOAD, 2);
			this.mv.visitVarInsn(FLOAD, 3);
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitVarInsn(ALOAD, 9);
			this.mv.visitMethodInsn(INVOKESTATIC, "cadiboo/renderchunkrebuildchunkhooks/hooks/RenderChunkRebuildChunkHooksHooks", "onRebuildChunkBlockEvent",
					"(Lnet/minecraft/client/renderer/RenderGlobal;Lnet/minecraft/world/ChunkCache;Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;Lnet/minecraft/client/renderer/chunk/CompiledChunk;Lnet/minecraft/client/renderer/BlockRendererDispatcher;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;FFFLjava/util/HashSet;Lnet/minecraft/client/renderer/chunk/VisGraph;)Lcadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent;",
					false);
			this.mv.visitVarInsn(ASTORE, 24);
			final Label l57 = new Label();
			this.mv.visitLabel(l57);
			this.mv.visitLineNumber(184, l57);
			this.mv.visitVarInsn(ALOAD, 24);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent", "isCanceled", "()Z", false);
			final Label l58 = new Label();
			this.mv.visitJumpInsn(IFEQ, l58);
			final Label l59 = new Label();
			this.mv.visitLabel(l59);
			this.mv.visitLineNumber(185, l59);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/BlockRenderLayer", "values", "()[Lnet/minecraft/util/BlockRenderLayer;", false);
			this.mv.visitInsn(DUP);
			this.mv.visitVarInsn(ASTORE, 28);
			this.mv.visitInsn(ARRAYLENGTH);
			this.mv.visitVarInsn(ISTORE, 27);
			this.mv.visitInsn(ICONST_0);
			this.mv.visitVarInsn(ISTORE, 26);
			final Label l60 = new Label();
			this.mv.visitJumpInsn(GOTO, l60);
			final Label l61 = new Label();
			this.mv.visitLabel(l61);
			this.mv.visitFrame(Opcodes.F_FULL, 29,
					new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet", "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z",
							"net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", "net/minecraft/util/BlockRenderLayer", Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;", Opcodes.INTEGER, "net/minecraft/client/renderer/BufferBuilder", "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent",
							Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" },
					0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 28);
			this.mv.visitVarInsn(ILOAD, 26);
			this.mv.visitInsn(AALOAD);
			this.mv.visitVarInsn(ASTORE, 25);
			final Label l62 = new Label();
			this.mv.visitLabel(l62);
			this.mv.visitLineNumber(186, l62);
			this.mv.visitVarInsn(ALOAD, 24);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent", "getUsedBlockRenderLayers", "()[Z", false);
			this.mv.visitVarInsn(ALOAD, 25);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/util/BlockRenderLayer", "ordinal", "()I", false);
			this.mv.visitInsn(BALOAD);
			final Label l63 = new Label();
			this.mv.visitJumpInsn(IFEQ, l63);
			final Label l64 = new Label();
			this.mv.visitLabel(l64);
			this.mv.visitLineNumber(187, l64);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 25);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "setLayerUsed", "(Lnet/minecraft/util/BlockRenderLayer;)V", false);
			this.mv.visitLabel(l63);
			this.mv.visitLineNumber(185, l63);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitIincInsn(26, 1);
			this.mv.visitLabel(l60);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitVarInsn(ILOAD, 26);
			this.mv.visitVarInsn(ILOAD, 27);
			this.mv.visitJumpInsn(IF_ICMPLT, l61);
			final Label l65 = new Label();
			this.mv.visitLabel(l65);
			this.mv.visitLineNumber(190, l65);
			this.mv.visitJumpInsn(GOTO, l48);
			this.mv.visitLabel(l58);
			this.mv.visitLineNumber(191, l58);
			this.mv.visitFrame(Opcodes.F_FULL, 25,
					new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet", "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z",
							"net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", "net/minecraft/util/BlockRenderLayer", Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;", Opcodes.INTEGER, "net/minecraft/client/renderer/BufferBuilder", "cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent" },
					0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 12);
			this.mv.visitVarInsn(ILOAD, 22);
			this.mv.visitInsn(DUP2);
			this.mv.visitInsn(BALOAD);
			this.mv.visitVarInsn(ALOAD, 13);
			this.mv.visitVarInsn(ALOAD, 16);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			this.mv.visitVarInsn(ALOAD, 23);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/BlockRendererDispatcher", "renderBlock", "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)Z", false);
			this.mv.visitInsn(IOR);
			this.mv.visitInsn(BASTORE);
			this.mv.visitLabel(l48);
			this.mv.visitLineNumber(168, l48);
			this.mv.visitFrame(Opcodes.F_FULL, 22, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/math/BlockPos$MutableBlockPos", "java/util/Iterator", "net/minecraft/block/state/IBlockState", "net/minecraft/block/Block", Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitIincInsn(19, 1);
			this.mv.visitLabel(l43);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitVarInsn(ILOAD, 19);
			this.mv.visitVarInsn(ILOAD, 20);
			this.mv.visitJumpInsn(IF_ICMPLT, l44);
			final Label l66 = new Label();
			this.mv.visitLabel(l66);
			this.mv.visitLineNumber(196, l66);
			this.mv.visitInsn(ACONST_NULL);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraftforge/client/ForgeHooksClient", "setRenderLayer", "(Lnet/minecraft/util/BlockRenderLayer;)V", false);
			this.mv.visitLabel(l27);
			this.mv.visitLineNumber(143, l27);
			this.mv.visitFrame(Opcodes.F_FULL, 16, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", Opcodes.TOP, "java/util/Iterator" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 15);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
			this.mv.visitJumpInsn(IFNE, l28);
			final Label l67 = new Label();
			this.mv.visitLabel(l67);
			this.mv.visitLineNumber(199, l67);
			this.mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/util/BlockRenderLayer", "values", "()[Lnet/minecraft/util/BlockRenderLayer;", false);
			this.mv.visitInsn(DUP);
			this.mv.visitVarInsn(ASTORE, 17);
			this.mv.visitInsn(ARRAYLENGTH);
			this.mv.visitVarInsn(ISTORE, 16);
			this.mv.visitInsn(ICONST_0);
			this.mv.visitVarInsn(ISTORE, 15);
			final Label l68 = new Label();
			this.mv.visitJumpInsn(GOTO, l68);
			final Label l69 = new Label();
			this.mv.visitLabel(l69);
			this.mv.visitFrame(Opcodes.F_FULL, 18, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 17);
			this.mv.visitVarInsn(ILOAD, 15);
			this.mv.visitInsn(AALOAD);
			this.mv.visitVarInsn(ASTORE, 14);
			final Label l70 = new Label();
			this.mv.visitLabel(l70);
			this.mv.visitLineNumber(200, l70);
			this.mv.visitVarInsn(ALOAD, 12);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/util/BlockRenderLayer", "ordinal", "()I", false);
			this.mv.visitInsn(BALOAD);
			final Label l71 = new Label();
			this.mv.visitJumpInsn(IFEQ, l71);
			final Label l72 = new Label();
			this.mv.visitLabel(l72);
			this.mv.visitLineNumber(201, l72);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "setLayerUsed", "(Lnet/minecraft/util/BlockRenderLayer;)V", false);
			this.mv.visitLabel(l71);
			this.mv.visitLineNumber(204, l71);
			this.mv.visitFrame(Opcodes.F_FULL, 18, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", "net/minecraft/util/BlockRenderLayer", Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "isLayerStarted", "(Lnet/minecraft/util/BlockRenderLayer;)Z", false);
			final Label l73 = new Label();
			this.mv.visitJumpInsn(IFEQ, l73);
			final Label l74 = new Label();
			this.mv.visitLabel(l74);
			this.mv.visitLineNumber(205, l74);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitVarInsn(FLOAD, 1);
			this.mv.visitVarInsn(FLOAD, 2);
			this.mv.visitVarInsn(FLOAD, 3);
			this.mv.visitVarInsn(ALOAD, 4);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "getRegionRenderCacheBuilder", "()Lnet/minecraft/client/renderer/RegionRenderCacheBuilder;", false);
			this.mv.visitVarInsn(ALOAD, 14);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/RegionRenderCacheBuilder", "getWorldRendererByLayer", "(Lnet/minecraft/util/BlockRenderLayer;)Lnet/minecraft/client/renderer/BufferBuilder;", false);
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/client/renderer/chunk/RenderChunk", "postRenderBlocks", "(Lnet/minecraft/util/BlockRenderLayer;FFFLnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/client/renderer/chunk/CompiledChunk;)V", false);
			this.mv.visitLabel(l73);
			this.mv.visitLineNumber(199, l73);
			this.mv.visitFrame(Opcodes.F_FULL, 18, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet",
					"cadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent", "[Z", "net/minecraft/client/renderer/BlockRendererDispatcher", Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Lnet/minecraft/util/BlockRenderLayer;" }, 0, new Object[] {});
			this.mv.visitIincInsn(15, 1);
			this.mv.visitLabel(l68);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitVarInsn(ILOAD, 15);
			this.mv.visitVarInsn(ILOAD, 16);
			this.mv.visitJumpInsn(IF_ICMPLT, l69);
			this.mv.visitLabel(l21);
			this.mv.visitLineNumber(210, l21);
			this.mv.visitFrame(Opcodes.F_FULL, 11, new Object[] { "net/minecraft/client/renderer/chunk/RenderChunk", Opcodes.FLOAT, Opcodes.FLOAT, Opcodes.FLOAT, "net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator", "net/minecraft/client/renderer/chunk/CompiledChunk", Opcodes.INTEGER, "net/minecraft/util/math/BlockPos", "net/minecraft/util/math/BlockPos", "net/minecraft/client/renderer/chunk/VisGraph", "java/util/HashSet" }, 0, new Object[] {});
			this.mv.visitVarInsn(ALOAD, 5);
			this.mv.visitVarInsn(ALOAD, 9);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/VisGraph", "computeVisibility", "()Lnet/minecraft/client/renderer/chunk/SetVisibility;", false);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/chunk/CompiledChunk", "setVisibility", "(Lnet/minecraft/client/renderer/chunk/SetVisibility;)V", false);
			final Label l75 = new Label();
			this.mv.visitLabel(l75);
			this.mv.visitLineNumber(211, l75);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "lockCompileTask", "Ljava/util/concurrent/locks/ReentrantLock;");
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "lock", "()V", false);
			this.mv.visitLabel(l4);
			this.mv.visitLineNumber(214, l4);
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Sets", "newHashSet", "(Ljava/lang/Iterable;)Ljava/util/HashSet;", false);
			this.mv.visitVarInsn(ASTORE, 11);
			final Label l76 = new Label();
			this.mv.visitLabel(l76);
			this.mv.visitLineNumber(215, l76);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "setTileEntities", "Ljava/util/Set;");
			this.mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Sets", "newHashSet", "(Ljava/lang/Iterable;)Ljava/util/HashSet;", false);
			this.mv.visitVarInsn(ASTORE, 12);
			final Label l77 = new Label();
			this.mv.visitLabel(l77);
			this.mv.visitLineNumber(216, l77);
			this.mv.visitVarInsn(ALOAD, 11);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "setTileEntities", "Ljava/util/Set;");
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "removeAll", "(Ljava/util/Collection;)Z", true);
			this.mv.visitInsn(POP);
			final Label l78 = new Label();
			this.mv.visitLabel(l78);
			this.mv.visitLineNumber(217, l78);
			this.mv.visitVarInsn(ALOAD, 12);
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "removeAll", "(Ljava/util/Collection;)Z", true);
			this.mv.visitInsn(POP);
			final Label l79 = new Label();
			this.mv.visitLabel(l79);
			this.mv.visitLineNumber(218, l79);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "setTileEntities", "Ljava/util/Set;");
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "clear", "()V", true);
			final Label l80 = new Label();
			this.mv.visitLabel(l80);
			this.mv.visitLineNumber(219, l80);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "setTileEntities", "Ljava/util/Set;");
			this.mv.visitVarInsn(ALOAD, 10);
			this.mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "addAll", "(Ljava/util/Collection;)Z", true);
			this.mv.visitInsn(POP);
			final Label l81 = new Label();
			this.mv.visitLabel(l81);
			this.mv.visitLineNumber(220, l81);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "renderGlobal", "Lnet/minecraft/client/renderer/RenderGlobal;");
			this.mv.visitVarInsn(ALOAD, 12);
			this.mv.visitVarInsn(ALOAD, 11);
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/renderer/RenderGlobal", "updateTileEntities", "(Ljava/util/Collection;Ljava/util/Collection;)V", false);
			final Label l82 = new Label();
			this.mv.visitLabel(l82);
			this.mv.visitLineNumber(221, l82);
			final Label l83 = new Label();
			this.mv.visitJumpInsn(GOTO, l83);
			this.mv.visitLabel(l5);
			this.mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] { "java/lang/Throwable" });
			this.mv.visitVarInsn(ASTORE, 13);
			final Label l84 = new Label();
			this.mv.visitLabel(l84);
			this.mv.visitLineNumber(222, l84);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "lockCompileTask", "Ljava/util/concurrent/locks/ReentrantLock;");
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "unlock", "()V", false);
			final Label l85 = new Label();
			this.mv.visitLabel(l85);
			this.mv.visitLineNumber(223, l85);
			this.mv.visitVarInsn(ALOAD, 13);
			this.mv.visitInsn(ATHROW);
			this.mv.visitLabel(l83);
			this.mv.visitLineNumber(222, l83);
			this.mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			this.mv.visitVarInsn(ALOAD, 0);
			this.mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "lockCompileTask", "Ljava/util/concurrent/locks/ReentrantLock;");
			this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/concurrent/locks/ReentrantLock", "unlock", "()V", false);
			final Label l86 = new Label();
			this.mv.visitLabel(l86);
			this.mv.visitLineNumber(224, l86);
			this.mv.visitInsn(RETURN);
			final Label l87 = new Label();
			this.mv.visitLabel(l87);
			this.mv.visitLocalVariable("this", "Lnet/minecraft/client/renderer/chunk/RenderChunk;", null, l6, l87, 0);
			this.mv.visitLocalVariable("x", "F", null, l6, l87, 1);
			this.mv.visitLocalVariable("y", "F", null, l6, l87, 2);
			this.mv.visitLocalVariable("z", "F", null, l6, l87, 3);
			this.mv.visitLocalVariable("generator", "Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;", null, l6, l87, 4);
			this.mv.visitLocalVariable("compiledchunk", "Lnet/minecraft/client/renderer/chunk/CompiledChunk;", null, l7, l87, 5);
			this.mv.visitLocalVariable("i", "I", null, l8, l87, 6);
			this.mv.visitLocalVariable("blockpos", "Lnet/minecraft/util/math/BlockPos;", null, l9, l87, 7);
			this.mv.visitLocalVariable("blockpos1", "Lnet/minecraft/util/math/BlockPos;", null, l10, l87, 8);
			this.mv.visitLocalVariable("lvt_9_1_", "Lnet/minecraft/client/renderer/chunk/VisGraph;", null, l19, l87, 9);
			this.mv.visitLocalVariable("lvt_10_1_", "Ljava/util/HashSet;", null, l20, l87, 10);
			this.mv.visitLocalVariable("rebuildBlocksEvent", "Lcadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlocksEvent;", null, l23, l21, 11);
			this.mv.visitLocalVariable("aboolean", "[Z", null, l25, l21, 12);
			this.mv.visitLocalVariable("blockrendererdispatcher", "Lnet/minecraft/client/renderer/BlockRendererDispatcher;", null, l26, l21, 13);
			this.mv.visitLocalVariable("blockpos$mutableblockpos", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;", null, l29, l27, 14);
			this.mv.visitLocalVariable("iblockstate", "Lnet/minecraft/block/state/IBlockState;", null, l30, l27, 16);
			this.mv.visitLocalVariable("block", "Lnet/minecraft/block/Block;", null, l31, l27, 17);
			this.mv.visitLocalVariable("tileentity", "Lnet/minecraft/tileentity/TileEntity;", null, l36, l34, 18);
			this.mv.visitLocalVariable("tileentityspecialrenderer", "Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;", "Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer<Lnet/minecraft/tileentity/TileEntity;>;", l38, l34, 19);
			this.mv.visitLocalVariable("blockrenderlayer1", "Lnet/minecraft/util/BlockRenderLayer;", null, l45, l48, 18);
			this.mv.visitLocalVariable("j", "I", null, l50, l48, 22);
			this.mv.visitLocalVariable("bufferbuilder", "Lnet/minecraft/client/renderer/BufferBuilder;", null, l52, l48, 23);
			this.mv.visitLocalVariable("rebuildBlockEvent", "Lcadiboo/renderchunkrebuildchunkhooks/event/RebuildChunkEvent$RebuildChunkBlockEvent;", null, l57, l48, 24);
			this.mv.visitLocalVariable("blockrenderlayer", "Lnet/minecraft/util/BlockRenderLayer;", null, l62, l63, 25);
			this.mv.visitLocalVariable("blockrenderlayer", "Lnet/minecraft/util/BlockRenderLayer;", null, l70, l73, 14);
			this.mv.visitLocalVariable("set", "Ljava/util/Set;", "Ljava/util/Set<Lnet/minecraft/tileentity/TileEntity;>;", l76, l82, 11);
			this.mv.visitLocalVariable("set1", "Ljava/util/Set;", "Ljava/util/Set<Lnet/minecraft/tileentity/TileEntity;>;", l77, l82, 12);

		}

	}

}
