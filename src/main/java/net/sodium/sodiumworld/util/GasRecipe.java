package net.sodium.sodiumworld.util;

import net.sodium.sodiumworld.block.entity.custom.GasContainerEntity;

import java.util.List;

public class GasRecipe {
    public List<GasStack> input;
    public List<GasStack> output;
    public GasContainerEntity gasContainer;
    public GasRecipe(GasContainerEntity gasContainer, List<GasStack> input, List<GasStack> output){
        this.input = input;
        this.output = output;
        this.gasContainer = gasContainer;
    }
    public boolean try_to_craft(){
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
