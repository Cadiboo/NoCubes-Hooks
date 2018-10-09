package cadiboo.renderchunkrebuildchunkhooks;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class RenderChunkRebuildChunkHooksRenderChunkClassTransformer implements IClassTransformer {

	public static final int ALOAD = 0x19;
	public static final int INVOKESTATIC = 0xB8;
	public static final int GETFIELD = 0xB4;
	public static final int FLOAD = 0x17;
	public static final int INVOKEVIRTUAL = 0xB6;
	public static final int IFEQ = 0x99;
	public static final int RETURN = 0xB1;

	@Override
	public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
		if (name.equals("net.minecraft.client.renderer.chunk.RenderChunk")) {
			System.out.println("********* INSIDE DEOBFUSCATED RENDER_CHUNK TRANSFORMER ABOUT TO PATCH: " + name);
			final byte[] hookRebuildChunkEvent = this.addRebuildChunkEvent(name, basicClass, true);
			final byte[] hookRebuildChunkBlocksEvent = this.addRebuildChunkBlocksEvent(name, hookRebuildChunkEvent, true);
			return hookRebuildChunkBlocksEvent;
		}
		return basicClass;
	}

	private byte[] addRebuildChunkEvent(final String name, final byte[] bytes, final boolean deobfuscated) {
		final String targetMethodName;
		if (deobfuscated == false) {
			targetMethodName = "NULL";
		} else {
			targetMethodName = "rebuildChunk";
		}

		// set up ASM class manipulation stuff. Consult the ASM docs for details
		final ClassNode classNodeRenderChunk = new ClassNode();
		final ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNodeRenderChunk, 0);

		// loop over all of the methods declared inside the class until we get to the @link{targetMethodName}
		final Iterator<MethodNode> methods = classNodeRenderChunk.methods.iterator();
		while (methods.hasNext()) {
			final MethodNode methodRebuildChunk = methods.next();
			if (!methodRebuildChunk.name.equals(targetMethodName)) {
				continue;
			}
			if (!methodRebuildChunk.desc.equals("(FFFLnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;)V")) {
				continue;
			}

			System.out.println("********* Inside target method! " + targetMethodName + " | " + methodRebuildChunk.name);

			final AbstractInsnNode currentNode = null;
			final AbstractInsnNode targetNode = null;

			// RENDER CHUNK METHOD ->
			// LINE - CODE
			// 147 - }
			// 148 -
			// 149 - VisGraph lvt_9_1_ = new VisGraph();

			// AFTER ASM
			// LINE - CODE
			// 147 - }
			// 148 - if (net.minecraftforge.client.ForgeHooksClient.onRebuildChunkEvent(field_178589_e, field_189564_r, p_178581_4_, compiledchunk, this.field_178586_f, p_178581_1_, p_178581_2_, p_178581_3_).isCanceled()) return;
			// 149 - VisGraph lvt_9_1_ = new VisGraph();

			//@formatter:off
//			L16
//		    LINENUMBER 145 L16
//		    ALOAD 0
//		    GETFIELD net/minecraft/client/renderer/chunk/RenderChunk.renderGlobal : Lnet/minecraft/client/renderer/RenderGlobal;
//		    ALOAD 0
//		    GETFIELD net/minecraft/client/renderer/chunk/RenderChunk.worldView : Lnet/minecraft/world/ChunkCache;
//		    ALOAD 4
//		    ALOAD 5
//		    ALOAD 0
//		    GETFIELD net/minecraft/client/renderer/chunk/RenderChunk.position : Lnet/minecraft/util/math/BlockPos$MutableBlockPos;
//		    FLOAD 1
//		    FLOAD 2
//		    FLOAD 3
//		    INVOKESTATIC net/minecraftforge/client/ForgeHooksClient.onRebuildChunkEvent(Lnet/minecraft/client/renderer/RenderGlobal;Lnet/minecraft/world/ChunkCache;Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;Lnet/minecraft/client/renderer/chunk/CompiledChunk;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;FFF)Lnet/minecraftforge/client/event/RebuildChunkEvent;
//		    INVOKEVIRTUAL net/minecraftforge/client/event/RebuildChunkEvent.isCanceled()Z
//		    IFEQ L17
//		    RETURN
//		   L17
			//@formatter:on

			final Label l16 = new Label();
			methodRebuildChunk.visitLabel(l16);
			methodRebuildChunk.visitLineNumber(145, l16);
			methodRebuildChunk.visitVarInsn(ALOAD, 0);
			methodRebuildChunk.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "renderGlobal", "Lnet/minecraft/client/renderer/RenderGlobal;");
			methodRebuildChunk.visitVarInsn(ALOAD, 0);
			methodRebuildChunk.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "worldView", "Lnet/minecraft/world/ChunkCache;");
			methodRebuildChunk.visitVarInsn(ALOAD, 4);
			methodRebuildChunk.visitVarInsn(ALOAD, 5);
			methodRebuildChunk.visitVarInsn(ALOAD, 0);
			methodRebuildChunk.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/chunk/RenderChunk", "position", "Lnet/minecraft/util/math/BlockPos$MutableBlockPos;");
			methodRebuildChunk.visitVarInsn(FLOAD, 1);
			methodRebuildChunk.visitVarInsn(FLOAD, 2);
			methodRebuildChunk.visitVarInsn(FLOAD, 3);
			methodRebuildChunk.visitMethodInsn(INVOKESTATIC, "net/minecraftforge/client/ForgeHooksClient", "onRebuildChunkEvent", "(Lnet/minecraft/client/renderer/RenderGlobal;Lnet/minecraft/world/ChunkCache;Lnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;Lnet/minecraft/client/renderer/chunk/CompiledChunk;Lnet/minecraft/util/math/BlockPos$MutableBlockPos;FFF)Lnet/minecraftforge/client/event/RebuildChunkEvent;", false);
			methodRebuildChunk.visitMethodInsn(INVOKEVIRTUAL, "net/minecraftforge/client/event/RebuildChunkEvent", "isCanceled", "()Z", false);
			final Label l17 = new Label();
			methodRebuildChunk.visitJumpInsn(IFEQ, l17);
			methodRebuildChunk.visitInsn(RETURN);

			// in this section, i'll just illustrate how to inject a call to a static method if your instruction is a little more advanced than just removing a couple of instruction:

			/*
			 * To add new instructions, such as calling a static method can be done like so:
			 *
			 * // make new instruction list InsnList toInject = new InsnList();
			 *
			 * //add your own instruction lists: *USE THE ASM JAVADOC AS REFERENCE* toInject.add(new VarInsnNode(ALOAD, 0)); toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));
			 *
			 * // add the added code to the nstruction list // You can also choose if you want to add the code before or after the target node, check the ASM Javadoc (insertBefore) m.instructions.insert(targetNode, toInject);
			 */

			final InsnList toInject = new InsnList();
			toInject.add(new VarInsnNode(ALOAD, 0));
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));

		}
		return bytes;
	}

	private byte[] addRebuildChunkBlocksEvent(final String name, final byte[] bytes, final boolean deobfuscated) {
		// TODO Auto-generated method stub
		return bytes;
	}

	public byte[] patchClassASM(final String name, final byte[] bytes, final boolean deobfuscated) {

		String targetMethodName = "";

		// Our target method
		if (deobfuscated == false) {
			targetMethodName = "NULL";
		} else {
			targetMethodName = "rebuildChunk";
		}

		// set up ASM class manipulation stuff. Consult the ASM docs for details
		final ClassNode classNodeRenderChunk = new ClassNode();
		final ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNodeRenderChunk, 0);

		// Now we loop over all of the methods declared inside the Explosion class until we get to the targetMethodName "doExplosionB"

		final Iterator<MethodNode> methods = classNodeRenderChunk.methods.iterator();
		while (methods.hasNext()) {
			final MethodNode methodRebuildChunk = methods.next();
			int fdiv_index = -1;

			// Check if this is doExplosionB and it's method signature is (FFFLnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;)V which means that it accepts a 3 floats (F), and an Object (L) of type ChunkCompileTaskGenerator and returns a void (V)
			if ((methodRebuildChunk.name.equals(targetMethodName) && methodRebuildChunk.desc.equals("(FFFLnet/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator;)V"))) {
				System.out.println("********* Inside target method!");

				AbstractInsnNode currentNode = null;
				AbstractInsnNode targetNode = null;

				// RENDER CHUNK METHOD ->
				// LINE - CODE
				// 147 - }
				// 148 -
				// 149 - VisGraph lvt_9_1_ = new VisGraph();

				// AFTER ASM
				// LINE - CODE
				// 147 - }
				// 148 - if (net.minecraftforge.client.ForgeHooksClient.onRebuildChunkEvent(field_178589_e, field_189564_r, p_178581_4_, compiledchunk, this.field_178586_f, p_178581_1_, p_178581_2_, p_178581_3_).isCanceled()) return;
				// 149 - VisGraph lvt_9_1_ = new VisGraph();

				// in this section, i'll just illustrate how to inject a call to a static method if your instruction is a little more advanced than just removing a couple of instruction:

				/*
				 * To add new instructions, such as calling a static method can be done like so:
				 *
				 * // make new instruction list InsnList toInject = new InsnList();
				 *
				 * //add your own instruction lists: *USE THE ASM JAVADOC AS REFERENCE* toInject.add(new VarInsnNode(ALOAD, 0)); toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));
				 *
				 * // add the added code to the nstruction list // You can also choose if you want to add the code before or after the target node, check the ASM Javadoc (insertBefore) m.instructions.insert(targetNode, toInject);
				 */

				final InsnList toInject = new InsnList();
				toInject.add(new VarInsnNode(ALOAD, 0));
				toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));

				final Iterator<AbstractInsnNode> iter = methodRebuildChunk.instructions.iterator();

				int index = -1;

				// Loop over the instruction set and find the instruction FDIV which does the division of 1/explosionSize
				while (iter.hasNext()) {
					index++;
					currentNode = iter.next();

					final int FDIV = 12213123;
					// Found it! save the index location of instruction FDIV and the node for this instruction
					if (currentNode.getOpcode() == FDIV) {
						targetNode = currentNode;
						fdiv_index = index;
					}
				}

				// now we want the save nods that load the variable explosionSize and the division instruction:

				/*
				 * mv.visitInsn(FCONST_1); mv.visitVarInsn(ALOAD, 0); mv.visitFieldInsn(GETFIELD, "net/minecraft/src/Explosion", "explosionSize", "F"); mv.visitInsn(FDIV); mv.visitInsn(ICONST_0); mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/src/Block", "dropBlockAsItemWithChance", "(Lnet/minecraft/src/World;IIIIFI)V");
				 */

				final AbstractInsnNode remNode1 = methodRebuildChunk.instructions.get(fdiv_index - 2); // mv.visitVarInsn(ALOAD, 0);
				final AbstractInsnNode remNode2 = methodRebuildChunk.instructions.get(fdiv_index - 1); // mv.visitFieldInsn(GETFIELD, "net/minecraft/src/Explosion", "explosionSize", "F");
				final AbstractInsnNode remNode3 = methodRebuildChunk.instructions.get(fdiv_index); // mv.visitInsn(FDIV);

				// just remove these nodes from the instruction set, this will prevent the instruction FCONST_1 to be divided.

				methodRebuildChunk.instructions.remove(remNode1);
				methodRebuildChunk.instructions.remove(remNode2);
				methodRebuildChunk.instructions.remove(remNode3);

				// in this section, i'll just illustrate how to inject a call to a static method if your instruction is a little more advanced than just removing a couple of instruction:

				/*
				 * To add new instructions, such as calling a static method can be done like so:
				 *
				 * // make new instruction list InsnList toInject = new InsnList();
				 *
				 * //add your own instruction lists: *USE THE ASM JAVADOC AS REFERENCE* toInject.add(new VarInsnNode(ALOAD, 0)); toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));
				 *
				 * // add the added code to the nstruction list // You can also choose if you want to add the code before or after the target node, check the ASM Javadoc (insertBefore) m.instructions.insert(targetNode, toInject);
				 */

				System.out.println("Patching Complete!");
				break;
			}
		}

		// ASM specific for cleaning up and returning the final bytes for JVM processing.
		final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNodeRenderChunk.accept(writer);
		return writer.toByteArray();
	}
}