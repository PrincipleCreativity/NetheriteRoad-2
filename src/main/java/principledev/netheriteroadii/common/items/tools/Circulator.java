package principledev.netheriteroadii.common.items.tools;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.ToolItem;
import principledev.netheriteroadii.NetheriteRoadII;

public class Circulator extends Item {
    public Circulator() {
        super(new Properties().group(NetheriteRoadII.TAB).isImmuneToFire().rarity(Rarity.EPIC).maxDamage(100));
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
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 0;
    }

    @Override
    public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
        return 1F;
    }

}
