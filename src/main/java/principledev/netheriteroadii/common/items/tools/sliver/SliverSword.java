package principledev.netheriteroadii.common.items.tools.sliver;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.items.tools.material.ToolMaterials;

import javax.annotation.Nullable;
import java.util.List;

public class SliverSword extends SwordItem {
    private static int sliverAttackDamage = 3;
    public SliverSword() {
        super(ToolMaterials.SLIVER, sliverAttackDamage, -2.4F,new Properties().group(NetheriteRoadII.TAB));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target instanceof MonsterEntity){
            target.setHealth(target.getHealth() - 14);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        Style whiteBoldUnderlined = Style.EMPTY.setBold(true).setFormatting(TextFormatting.WHITE).setUnderlined(true);
        Style descStyle = Style.EMPTY.setFormatting(TextFormatting.GRAY);
        ITextComponent top = new TranslationTextComponent("tooltip.netheriteroadii.sliver_sword.top").setStyle(whiteBoldUnderlined);
        ITextComponent desc = new TranslationTextComponent("tooltip.netheriteroadii.sliver_sword.desc").setStyle(descStyle);
        tooltip.add(1, top);
        tooltip.add(2, desc);
    }
}
