package catchBox;

import ga.Problem;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    private LinkedList<Cell> cellsBoxes;
    private LinkedList<Pair> pairs;
    private Cell cellCatch;
    private Cell door;

    public CatchProblemForGA(
            LinkedList<Cell> cellsBoxes,
            LinkedList<Pair> pairs,
            Cell cellCatch,
            Cell door) {
        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;
    }

    public LinkedList<Cell> getCellsBoxes() {
        return cellsBoxes;
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }

    public Cell getCellCatch() {
        return cellCatch;
    }

    public Cell getDoor() {
        return door;
    }

    @Override
    public CatchIndividual getNewIndividual() {
        return new CatchIndividual(this, cellsBoxes.size());
    }

    @Override
    public String toString() {
        return "Cells boxes: " + cellsBoxes.size();
    }
}
