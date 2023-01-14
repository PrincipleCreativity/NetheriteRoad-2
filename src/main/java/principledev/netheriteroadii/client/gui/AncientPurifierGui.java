package principledev.netheriteroadii.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.blocks.tileEntity.TileEntityPurifier;
import principledev.netheriteroadii.common.utils.PurifierData;
import principledev.netheriteroadii.common.container.AncientPurifierContainer;

@SuppressWarnings("NullableProblems")
public class AncientPurifierGui extends ContainerScreen<AncientPurifierContainer> {
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    private static final ResourceLocation TEXTURE = new ResourceLocation(NetheriteRoadII.MOD_ID, "textures/gui/background_purifier.png");
    private final TileEntityPurifier purifier;
    public AncientPurifierGui(AncientPurifierContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
        this.purifier = screenContainer.purifier;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    private void getEnergyProgressBar(){
        if(getIndex() <= 0){
            Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(NetheriteRoadII.MOD_ID, "textures/gui/energy/0.png"));
        }
        else {
            Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(NetheriteRoadII.MOD_ID, "textures/gui/energy/" + getIndex() + ".png"));
        }
    }

    private int getIndex(){
        int energy = this.purifier.data.storage.getEnergyStored();
        return energy / 1250;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        //new TranslationTextComponent("gui.netheriteroadii.ancient_purifier.energy" + this.purifier.data.storage.getEnergyStored() + "/&3" + PurifierData.MAX_RF + "&4RF"),
        super.drawGuiContainerForegroundLayer(matrixStack,x,y);
        this.font.drawText(matrixStack,
                new TranslationTextComponent("gui.netheriteroadii.ancient_purifier.energy"), (float)this.titleX, (float)this.titleY + 10, 4210752);
        this.font.drawString(matrixStack, String.valueOf(this.purifier.data.storage.getEnergyStored()), (float)this.titleX + 40, (float)this.titleY + 10, 0x616600);
        this.font.drawString(matrixStack, "/", (float)this.titleX + 75, (float)this.titleY + 10, 4210752);
        this.font.drawString(matrixStack, String.valueOf(PurifierData.MAX_RF), (float)this.titleX + 85, (float)this.titleY + 10, 0x00800d);
        this.font.drawString(matrixStack, "RF", (float)this.titleX + 120, (float)this.titleY + 10, 0x800000);
        if(this.purifier.data.isActive()){
            this.font.drawText(matrixStack,
                    new TranslationTextComponent("gui.netheriteroadii.ancient_purifier.active"), (float)this.titleX, (float)this.titleY + 20, 0x00800d);
        }
        else {
            this.font.drawText(matrixStack,
                    new TranslationTextComponent("gui.netheriteroadii.ancient_purifier.nonactive"), (float)this.titleX, (float)this.titleY + 20, 0x800000);
        }
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
        getEnergyProgressBar();
        blit(matrixStack, this.guiLeft + 68, guiTop + 43, 0, 0, 32, 8, 32, 8);
        RenderSystem.disableBlend();

    }
}
