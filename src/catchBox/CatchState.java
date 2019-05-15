package catchBox;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[][] matrix;
    private int lineBlank;
    private int columnBlank;
    private int steps;
    private int catchLine;
    private int catchColumn;
    private int lineGoal;
    private int columnGoal;

    public CatchState(int[][] matrix) {
        //TODO
        this.matrix = new int[matrix.length][matrix.length];
        this.steps = 0;

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 0){
                    lineBlank = i;
                    columnBlank = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();

        throw new UnsupportedOperationException("Not Implemented Yet"); // delete after implementing
    }

    public boolean canMoveUp() {
        //TODO
        return lineBlank != 0;
    }

    public boolean canMoveRight() {
        //TODO
        return columnBlank != matrix.length - 1;
    }

    public boolean canMoveDown() {
        //TODO
        return lineBlank != matrix.length - 1;
    }

    public boolean canMoveLeft() {
        //TODO
        return columnBlank != 0;
    }

    public void moveUp() {
        //TODO
        matrix[lineBlank][columnBlank] = matrix[--lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = 0;
        steps++;
    }

    public void moveRight() {
        //TODO
        matrix[lineBlank][columnBlank] = matrix[lineBlank][++columnBlank];
        matrix[lineBlank][columnBlank] = 0;
        steps++;
    }

    public void moveDown() {
        //TODO
        matrix[lineBlank][columnBlank] = matrix[++lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = 0;
        steps++;
    }

    public void moveLeft() {
        //TODO
        matrix[lineBlank][columnBlank] = matrix[lineBlank][--columnBlank];
        matrix[lineBlank][columnBlank] = 0;
        steps++;
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

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 1){
                    this.matrix[i][j] = 0;
                }
            }
        }
        this.matrix[line][column] = 1;
        this.catchLine = line;
        this.catchColumn = column;
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
