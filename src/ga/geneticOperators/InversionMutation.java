package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class InversionMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public InversionMutation(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //Inversion
        int randomPosition;
        int randomPosition2;
        do{
            randomPosition = GeneticAlgorithm.random.nextInt(ind.getNumGenes() - 1);
            randomPosition2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes() - 1);
        }while (randomPosition == randomPosition2);

        if (randomPosition > randomPosition2) {
            int aux = randomPosition;
            randomPosition = randomPosition2;
            randomPosition2 = aux;
        }

        for (int i = randomPosition; i < randomPosition2; i++) {
            for (int j = 0; j < randomPosition2-randomPosition; j++) {
                int second = ind.getGene(randomPosition2 - j);
                ind.setGene(randomPosition2 - j, ind.getGene(i));
                ind.setGene(i, second);
            }
        }
    }

    @Override
    public String toString() {
        return "Probility of Inversion = " + probability;
    }
}