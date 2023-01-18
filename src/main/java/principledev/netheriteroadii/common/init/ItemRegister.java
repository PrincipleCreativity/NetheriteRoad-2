package principledev.netheriteroadii.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.items.NetheriteItem;
import principledev.netheriteroadii.common.items.PurifierInputBlockItem;
import principledev.netheriteroadii.common.items.misc.*;
import principledev.netheriteroadii.common.items.tools.Circulator;
import principledev.netheriteroadii.common.items.tools.NetheriteShears;
import principledev.netheriteroadii.common.items.tools.sliver.SliverSword;
import principledev.netheriteroadii.common.recipes.PurifierRecipes;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetheriteRoadII.MOD_ID);

    //Item Registry

    //MISC
    public static final RegistryObject<Item> SLIVER_INGOT;
    public static final RegistryObject<Item> SLIVER_HEAT_PIPE;
    public static final RegistryObject<Item> NETHERITE_STICK;
    public static final RegistryObject<Item> NETHERITE_NUGGET;
    public static final RegistryObject<Item> NETHERITE_PLATE;
    public static final RegistryObject<Item> NETHERITE_FIXING_PLATE;
    public static final RegistryObject<Item> GOLD_STRING;
    public static final RegistryObject<Item> MICRON_GOLD_STRING;
    public static final RegistryObject<Item> NANOGOLD_STRING;
    public static final RegistryObject<Item> NANOGOLD_STRING_SCREEN;
    public static final RegistryObject<Item> S_NANOGOLD_STRING_SCREEN;
    public static final RegistryObject<Item> VS_NANOGOLD_STRING_SCREEN;

    public static final RegistryObject<Item> GOLD_PLATE;


    //Tools
    public static final RegistryObject<Item> SLIVER_SWORD;
    public static final RegistryObject<Item> CIRCULATOR;
    public static final RegistryObject<Item> NETHERITE_SHEARS;

    //Block Item
    public static final RegistryObject<Item> ANCIENT_PURIFIER_ITEM;
    public static final RegistryObject<Item> ANCIENT_ROCK;
    public static final RegistryObject<Item> SLIVER_ORE_ITEM;

    static{
        SLIVER_INGOT = ITEMS.register("sliver_ingot", SliverItem::new);
        SLIVER_HEAT_PIPE = ITEMS.register("sliver_heat_pipe", SliverItem::new);
        NETHERITE_STICK = ITEMS.register("netherite_stick", NetheriteItem::new);
        NETHERITE_NUGGET = ITEMS.register("netherite_nugget", NetheriteItem::new);
        NETHERITE_PLATE = ITEMS.register("netherite_plate", NetheriteItem::new);
        NETHERITE_FIXING_PLATE = ITEMS.register("netherite_fixing_plate", NetheriteItem::new);
        GOLD_STRING = ITEMS.register("gold_string", GoldString::new);
        MICRON_GOLD_STRING = ITEMS.register("micron_gold_string", MicronGoldString::new);
        NANOGOLD_STRING = ITEMS.register("nanogold_string", NanoGoldString::new);
        NANOGOLD_STRING_SCREEN = ITEMS.register("nanogold_string_screen", NanoGoldStringScreen::new);
        S_NANOGOLD_STRING_SCREEN = ITEMS.register("s_nanogold_string_screen", NanoGoldStringScreen::new);
        VS_NANOGOLD_STRING_SCREEN = ITEMS.register("vs_nanogold_string_screen", NanoGoldStringScreen::new);
        GOLD_PLATE = ITEMS.register("gold_plate", GoldPlate::new);
        SLIVER_SWORD = ITEMS.register("sliver_sword", SliverSword::new);
        CIRCULATOR = ITEMS.register("circulator", Circulator::new);
        NETHERITE_SHEARS = ITEMS.register("netherite_shears", NetheriteShears::new);

        ANCIENT_PURIFIER_ITEM = ITEMS.register("ancient_purifier", () -> new BlockItem(BlockRegister.ANCIENT_PURIFIER.get(), new Item.Properties().group(NetheriteRoadII.TAB)));
        ANCIENT_ROCK = ITEMS.register("ancient_rock", () -> new PurifierInputBlockItem(BlockRegister.ANCIENT_ROCK.get()));
        SLIVER_ORE_ITEM = ITEMS.register("sliver_ore", () -> new PurifierInputBlockItem(BlockRegister.SLIVER_ORE.get()));
    }
}
