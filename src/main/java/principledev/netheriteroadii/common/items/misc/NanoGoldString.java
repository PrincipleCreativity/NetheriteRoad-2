package principledev.netheriteroadii.common.items.misc;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import principledev.netheriteroadii.NetheriteRoadII;

public class NanoGoldString extends Item {
    public NanoGoldString() {
        super(new Properties().group(NetheriteRoadII.TAB).rarity(Rarity.UNCOMMON));
    }
}
