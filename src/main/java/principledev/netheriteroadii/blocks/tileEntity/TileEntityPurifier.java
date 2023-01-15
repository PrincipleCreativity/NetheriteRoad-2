package principledev.netheriteroadii.blocks.tileEntity;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeWorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.PurifierContainerItemNumber;
import principledev.netheriteroadii.common.utils.PurifierData;
import principledev.netheriteroadii.common.container.AncientPurifierContainer;
import principledev.netheriteroadii.common.init.BlockRegister;
import principledev.netheriteroadii.common.init.CommonRegister;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.IntStream;

@SuppressWarnings("NullableProblems")
public class TileEntityPurifier extends LockableLootTileEntity implements ITickableTileEntity, INamedContainerProvider, ISidedInventory {
    public PurifierData data;
    private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
    private NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);
    public TileEntityPurifier() {
        super(BlockRegister.TILE_ENTITY_PURIFIER.get());
        data = new PurifierData();
        data.storage = new EnergyStorage(PurifierData.MAX_RF){
            public int receiveEnergy(int maxReceive, boolean simulate){
                if(world != null)
                    world.notifyBlockUpdate(pos,getBlockState(),getBlockState(), Constants.BlockFlags.DEFAULT);
                return super.receiveEnergy(maxReceive,simulate);
            }
            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                if(world != null)
                    world.notifyBlockUpdate(pos,getBlockState(),getBlockState(), Constants.BlockFlags.DEFAULT);
                return super.extractEnergy(maxExtract,simulate);
            }
        };
    }
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("energy",data.storage.getEnergyStored());
        compound.putInt("cool_down",data.cool_down);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.stacks);
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        data.cool_down = nbt.getInt("cool_down");
        if (!this.checkLootAndRead(nbt)) {
            this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        }
        ItemStackHelper.loadAllItems(nbt, this.stacks);
        data.storage.receiveEnergy(nbt.getInt("energy"),false);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability){
        if(capability == CapabilityEnergy.ENERGY){
            return LazyOptional.of(() -> data.storage).cast();
        }
        return LazyOptional.empty();
    }
    @Nonnull
    @Override
    public  <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side){
        return this.getCapability(cap);
    }
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("energy",data.storage.getEnergyStored());
        return new SUpdateTileEntityPacket(this.pos, 1, compound);
    }

    @Override
    public void onDataPacket(NetworkManager manager, SUpdateTileEntityPacket packet) {
        CompoundNBT data = packet.getNbtCompound();
        this.data.storage.extractEnergy(PurifierData.MAX_RF,false);
        this.data.storage.receiveEnergy(data.getInt("energy"),false);
    }
    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        if(data.storage.getEnergyStored() >= PurifierData.MAX_RF
                && !data.inventory.get(0).isEmpty()
                && data.inventory.get(0).getItem() == BlockItem.getItemFromBlock(Blocks.ANCIENT_DEBRIS)
                && (data.inventory.get(1).isEmpty() || data.inventory.get(1).getItem() == Items.NETHERITE_INGOT)
                && (data.inventory.get(2).isEmpty() || data.inventory.get(2).getItem() == Items.DIAMOND)
        ) {
            data.cool_down -= 1;
            if (data.cool_down <= 0) {
                data.cool_down = PurifierData.TIME_PER_WORK;
            }else return;
        }else return;
        if(data.storage.getEnergyStored() >= PurifierData.MAX_RF && world instanceof IForgeWorldServer) {
            data.storage.extractEnergy(PurifierData.MAX_RF,false);
            data.inventory.get(0).shrink(1);
            if(data.inventory.get(1).isEmpty()) {
                data.inventory.set(1, new ItemStack(Items.NETHERITE_INGOT));
            }else{
                data.inventory.get(1).grow(1);
            }
            if(Math.random() < 0.2){
                if(data.inventory.get(2).isEmpty()) {
                    data.inventory.set(2, new ItemStack(Items.DIAMOND));
                }else{
                    data.inventory.get(2).grow(1);
                }
            }
            world.notifyBlockUpdate(pos,getBlockState(),getBlockState(), Constants.BlockFlags.DEFAULT);
        }
        if(this.world != null){
            this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.data.isWork()), 3);
        }
    }


    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + NetheriteRoadII.MOD_ID + ".ancient_purifier_container");
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("gui." + NetheriteRoadII.MOD_ID + ".ancient_purifier_container");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new AncientPurifierContainer(CommonRegister.ANCIENT_PURIFIER_CONTAINER.get(), p_createMenu_1_, new PurifierContainerItemNumber(), p_createMenu_2_,this.pos);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getSizeInventory()).toArray();
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return index == 0 && itemStackIn.getItem() == Blocks.ANCIENT_DEBRIS.asItem();
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return index != 0;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return data.inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return data.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);

        if(!stack.isEmpty()){
            if(stack.getCount() <= count){
                setInventorySlotContents(index, ItemStack.EMPTY);
            }else{
                stack = stack.split(count);
                if(stack.getCount() == 0){
                    setInventorySlotContents(index, ItemStack.EMPTY);
                }
            }
            this.markDirty();
        }

        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = getStackInSlot(index);

        if(!stack.isEmpty()){
            setInventorySlotContents(index, ItemStack.EMPTY);
        }

        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        data.inventory.set(index, stack);
        if(!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()){
            stack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        data.inventory.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.stacks = itemsIn;
    }


    @Override
    public void remove() {
        super.remove();
        for (LazyOptional<? extends IItemHandler> handler : handlers)
            handler.invalidate();
    }
}