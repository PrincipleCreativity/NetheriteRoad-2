package principledev.netheriteroadii.init;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.PurifierContainerItemNumber;
import principledev.netheriteroadii.common.container.AncientPurifierContainer;

public class ClientRegister {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NetheriteRoadII.MOD_ID);
    public static final RegistryObject<ContainerType<AncientPurifierContainer>> ANCIENT_PURIFIER_CONTAINER = CONTAINERS.register("purifier_container", () -> IForgeContainerType.create(new IContainerFactory<AncientPurifierContainer>() {
        @Override
        public AncientPurifierContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
            return new AncientPurifierContainer(ClientRegister.ANCIENT_PURIFIER_CONTAINER.get(), windowId, new PurifierContainerItemNumber(), inv,data.readBlockPos());
        }
    }));
}
