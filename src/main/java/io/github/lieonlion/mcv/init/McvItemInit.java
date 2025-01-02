package io.github.lieonlion.mcv.init;

import io.github.lieonlion.mcv.MoreChestVariants;
import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.block.MoreTrappedChestBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class McvItemInit {
    public static final BlockItem OAK_CHEST_I = itemFromBlock(McvBlockInit.OAK_CHEST);
    public static final BlockItem SPRUCE_CHEST_I = itemFromBlock(McvBlockInit.SPRUCE_CHEST);
    public static final BlockItem BIRCH_CHEST_I = itemFromBlock(McvBlockInit.BIRCH_CHEST);
    public static final BlockItem JUNGLE_CHEST_I = itemFromBlock(McvBlockInit.JUNGLE_CHEST);
    public static final BlockItem ACACIA_CHEST_I = itemFromBlock(McvBlockInit.ACACIA_CHEST);
    public static final BlockItem DARK_OAK_CHEST_I = itemFromBlock(McvBlockInit.DARK_OAK_CHEST);
    public static final BlockItem MANGROVE_CHEST_I = itemFromBlock(McvBlockInit.MANGROVE_CHEST);
    public static final BlockItem CHERRY_CHEST_I = itemFromBlock(McvBlockInit.CHERRY_CHEST);
    public static final BlockItem PALE_OAK_CHEST_I = itemFromBlock(McvBlockInit.PALE_OAK_CHEST);
    public static final BlockItem BAMBOO_CHEST_I = itemFromBlock(McvBlockInit.BAMBOO_CHEST);
    public static final BlockItem CRIMSON_CHEST_I = itemFromBlock(McvBlockInit.CRIMSON_CHEST);
    public static final BlockItem WARPED_CHEST_I = itemFromBlock(McvBlockInit.WARPED_CHEST);

    public static final BlockItem OAK_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.OAK_TRAPPED_CHEST);
    public static final BlockItem SPRUCE_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.SPRUCE_TRAPPED_CHEST);
    public static final BlockItem BIRCH_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.BIRCH_TRAPPED_CHEST);
    public static final BlockItem JUNGLE_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.JUNGLE_TRAPPED_CHEST);
    public static final BlockItem ACACIA_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.ACACIA_TRAPPED_CHEST);
    public static final BlockItem DARK_OAK_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.DARK_OAK_TRAPPED_CHEST);
    public static final BlockItem MANGROVE_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.MANGROVE_TRAPPED_CHEST);
    public static final BlockItem CHERRY_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.CHERRY_TRAPPED_CHEST);
    public static final BlockItem PALE_OAK_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.PALE_OAK_TRAPPED_CHEST);
    public static final BlockItem BAMBOO_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.BAMBOO_TRAPPED_CHEST);
    public static final BlockItem CRIMSON_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.CRIMSON_TRAPPED_CHEST);
    public static final BlockItem WARPED_TRAPPED_CHEST_I = itemFromBlock(McvBlockInit.WARPED_TRAPPED_CHEST);

    public static BlockItem itemFromBlock(Block block) {
        return new BlockItem(block, setProperties(block));
    }

    public static Item.Properties setProperties(Block block) {
        return new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, BuiltInRegistries.BLOCK.getKey(block))).useBlockDescriptionPrefix();
    }
    
    public static void registerItems() {
        registerItem(OAK_CHEST_I, OAK_TRAPPED_CHEST_I, Items.CHEST, Items.TRAPPED_CHEST);
        registerItem(SPRUCE_CHEST_I, SPRUCE_TRAPPED_CHEST_I, OAK_CHEST_I, OAK_TRAPPED_CHEST_I);
        registerItem(BIRCH_CHEST_I, BIRCH_TRAPPED_CHEST_I, SPRUCE_CHEST_I, SPRUCE_TRAPPED_CHEST_I);
        registerItem(JUNGLE_CHEST_I, JUNGLE_TRAPPED_CHEST_I, BIRCH_CHEST_I, BIRCH_TRAPPED_CHEST_I);
        registerItem(ACACIA_CHEST_I, ACACIA_TRAPPED_CHEST_I, JUNGLE_CHEST_I, JUNGLE_TRAPPED_CHEST_I);
        registerItem(DARK_OAK_CHEST_I, DARK_OAK_TRAPPED_CHEST_I, ACACIA_CHEST_I, ACACIA_TRAPPED_CHEST_I);
        registerItem(MANGROVE_CHEST_I, MANGROVE_TRAPPED_CHEST_I, DARK_OAK_CHEST_I, DARK_OAK_TRAPPED_CHEST_I);
        registerItem(CHERRY_CHEST_I, CHERRY_TRAPPED_CHEST_I, MANGROVE_CHEST_I, MANGROVE_TRAPPED_CHEST_I);
        registerItem(PALE_OAK_CHEST_I, PALE_OAK_TRAPPED_CHEST_I, CHERRY_CHEST_I, CHERRY_TRAPPED_CHEST_I);
        registerItem(BAMBOO_CHEST_I, BAMBOO_TRAPPED_CHEST_I, PALE_OAK_CHEST_I, PALE_OAK_TRAPPED_CHEST_I);
        registerItem(CRIMSON_CHEST_I, CRIMSON_TRAPPED_CHEST_I, BAMBOO_CHEST_I, BAMBOO_TRAPPED_CHEST_I);
        registerItem(WARPED_CHEST_I, WARPED_TRAPPED_CHEST_I, CRIMSON_CHEST_I, CRIMSON_TRAPPED_CHEST_I);
    }

    private static void registerItem(BlockItem chest, BlockItem trappedChest, Item chestAfter, Item trappedAfter) {
        Registry.register(BuiltInRegistries.ITEM, MoreChestVariants.asId(((MoreChestBlock) chest.getBlock()).chestType + "_chest"), chest);
        Registry.register(BuiltInRegistries.ITEM, MoreChestVariants.asId(((MoreTrappedChestBlock) trappedChest.getBlock()).chestType + "_trapped_chest"), trappedChest);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(chestAfter, chest));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> entries.addAfter(chestAfter,chest));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> entries.addAfter(trappedAfter, trappedChest));
    }
}