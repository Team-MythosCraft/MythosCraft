package com.crypticcosmos.crypticcosmos.datagen;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.Arrays;

import static com.crypticcosmos.crypticcosmos.registries.BlockRegistries.*;

public class ItemTagGenerator extends ItemTagsProvider {

    public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, CrypticCosmos.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        addItemProvider(ItemTags.LEAVES, MONDROVE_LEAVES.get());
        addItemProvider(ItemTags.LOGS, MONDROVE_LOG.get());
        addItemProvider(ItemTags.PLANKS, MONDROVE_PLANKS.get());
        addItemProvider(ItemTags.SAPLINGS, MONDROVE_SAPLING.get());
        addItemProvider(ItemTags.LEAVES, OSMINSTEM_CAP.get());
        addItemProvider(ItemTags.SAPLINGS, STINKY_OSMIN.get());
    }

    private void addItemProvider(ITag.INamedTag<Item> tag, IItemProvider... itemProvider) {
        Arrays.stream(itemProvider).forEach((item) -> tag(tag).add(item.asItem()));
    }
}
