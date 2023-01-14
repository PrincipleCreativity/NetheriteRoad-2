package principledev.netheriteroadii.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.items.NetheriteItem;
import principledev.netheriteroadii.common.items.misc.GoldString;
import principledev.netheriteroadii.common.items.misc.MicronGoldString;
import principledev.netheriteroadii.common.items.misc.NanoGoldString;
import principledev.netheriteroadii.common.items.misc.NanoGoldStringScreen;
import principledev.netheriteroadii.common.items.tools.Circulator;
import principledev.netheriteroadii.common.items.tools.NetheriteShears;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetheriteRoadII.MOD_ID);

    //Item Registry

    //MISC
    public static final RegistryObject<Item> NETHERITE_STICK;
    public static final RegistryObject<Item> NETHERITE_NUGGET;
    public static final RegistryObject<Item> NETHERITE_PLATE;
    public static final RegistryObject<Item> GOLD_STRING;
    public static final RegistryObject<Item> MICRON_GOLD_STRING;
    public static final RegistryObject<Item> NANOGOLD_STRING;
    public static final RegistryObject<Item> NANOGOLD_STRING_SCREEN;

    //Tools

    public static final RegistryObject<Item> CIRCULATOR;
    public static final RegistryObject<Item> NETHERITE_SHEARS;

    //Block Item
    public static final RegistryObject<Item> ANCIENT_PURIFIER_ITEM;

    static{
        NETHERITE_STICK = ITEMS.register("netherite_stick", NetheriteItem::new);
        NETHERITE_NUGGET = ITEMS.register("netherite_nugget", NetheriteItem::new);
        NETHERITE_PLATE = ITEMS.register("netherite_plate", NetheriteItem::new);
        GOLD_STRING = ITEMS.register("gold_string", GoldString::new);
        MICRON_GOLD_STRING = ITEMS.register("micron_gold_string", MicronGoldString::new);
        NANOGOLD_STRING = ITEMS.register("nanogold_string", NanoGoldString::new);
        NANOGOLD_STRING_SCREEN = ITEMS.register("nanogold_string_screen", NanoGoldStringScreen::new);
        CIRCULATOR = ITEMS.register("circulator", Circulator::new);
        NETHERITE_SHEARS = ITEMS.register("netherite_shears", NetheriteShears::new);
        ANCIENT_PURIFIER_ITEM = ITEMS.register("ancient_purifier", () -> new BlockItem(BlockRegister.ANCIENT_PURIFIER.get(), new Item.Properties().group(NetheriteRoadII.TAB)));
    }
}