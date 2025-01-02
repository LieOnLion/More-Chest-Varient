package io.github.lieonlion.mcv.init;

import io.github.lieonlion.mcv.MoreChestVariants;
import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.block.MoreTrappedChestBlock;
import io.github.lieonlion.mcv.block.entity.MoreChestBlockEntity;
import io.github.lieonlion.mcv.block.entity.MoreTrappedChestBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;

public class McvBlockInit {
    public static final MoreChestBlock OAK_CHEST = new MoreChestBlock(MapColor.WOOD, "oak");
    public static final MoreChestBlock SPRUCE_CHEST = new MoreChestBlock(MapColor.PODZOL, "spruce");
    public static final MoreChestBlock BIRCH_CHEST = new MoreChestBlock(MapColor.SAND, "birch");
    public static final MoreChestBlock JUNGLE_CHEST = new MoreChestBlock(MapColor.DIRT, "jungle");
    public static final MoreChestBlock ACACIA_CHEST = new MoreChestBlock(MapColor.COLOR_ORANGE, "acacia");
    public static final MoreChestBlock DARK_OAK_CHEST = new MoreChestBlock(MapColor.COLOR_BROWN, "dark_oak");
    public static final MoreChestBlock MANGROVE_CHEST = new MoreChestBlock(MapColor.COLOR_RED, "mangrove");
    public static final MoreChestBlock CHERRY_CHEST = new MoreChestBlock(MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, "cherry");
    public static final MoreChestBlock PALE_OAK_CHEST = new MoreChestBlock(MapColor.QUARTZ, "pale_oak");
    public static final MoreChestBlock BAMBOO_CHEST = new MoreChestBlock(MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, "bamboo");
    public static final MoreChestBlock CRIMSON_CHEST = new MoreChestBlock(MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, "crimson");
    public static final MoreChestBlock WARPED_CHEST = new MoreChestBlock(MapColor.WARPED_STEM, SoundType.NETHER_WOOD, "warped");

    public static final MoreTrappedChestBlock OAK_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.WOOD, "oak");
    public static final MoreTrappedChestBlock SPRUCE_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.PODZOL, "spruce");
    public static final MoreTrappedChestBlock BIRCH_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.SAND, "birch");
    public static final MoreTrappedChestBlock JUNGLE_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.DIRT, "jungle");
    public static final MoreTrappedChestBlock ACACIA_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.COLOR_ORANGE, "acacia");
    public static final MoreTrappedChestBlock DARK_OAK_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.COLOR_BROWN, "dark_oak");
    public static final MoreTrappedChestBlock MANGROVE_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.COLOR_RED, "mangrove");
    public static final MoreTrappedChestBlock CHERRY_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, "cherry");
    public static final MoreTrappedChestBlock PALE_OAK_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.QUARTZ, "pale_oak");
    public static final MoreTrappedChestBlock BAMBOO_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, "bamboo");
    public static final MoreTrappedChestBlock CRIMSON_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, "crimson");
    public static final MoreTrappedChestBlock WARPED_TRAPPED_CHEST = new MoreTrappedChestBlock(MapColor.WARPED_STEM, SoundType.NETHER_WOOD, "warped");

    public static BlockEntityType<MoreChestBlockEntity> MORE_CHEST_BLOCK_ENTITY;
    public static BlockEntityType<MoreTrappedChestBlockEntity> MORE_TRAPPED_CHEST_BLOCK_ENTITY;
    public static final List<Block> more_chest = new ArrayList<>();
    public static final List<Block> more_trapped_chest = new ArrayList<>();

    public static void registerBlocks() {
        registerBlock(OAK_CHEST, OAK_TRAPPED_CHEST);
        registerBlock(SPRUCE_CHEST, SPRUCE_TRAPPED_CHEST);
        registerBlock(BIRCH_CHEST, BIRCH_TRAPPED_CHEST);
        registerBlock(JUNGLE_CHEST, JUNGLE_TRAPPED_CHEST);
        registerBlock(ACACIA_CHEST, ACACIA_TRAPPED_CHEST);
        registerBlock(DARK_OAK_CHEST, DARK_OAK_TRAPPED_CHEST);
        registerBlock(MANGROVE_CHEST, MANGROVE_TRAPPED_CHEST);
        registerBlock(CHERRY_CHEST, CHERRY_TRAPPED_CHEST);
        registerBlock(PALE_OAK_CHEST, PALE_OAK_TRAPPED_CHEST);
        registerBlock(BAMBOO_CHEST, BAMBOO_TRAPPED_CHEST);
        registerBlock(CRIMSON_CHEST, CRIMSON_TRAPPED_CHEST);
        registerBlock(WARPED_CHEST, WARPED_TRAPPED_CHEST);

        MORE_CHEST_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, "chest_entity",
                FabricBlockEntityTypeBuilder.create(MoreChestBlockEntity::new, more_chest.toArray(Block[]::new)).build());
        MORE_TRAPPED_CHEST_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, "trapped_chest_entity",
                FabricBlockEntityTypeBuilder.create(MoreTrappedChestBlockEntity::new, more_trapped_chest.toArray(Block[]::new)).build());
    }

    private static void registerBlock(MoreChestBlock chest, MoreTrappedChestBlock trappedChest) {
        Registry.register(BuiltInRegistries.BLOCK, MoreChestVariants.asId(chest.chestType + "_chest"), chest);
        Registry.register(BuiltInRegistries.BLOCK, MoreChestVariants.asId(trappedChest.chestType + "_trapped_chest"), trappedChest);
        more_chest.add(chest);
        more_trapped_chest.add(trappedChest);
    }
}