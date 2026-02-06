package net.sodium.sodiumworld.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModTreeGeneration.generateTrees();
        ModBeachGeneration.addChalkToBeaches();
    }
}
