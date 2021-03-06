package ga;

import java.util.Random;

public abstract class IntVectorIndividual<P extends Problem, I extends IntVectorIndividual> extends Individual<P, I> {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[] genome;

    public IntVectorIndividual(P problem, int size) {
        super(problem);
        genome = new int[size];
        int r;
        for (int i = 0; i <genome.length ; i++) {
            do{
                r = GeneticAlgorithm.random.nextInt(size) + 1;
            } while(repeatedGenome(r, i));
            genome[i] = r;
        }
      }

    public int[] getGenome() {
        return genome;
    }

    private boolean repeatedGenome(int r, int j) {
        for (int i = 0; i < j ; i++) {
            if (genome[i] == r) return true;
        }
        return false;
    }


    public IntVectorIndividual(IntVectorIndividual<P, I> original) {
        super(original);
        this.genome = new int[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }

    @Override
    public int getNumGenes() {
        return genome.length;
    }

    public int getIndexof(int value){
        for (int i = 0; i < genome.length; i++) {
            if (genome[i] == value)
                return i;
        }
        return -1;
    }

    public int getGene(int index) {
        return genome[index];
    }

    public void setGene(int index, int newValue) {
        genome[index] = newValue;
    }

    @Override
    public void swapGenes(IntVectorIndividual other, int index) {
        int aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;
    }
}
