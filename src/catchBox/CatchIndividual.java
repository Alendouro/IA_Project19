package catchBox;

import ga.IntVectorIndividual;

import java.util.LinkedList;

public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {

    public CatchIndividual(CatchProblemForGA problem, int size) {
        super(problem, size);
    }

    public CatchIndividual(CatchIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        //Percorrer os pares todos, depois usar a auxiliar (cell1 = cell2 ou o contrario) se sim fitness+= pair.getValue(); (este for e para os do meio), da primeira posicao a ultima, e ao contrario.
        fitness = 0;
        LinkedList<Cell> boxes = problem.getCellsBoxes();
        Cell catchCell = problem.getCellCatch();
        Cell door = problem.getDoor();
        fitness = getPairValue(catchCell, boxes.get(genome[0]-1)); //Entre a posicao zero do genome e da catchCell
        // Entre a primeira posicao do genoma até a ultima (pela ordem)
        for (int i = 0; i < genome.length - 1; i++) {
            fitness += getPairValue(boxes.get(genome[i] - 1), boxes.get(genome[i + 1] - 1));
        }
        // Fitness entre a ultima posicao e a door
        fitness += getPairValue(boxes.get(genome[genome.length - 1] - 1), door);

        return fitness;
    }

    private double getPairValue(Cell c1, Cell c2) {
        LinkedList<Pair> pairs = problem.getPairs();
        for (Pair pair: pairs) {
            if (pair.getCell1() == c1 && pair.getCell2() == c2 || pair.getCell1() == c2 && pair.getCell2() == c1){
                return pair.getValue();
            }
        }
        return -1;
    }

    public int[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i <genome.length ; i++) {
            sb.append(genome[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(CatchIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }
}
