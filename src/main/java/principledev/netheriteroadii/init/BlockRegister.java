package principledev.netheriteroadii.init;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.blocks.BlockAncientPurifier;
import principledev.netheriteroadii.blocks.tileEntity.TileEntityPurifier;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NetheriteRoadII.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NetheriteRoadII.MOD_ID);

    //Block Registry
    public static final RegistryObject<Block> ANCIENT_PURIFIER;

    //TileEntity Registry
    public static final RegistryObject<TileEntityType<TileEntityPurifier>> TILE_ENTITY_PURIFIER;

    static{
        ANCIENT_PURIFIER = BLOCKS.register("ancient_purifier", BlockAncientPurifier::new);
        TILE_ENTITY_PURIFIER = TILE_ENTITIES.register("ancient_purifier_entity", () -> TileEntityType
                .Builder.create(TileEntityPurifier::new, ANCIENT_PURIFIER.get())
                .build(null));
    }
}