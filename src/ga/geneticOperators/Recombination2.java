package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;

    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //One cut recombination
        child1 = new int[ind2.getNumGenes()];
        child2 = new int[ind1.getNumGenes()];
        int aux1 = 0;
        int aux2 = 0;

        for (int i = 1; i < ind2.getGenome().length - 1; i++) {
            child1[i] = ind2.getGenome()[i];
            child2[i] = ind1.getGenome()[i];
        }

        for (int i = 0; i < ind1.getGenome().length; i++) {
            if (!containsNumber(child1, ind1.getGenome()[i])){
                child1[aux1] = ind1.getGenome()[i];
                aux1 = child1.length - 1;
            }
            if (!containsNumber(child2, ind2.getGenome()[i])){
                child2[aux2] = ind2.getGenome()[i];
                aux2 = child2.length - 1;
            }
        }

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }

    public boolean containsNumber(int [] ind, int num){
        for (int i = 0; i < ind.length; i++) {
            if (ind[i] == num) return true;
        }
        return false;
    }


    @Override
    public String toString(){
        return "One cut combination: " + probability;
    }
}