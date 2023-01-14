package principledev.netheriteroadii.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import principledev.netheriteroadii.NetheriteRoadII;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetheriteRoadII.MOD_ID);

    //Item Registry

    //Block Item
    public static final RegistryObject<Item> ANCIENT_PURIFIER_ITEM;

    static{
        ANCIENT_PURIFIER_ITEM = ITEMS.register("ancient_purifier", () -> new BlockItem(BlockRegister.ANCIENT_PURIFIER.get(), new Item.Properties().group(NetheriteRoadII.TAB)));
    }
}
