package com.supermartijn642.additionalblocks.stone;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created 1/20/2021 by SuperMartijn642
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldGeneration {

    public static ConfiguredFeature<?, ?> ore_marble;
    public static ConfiguredFeature<?, ?> ore_limestone;
    public static ConfiguredFeature<?, ?> ore_bloodstone;
    public static ConfiguredFeature<?, ?> mud;
    public static ConfiguredFeature<?, ?> ore_volcanic_stone;
    public static ConfiguredFeature<?, ?> ore_volcanic_stone_bricks;
    public static ConfiguredFeature<?, ?> ore_black_marble;
    public static ConfiguredFeature<?, ?> ore_silver;
    public static ConfiguredFeature<?, ?> ore_copper;

    //
    public static void onFeatureRegistry(final RegistryEvent.Register<Feature<?>> e) {
        ore_marble = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, AdditionalBlocks.marble.getDefaultState(), 25));
        ore_marble = ore_marble.range(60).square().func_242731_b(6);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_marble"),
                ore_marble
        );
        ore_limestone = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, AdditionalBlocks.limestone.getDefaultState(), 25));
        ore_limestone = ore_limestone.range(60).square().func_242731_b(6);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_limestone"),
                ore_limestone
        );
        ore_copper = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, AdditionalBlocks.copper_ore.getDefaultState(), 10));
        ore_copper = ore_copper.range(64).square().func_242731_b(20);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_copper"),
                ore_copper
        );
        ore_silver = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, AdditionalBlocks.silver_ore.getDefaultState(), 9));
        ore_silver = ore_silver.range(64).square().func_242731_b(20);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_silver"),
                ore_silver
        );
        ore_bloodstone = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, AdditionalBlocks.bloodstone.getDefaultState(), 25));
        ore_bloodstone = ore_bloodstone.range(60).square().func_242731_b(6);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_bloodstone"),
                ore_bloodstone
        );
        ore_black_marble = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, AdditionalBlocks.black_marble.getDefaultState(), 25));
        ore_black_marble = ore_black_marble.range(60).square().func_242731_b(6);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_black_marble"),
                ore_black_marble
        );
        ore_volcanic_stone_bricks = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, AdditionalBlocks.volcanic_stone_bricks.getDefaultState(), 25));
        ore_volcanic_stone_bricks = ore_volcanic_stone_bricks.range(60).square().func_242731_b(6);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_volcanic_stone_bricks"),
                ore_volcanic_stone_bricks
        );
        ore_volcanic_stone = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, AdditionalBlocks.volcanic_stone.getDefaultState(), 30));
        ore_volcanic_stone = ore_volcanic_stone.range(60).square().func_242731_b(8);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("abstoneedition", "ore_volcanic_stone"),
                ore_volcanic_stone
        );
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onBiomeLoad(BiomeLoadingEvent e) {
        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, e.getName());

        // all overworld biomes
        if (BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD).contains(biomeKey)) {
            if (!(e.getName().getNamespace().equals("minecraft") && (e.getName().getPath().equals("desert")||e.getName().getPath().equals("desert_hills")||e.getName().getPath().equals("desert_lakes")))) {
                if(AdditionalBlocksConfig.enableMarble.get())
                    e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_marble);
            }
            if (e.getName().getNamespace().equals("minecraft") && (e.getName().getPath().equals("desert")||e.getName().getPath().equals("desert_hills")||e.getName().getPath().equals("desert_lakes"))) {
                if(AdditionalBlocksConfig.enableLimestone.get())
                    e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_limestone);
            }
            if (e.getName().getNamespace().equals("minecraft") && (e.getName().getPath().equals("snowy_mountains")||e.getName().getPath().equals("modified_gravelly_mountains")||e.getName().getPath().equals("gravelly_mountains")||e.getName().getPath().equals("stone_shore"))) {
                if(AdditionalBlocksConfig.enableVolcanicStone.get())
                    e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_volcanic_stone);
            }
            if(AdditionalBlocksConfig.enableCopper.get())
                e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_copper);
            if(AdditionalBlocksConfig.enableSilver.get())
                e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_silver);
        }

        // all nether biomes
        if (BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).contains(biomeKey)) {
            if (e.getName().getNamespace().equals("minecraft") && (e.getName().getPath().equals("soul_sand_valley")||e.getName().getPath().equals("basalt_deltas"))) {
                if(AdditionalBlocksConfig.enableBlackMarble.get())
                    e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_black_marble);
            }
            if (e.getName().getNamespace().equals("minecraft") && (e.getName().getPath().equals("soul_sand_valley")||e.getName().getPath().equals("basalt_deltas"))) {
                if(AdditionalBlocksConfig.enableVolcanicStone.get())
                    e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_volcanic_stone_bricks);
            }
            if(AdditionalBlocksConfig.enableBloodstone.get())
                e.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore_bloodstone);
        }

        // all end biomes
        if (BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD).contains(biomeKey)) {

        }
    }

}
