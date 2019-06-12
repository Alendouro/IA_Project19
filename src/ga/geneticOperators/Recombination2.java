package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //One cut recombination
        int cut = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        for (int i = 0; i < cut; i++) {
            ind1.swapGenes(ind2, i);
        }
    }

    @Override
    public String toString(){
        return "One cut combination: " + probability;
    }
}