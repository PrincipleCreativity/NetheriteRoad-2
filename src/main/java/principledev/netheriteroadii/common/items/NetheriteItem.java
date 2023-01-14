package principledev.netheriteroadii.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import principledev.netheriteroadii.NetheriteRoadII;

import javax.annotation.Nullable;
import java.util.List;

public class NetheriteItem extends Item {
    public NetheriteItem() {
        super(new Properties().group(NetheriteRoadII.TAB).isImmuneToFire().maxStackSize(64));
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

}
