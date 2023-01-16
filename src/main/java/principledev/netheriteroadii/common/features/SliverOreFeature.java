package principledev.netheriteroadii.common.features;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.init.BlockRegister;

import java.util.Random;

public class SliverOreFeature extends OreFeature {
    public final ConfiguredFeature<?, ?> configuredFeature = this.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockRegister.SLIVER_ORE.get().getDefaultState(), 10)).range(48)
					.square().count(5);
    public SliverOreFeature() {
        super(OreFeatureConfig.CODEC);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(NetheriteRoadII.MOD_ID, "sliver_feature"), configuredFeature);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        RegistryKey<World> dimensionType = reader.getWorld().getDimensionKey();
        boolean dimensionCriteria = dimensionType == World.OVERWORLD;
        if (!dimensionCriteria) {
            return false;
        }
        return super.generate(reader, generator, rand, pos, config);
    }

}
