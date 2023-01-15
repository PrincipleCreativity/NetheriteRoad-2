package principledev.netheriteroadii.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.energy.EnergyStorage;

public class PurifierData {
    public final NonNullList<ItemStack> inventory;
    public static final int TIME_PER_WORK = 20;
    public static final int MAX_RF = 40000;
    public int cool_down = TIME_PER_WORK;
    public EnergyStorage storage;
    public PurifierData(){
        storage = new EnergyStorage(MAX_RF);
        inventory = NonNullList.withSize(3,ItemStack.EMPTY);
    }

    public boolean isActive(){
        return storage.getEnergyStored() > 0 && inventory.get(0).getItem() == Items.ANCIENT_DEBRIS;
    }
    public boolean isWork(){
        return storage.canReceive();
    }
}
