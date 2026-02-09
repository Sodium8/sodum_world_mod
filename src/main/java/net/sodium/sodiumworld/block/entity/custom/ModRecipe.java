package net.sodium.sodiumworld.block.entity.custom;

import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModRecipe<T> {
    private final List<T> input;
    public ModRecipe(List<T> input){
        this.input = input;
    }
    public boolean try_craft(List<T> input){
        for (int i = 0; i < this.input.toArray().length; i++){
            if (!input.contains(this.input.get(i))){
                return false;
            }
        }
        return true;
    }
}
