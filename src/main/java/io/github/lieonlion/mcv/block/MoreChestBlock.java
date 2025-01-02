package io.github.lieonlion.mcv.block;

import io.github.lieonlion.mcv.MoreChestVariants;
import io.github.lieonlion.mcv.block.entity.MoreChestBlockEntity;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

public class MoreChestBlock extends ChestBlock {
    public DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> NAME_RETRIEVER;
    public final String chestType;

    public MoreChestBlock(MapColor colour, String chestType) {
        super(() -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY, Properties.ofFullCopy(Blocks.CHEST).mapColor(colour).setId(ResourceKey.create(Registries.BLOCK, MoreChestVariants.asId(chestType + "_chest"))));
        this.chestType = chestType;

        registerMaterialNameRetriever();
    }

    public MoreChestBlock(MapColor colour, SoundType sound, String chestType) {
        super(() -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY, Properties.ofFullCopy(Blocks.CHEST).mapColor(colour).sound(sound).setId(ResourceKey.create(Registries.BLOCK, MoreChestVariants.asId(chestType + "_chest"))));
        this.chestType = chestType;

        registerMaterialNameRetriever();
    }

    public MoreChestBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, String chestType) {
        super(supplier, properties);
        this.chestType = chestType;

        registerMaterialNameRetriever();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MoreChestBlockEntity(pos, state);
    }

    protected void registerMaterialNameRetriever() {
        NAME_RETRIEVER = new DoubleBlockCombiner.Combiner<>() {
            public Optional<MenuProvider> acceptDouble(ChestBlockEntity chestBlockEntity, ChestBlockEntity chestBlockEntity2) {
                final Container container = new CompoundContainer(chestBlockEntity, chestBlockEntity2);
                return Optional.of(new MenuProvider() {
                    @Nullable
                    public AbstractContainerMenu createMenu(int p_51622_, Inventory p_51623_, Player p_51624_) {
                        if (chestBlockEntity.canOpen(p_51624_) && chestBlockEntity2.canOpen(p_51624_)) {
                            chestBlockEntity.unpackLootTable(p_51623_.player);
                            chestBlockEntity2.unpackLootTable(p_51623_.player);
                            return ChestMenu.sixRows(p_51622_, p_51623_, container);
                        } else {
                            return null;
                        }
                    }

                    public Component getDisplayName() {
                        if (chestBlockEntity.hasCustomName()) {
                            return chestBlockEntity.getDisplayName();
                        } else {
                            return chestBlockEntity2.hasCustomName() ? chestBlockEntity2.getDisplayName() :
                                    Component.translatable("container.lolmcv." + chestType + "_chestDouble");
                        }
                    }
                });
            }

            public Optional<MenuProvider> acceptSingle(ChestBlockEntity chestBlockEntity) {
                return Optional.of(chestBlockEntity);
            }

            public Optional<MenuProvider> acceptNone() {
                return Optional.empty();
            }
        };
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false)
                .apply(NAME_RETRIEVER).orElse(null);
    }
}