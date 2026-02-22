package net.sodium.sodiumworld.util;

import net.sodium.sodiumworld.block.entity.custom.GasContainerEntity;
import net.sodium.sodiumworld.block.entity.custom.Utils.GasRecipeCondition;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GasRecipe {
    public List<GasStack> input;
    public List<GasStack> output;
    public GasContainerEntity gasContainer;
    public GasRecipeCondition condition;
    public GasRecipe(GasContainerEntity gasContainer, List<GasStack> input, List<GasStack> output, GasRecipeCondition condition){
        this.input = input;
        this.output = output;
        this.gasContainer = gasContainer;
        this.condition = condition;
    }
    public boolean try_to_craft(){
        if (condition.complete(gasContainer)){
            float output_sum = 0;
            for (GasStack i :this.output){
                output_sum += i.getVolume();
            }
            if (output_sum <= gasContainer.leftFreePlace()){
                if(can_craft()){
                    for (GasStack i : this.input){
                        gasContainer.addGas(gasContainer, i.getId(), -i.getVolume());
                    }
                    for (GasStack i : this.output){
                        gasContainer.addGas(gasContainer, i.getId(), i.getVolume());
                    }
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    private boolean can_craft(){
        for (GasStack i:input){
            if(!(gasContainer.getGasAmount(i.getId()) >= i.getVolume())){
                return false;
            }
        }
        return true;
    }
}
