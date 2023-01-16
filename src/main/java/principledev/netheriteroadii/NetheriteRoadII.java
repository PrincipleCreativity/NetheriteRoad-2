package principledev.netheriteroadii;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import principledev.netheriteroadii.client.gui.AncientPurifierGui;
import principledev.netheriteroadii.common.init.BlockRegister;
import principledev.netheriteroadii.common.init.CommonRegister;
import principledev.netheriteroadii.common.init.ItemRegister;
import principledev.netheriteroadii.common.init.NeroFeatures;
import principledev.netheriteroadii.common.utils.NetheriteRoadTab;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("NullableProblems")
@Mod(NetheriteRoadII.MOD_ID)
public class NetheriteRoadII {
    public static final String MOD_ID = "netheriteroadii";
    public static final ItemGroup TAB = new NetheriteRoadTab();
    private static final Logger LOGGER = LogManager.getLogger();
    public NetheriteRoadII() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NeroFeatures.FEATURES.register(bus);
        CommonRegister.CONTAINERS.register(bus);
        BlockRegister.BLOCKS.register(bus);
        BlockRegister.TILE_ENTITIES.register(bus);
        ItemRegister.ITEMS.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", net.minecraft.block.Blocks.DIRT.getRegistryName());
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        event.enqueueWork(() -> ScreenManager.registerFactory(CommonRegister.ANCIENT_PURIFIER_CONTAINER.get(), AncientPurifierGui::new));
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("netherite_road", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }
    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> NeroFeatures.SLIVER_ORE_FEATURE.getFeature().configuredFeature);
    }
}
