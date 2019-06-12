package catchBox;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {

    protected int[][] matrix;
    private int steps;
    private int catchLine;
    private int catchColumn;
    private int lineGoal;
    private int columnGoal;

    public CatchState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.CATCH){
                    catchLine = i;
                    catchColumn = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        fireUpdatedEnvironment();
    }

    public boolean canMoveUp() {
        return catchLine != 0 && matrix[catchLine-1][catchColumn]!=Properties.WALL;
    }

    public boolean canMoveRight() {
        return catchColumn != matrix.length - 1 && matrix[catchLine][catchColumn + 1]!= Properties.WALL;
    }

    public boolean canMoveDown() {
        return catchLine != matrix.length - 1 && matrix[catchLine+1][catchColumn]!=Properties.WALL;
    }

    public boolean canMoveLeft() {
        return catchColumn != 0 && matrix[catchLine][catchColumn-1]!=Properties.WALL;
    }

    public void moveUp() {
        matrix[catchLine][catchColumn] = Properties.EMPTY;
        matrix[--catchLine][catchColumn] = Properties.CATCH;
    }

    public void moveRight() {
        matrix[catchLine][catchColumn] = Properties.EMPTY;
        matrix[catchLine][++catchColumn] = Properties.CATCH;
    }

    public void moveDown() {
        matrix[catchLine][catchColumn] = Properties.EMPTY;
        matrix[++catchLine][catchColumn] = Properties.CATCH;
    }

    public void moveLeft() {
        matrix[catchLine][catchColumn] = Properties.EMPTY;
        matrix[catchLine][--catchColumn] = Properties.CATCH;
    }

    public double computeDistance(Cell goalPosition) {
        return Math.abs(catchLine-goalPosition.getLine()) + Math.abs(catchColumn-goalPosition.getColumn());
    }

    public int getNumBox() {

        int numBox = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                if (this.matrix[i][j] == 2){
                    numBox ++;
                }
            }
        }
        return numBox;
    }

    public void setCellCatch(int line, int column) {

        matrix[catchLine][catchColumn] = Properties.EMPTY;
        this.catchLine = line;
        this.catchColumn = column;
        matrix[catchLine][catchColumn] = Properties.CATCH;
    }

    public int getCatchLine() {
        return catchLine;
    }

    public int getCatchColumn() {
        return catchColumn;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setGoal(int line, int column) {
        this.lineGoal = line;
        this.columnGoal = column;
    }

    public int getLineGoal() {
        return lineGoal;
    }

    public int getColumnGoal() {
        return columnGoal;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public CatchState clone() {
        return new CatchState(matrix);
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

}
