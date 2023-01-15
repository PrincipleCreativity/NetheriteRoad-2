package principledev.netheriteroadii.common.items.tools;


import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import principledev.netheriteroadii.NetheriteRoadII;


public class NetheriteShears extends ShearsItem {

    public NetheriteShears() {
        super(new Properties().group(NetheriteRoadII.TAB).isImmuneToFire().maxDamage(2031));
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack retval = new ItemStack(this);
        retval.setDamage(itemstack.getDamage() + 1);
        if (retval.getDamage() >= retval.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return retval;
    }

    @Override
    public boolean isRepairable(ItemStack itemstack) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 4;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 0;
    }

}
