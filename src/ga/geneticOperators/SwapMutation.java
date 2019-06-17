package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class SwapMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public SwapMutation(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //swap
        int randomIndex;
        int randomIndex2;

        do {
            randomIndex = GeneticAlgorithm.random.nextInt(ind.getNumGenes() - 1);
            randomIndex2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes() - 1);
        }while (randomIndex == randomIndex2);
        int auxGene1 = ind.getGene(randomIndex);
        int auxGene2 = ind.getGene(randomIndex2);
        ind.setGene(randomIndex, auxGene2);
        ind.setGene(randomIndex2, auxGene1);
    }

    @Override
    public String toString() {
        return "Probility of Swap = " + probability;
    }
}