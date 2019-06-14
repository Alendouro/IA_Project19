package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind2.getNumGenes()];
        child2 = new int[ind1.getNumGenes()];
        int number1 = GeneticAlgorithm.random.nextInt(ind2.getNumGenes());
        System.out.println("number 1" + number1);
        System.out.println("Individuo 1" + Arrays.toString(ind1.getGenome()));
        System.out.println("Individuo 2" + Arrays.toString(ind2.getGenome()));

        for (int i = 1; i < ind1.getGenome().length - 1; i++) {
            child1[i] = ind1.getGenome()[i];
            child2[i] = ind2.getGenome()[i];
        }
    }

    @Override
    public String toString(){
        return "Two Cut Recombination: " + probability;
    }
}