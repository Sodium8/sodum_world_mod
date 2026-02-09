package net.sodium.sodiumworld.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;

public class ModTags {
    public static class Blocks {
        public static TagKey<Block> CONTAIN_GAS = createTag("contain_gas");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(SodiumWorld.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(SodiumWorld.MOD_ID, name));
        }
    }
}
