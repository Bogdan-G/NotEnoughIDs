package ru.fewizz.idextender.asm.transformer;

import java.util.ListIterator;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import ru.fewizz.idextender.asm.AsmUtil;
import ru.fewizz.idextender.asm.Constants;
import ru.fewizz.idextender.asm.IClassNodeTransformer;

public class WorldEditBaseBlock implements IClassNodeTransformer {

	@Override
	public void transform(ClassNode cn, boolean obfuscated) {
		MethodNode method = AsmUtil.findMethod(cn, "internalSetId", true);
		if(method == null) {
			return;
		}
		
		AsmUtil.transformInlinedSizeMethod(cn, method, 4095, Constants.maxBlockId);

		InsnList code = method.instructions;

		for (ListIterator<AbstractInsnNode> iterator = code.iterator(); iterator.hasNext();) {
			AbstractInsnNode insn = iterator.next();

			if (insn.getType() == insn.LDC_INSN && ((LdcInsnNode) insn).cst instanceof String) {
				String string = (String) (((LdcInsnNode) insn).cst);
				if (string.contains("4095")) {
					((LdcInsnNode) insn).cst = string.replace("4095", Integer.toString(Constants.maxBlockId));
					break;
				}
			}
		}
	}

}
