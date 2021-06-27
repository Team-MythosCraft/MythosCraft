package com.crypticcosmos.crypticcosmos.register;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import com.crypticcosmos.crypticcosmos.block.GrombleStalk;
import com.crypticcosmos.crypticcosmos.block.GrombleStalkTop;
import com.crypticcosmos.crypticcosmos.block.MondroveFungus;
import com.crypticcosmos.crypticcosmos.block.RiftBlock;
import com.crypticcosmos.crypticcosmos.util.RegistrationUtils;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import static com.crypticcosmos.crypticcosmos.CrypticCosmos.getRegistrate;
import static com.crypticcosmos.crypticcosmos.register.TagRegistries.GIANT_GROMBLE_BERRIES;
import static com.crypticcosmos.crypticcosmos.util.RegistrationUtils.blockTexture;

@SuppressWarnings("unused")
public class BlockRegistries {
    public static final BlockEntry<RiftBlock> RIFT_BLOCK = getRegistrate().object("rift_block")
            .block(Material.STONE, RiftBlock::new)
            .properties(p -> p.strength(40f, 1200f)
                    .sound(SoundType.STONE)
                    .noCollission()
                    .noDrops())
            .register();

    // Umbral Plains
    public static final BlockEntry<SandBlock> UMBRAL_DUNE = getRegistrate().object("umbral_dune")
            .block(Material.SAND, p -> new SandBlock(0x16, p))
            .properties(p -> p.strength(0.5F).sound(SoundType.SAND))
            .simpleItem()
            .register();

    // mondrove fungus set
    public static final BlockEntry<MondroveFungus> MONDROVE_FUNGUS = getRegistrate().object("mondrove_fungus")
            .block(Material.PLANT, MondroveFungus::new)
            .properties(p -> p.strength(0)
                    .noCollission()
                    .sound(SoundType.GRASS))
            .addLayer(() -> RenderType::cutout)
            .blockstate(RegistrationUtils::crossModel)

            .item()
            .model((context, provider) -> provider.generated(context,
                    provider.modLoc("block/" + provider.name(context)))
            )
            .build()

            .register();

    public static final BlockEntry<Block> MONDROVE_FUNGUS_BLOCK = getRegistrate().object("mondrove_fungus_block")
            .block(Block::new)
            .properties(p -> Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK))
            .tag(TagRegistries.MONDROVE_FUNGUS_PLANTABLE)
            .recipe((context, provider) -> provider.storage(MONDROVE_FUNGUS, context))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> MONDROVE_FUNGUS_SPORE_BLOCK = getRegistrate().object("mondrove_fungus_spore_block")
            .block(Block::new)
            .properties(p -> Properties.copy(MONDROVE_FUNGUS_BLOCK.get()))
            .tag(TagRegistries.MONDROVE_FUNGUS_PLANTABLE)
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_MONDROVE_FUNGUS_BLOCK = getRegistrate().object("smooth_mondrove_fungus_block")
            .block(Block::new)
            .properties(p -> Properties.copy(MONDROVE_FUNGUS_BLOCK.get()))
            .recipe((context, provider) ->
                    provider.square(DataIngredient.items(MONDROVE_FUNGUS_BLOCK), context, true)
            )
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_MONDROVE_FUNGUS_BRICKS = getRegistrate().object("smooth_mondrove_fungus_bricks")
            .block(Block::new)
            .properties(p -> Properties.copy(MONDROVE_FUNGUS_BLOCK.get()))
            .simpleItem()
            .register();

    public static final BlockEntry<GrombleStalk> GROMBLE_STALK_PLANT = getRegistrate().object("gromble_stalk_plant")
            .block(GrombleStalk::new)
            .addLayer(() -> RenderType::cutout)
            .initialProperties(Material.PLANT, MaterialColor.COLOR_CYAN)
            .properties(p -> p.noCollission().instabreak().sound(SoundType.WEEPING_VINES))
            .tag(BlockTags.CLIMBABLE)
            .blockstate((context, provider) -> provider.getVariantBuilder(context.get()).forAllStates(state ->
                    ConfiguredModel.builder().modelFile(
                            provider.models().cross(context.getName(), provider.blockTexture(context.get()))
                    ).build()
            ))
            .register();

    public static final BlockEntry<GrombleStalkTop> GROMBLE_STALK = getRegistrate().object("gromble_stalk")
            .block(GrombleStalkTop::new)
            .addLayer(() -> RenderType::cutout)
            .initialProperties(Material.PLANT, MaterialColor.COLOR_CYAN)
            .properties(p -> p.noCollission().instabreak().sound(SoundType.WEEPING_VINES))
            // this will add the loot table to both blocks
            .tag(GIANT_GROMBLE_BERRIES)
            .loot((lootTables, block) -> lootTables.addNetherVinesDropTable(block, GROMBLE_STALK_PLANT.get()))
            .blockstate((context, provider) -> provider.getVariantBuilder(context.get()).forAllStates(state ->
                    ConfiguredModel.builder().modelFile(
                            provider.models().cross(context.getName(), provider.blockTexture(context.get()))
                    ).build()
            ))

            .item()
            .model((context, provider) -> provider.generated(context, blockTexture(() -> context.get().getBlock(), "")))
            .build()

            .register();

    public static void init() {
        CrypticCosmos.LOGGER.info("BlockRegistries initialized");
    }
}