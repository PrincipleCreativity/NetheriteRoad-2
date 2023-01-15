package principledev.netheriteroadii.common.utils;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import principledev.netheriteroadii.common.init.ItemRegister;

public class NetheriteRoadTab extends ItemGroup {
    public NetheriteRoadTab() {
        super("netheriteroadii.tab");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegister.CIRCULATOR.get());
    }
}
