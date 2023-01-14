package principledev.netheriteroadii.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkHooks;
import principledev.netheriteroadii.blocks.tileEntity.TileEntityPurifier;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
public class BlockAncientPurifier extends Block {
    public BlockAncientPurifier() {
        super(Properties.create(Material.ANVIL));
    }
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityPurifier();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntityPurifier tileEntityPurifier = (TileEntityPurifier) worldIn.getTileEntity(pos);
            worldIn.notifyBlockUpdate(pos,state,state, Constants.BlockFlags.DEFAULT);
            NetworkHooks.openGui(
                    (ServerPlayerEntity) player,
                    tileEntityPurifier,
                    packerBuffer -> packerBuffer.writeBlockPos(tileEntityPurifier.getPos()));
        }
        return ActionResultType.SUCCESS;
    }
}
