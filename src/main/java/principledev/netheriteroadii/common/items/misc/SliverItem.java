package principledev.netheriteroadii.common.items.misc;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import principledev.netheriteroadii.NetheriteRoadII;

public class SliverItem extends Item {
    public SliverItem() {
        super(new Properties().group(NetheriteRoadII.TAB).rarity(Rarity.COMMON));
    }
}
