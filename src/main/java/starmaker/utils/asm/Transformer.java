package starmaker.utils.asm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

public class Transformer implements IClassTransformer
{

	Logger logger = LogManager.getLogger("StarMakerPlugin");

	public Transformer()
	{
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (transformedName.equals("net.minecraft.client.Minecraft"))
		{
			return patchMinecraft(basicClass);
		}
		return basicClass;
	}

	private byte[] patchMinecraft(byte[] basicClass)
	{
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, 0);

		MethodNode refreshResources = null;
		MethodNode startGame = null;

		for (MethodNode mn : classNode.methods)
		{
			if (mn.name.equals(ObfHelper.method("func_110436_a")))
			{
				refreshResources = mn;
			} else if (mn.name.equals(ObfHelper.method("func_71384_a")))
			{
				startGame = mn;
			}
		}

		if (refreshResources != null)
		{
			for (int i = 0; i < refreshResources.instructions.size(); i++)
			{
				AbstractInsnNode ain = refreshResources.instructions.get(i);
				if (ain instanceof MethodInsnNode)
				{
					MethodInsnNode min = (MethodInsnNode) ain;
					if (min.name.equals(ObfHelper.method("func_110541_a")))
					{
						InsnList toInsert = new InsnList();
						toInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "starmaker/StarMaker",
								"defineResourcePack", "(Ljava/util/List;)V", false));
						toInsert.add(new VarInsnNode(Opcodes.ALOAD, 1));
						refreshResources.instructions.insertBefore(min, toInsert);
						i += 2;
					} else if (min.name.equals("newArrayList"))
					{
						AbstractInsnNode target = refreshResources.instructions.get(i + 1);
						InsnList toInsert = new InsnList();
						toInsert.add(new VarInsnNode(Opcodes.ALOAD, 1));
						toInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "starmaker/StarMaker",
								"defineResourcePack", "(Ljava/util/List;)V", false));
						refreshResources.instructions.insert(target, toInsert);
					}
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);

		return writer.toByteArray();
	}
}
