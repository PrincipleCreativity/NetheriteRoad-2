package principledev.netheriteroadii.common.items.misc;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import principledev.netheriteroadii.NetheriteRoadII;

public class GoldString extends Item {
    public GoldString() {
        super(new Properties().rarity(Rarity.COMMON).group(NetheriteRoadII.TAB));
    }
}
