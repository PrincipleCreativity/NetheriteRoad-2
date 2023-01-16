package principledev.netheriteroadii.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SliverOreBlock extends Block {
    public SliverOreBlock() {
        super(Properties.create(Material.ROCK).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 10).sound(SoundType.STONE).harvestLevel(2));
    }
}
