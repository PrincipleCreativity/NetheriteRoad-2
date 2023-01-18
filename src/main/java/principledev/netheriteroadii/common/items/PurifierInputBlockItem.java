package principledev.netheriteroadii.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.recipes.PurifierRecipes;

public class PurifierInputBlockItem extends BlockItem {
    public PurifierInputBlockItem(Block blockIn) {
        super(blockIn, new Properties().group(NetheriteRoadII.TAB).isImmuneToFire());
        PurifierRecipes.inputItem.add(this);
    }
}
