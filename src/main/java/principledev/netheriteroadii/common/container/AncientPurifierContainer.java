package principledev.netheriteroadii.common.container;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import principledev.netheriteroadii.common.blocks.tileEntity.TileEntityPurifier;
import principledev.netheriteroadii.common.items.PurifierInputBlockItem;
import principledev.netheriteroadii.common.slot.PurifierResultSlot;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class AncientPurifierContainer extends Container{
    //private final IIntArray array;
    public final TileEntityPurifier purifier;

    public AncientPurifierContainer(@Nullable ContainerType<?> type, int id, IIntArray array, PlayerInventory playerInventory, BlockPos position) {
        super(type, id);
        //this.array = array;
        this.purifier = (TileEntityPurifier) playerInventory.player.getEntityWorld().getTileEntity(position);
        assert purifier != null;
        this.addSlot(new Slot(purifier, 0, 56 - 10, 35 + 4){
            public boolean isItemValid(ItemStack stack){
                return stack.getItem() instanceof PurifierInputBlockItem;
            }
        });
        this.addSlot(new PurifierResultSlot(purifier, 1, 116 - 10, 35 + 4));
        this.addSlot(new PurifierResultSlot(purifier, 2, 136 - 10, 35 + 4));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.trackIntArray(array);
    }
    /**
     * Determines whether supplied player can use this container
     *
     */
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.getSlot(index);
        if (slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.purifier.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.purifier.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /*
    public IIntArray getArray() {
        return array;
    }

     */
}