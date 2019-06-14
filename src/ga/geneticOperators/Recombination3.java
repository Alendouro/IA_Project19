package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.*;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2;

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        int start = 0, inCycle = 0;
        LinkedList<Integer> visited = new LinkedList<>();
        do {
            visited.add(inCycle);
            inCycle = ind1.getIndexof(ind2.getGene(inCycle));
        }while (start != inCycle);
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            if (!visited.contains(i)){
                ind1.setGene(i, ind2.getGene(i));
                ind2.setGene(i, ind1.getGene(i));
            }
        }

    }

    @Override
    public String toString(){
        return "Two Cut Recombination: " + probability;
    }
}