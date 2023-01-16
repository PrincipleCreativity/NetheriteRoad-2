package principledev.netheriteroadii.common.init;

import principledev.netheriteroadii.NetheriteRoadII;
import principledev.netheriteroadii.common.features.FeatureDeferredRegister;
import principledev.netheriteroadii.common.features.FeatureRegistryObject;
import principledev.netheriteroadii.common.features.SliverOreFeature;

public class NeroFeatures {
    public static final FeatureDeferredRegister FEATURES = new FeatureDeferredRegister(NetheriteRoadII.MOD_ID);
    public static final FeatureRegistryObject<?, SliverOreFeature> SLIVER_ORE_FEATURE = FEATURES.register("sliver_ore_feature", SliverOreFeature::new);
}
