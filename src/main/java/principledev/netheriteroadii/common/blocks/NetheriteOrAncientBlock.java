package principledev.netheriteroadii.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraftforge.common.ToolType;

public class NetheriteOrAncientBlock extends Block {
    public NetheriteOrAncientBlock(Properties properties) {
        super(properties.harvestLevel(4).sound(SoundType.NETHERITE).hardnessAndResistance(7f, 50f).harvestTool(ToolType.PICKAXE).setRequiresTool());
    }
}
