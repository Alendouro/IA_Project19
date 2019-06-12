package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {

    private LinkedList<Action> actions;

    //private final CatchState goalState;
    //  Create a Catch where we set the goal to the goalPosition (Cell) and then in the isGoal function we just do a get of the line of the goal state abd cnoare it (but it would take more lines of code)
    private Cell goalPosition;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        actions = new LinkedList<Action>() {{
            add(new ActionDown());
            add(new ActionRight());
            add(new ActionUp());
            add(new ActionLeft());
        }};

        this.goalPosition = goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {

        List<S> successors = new ArrayList<>();

        for (Action action : actions) {
            if(action.isValid(state)) {
                S successor = (S) state.clone();
                action.execute(successor);
                successors.add(successor);
            }
        }
        return successors;
    }

    public Cell getGoalPosition() {
        return goalPosition;
    }

    public boolean isGoal(S state) {
        return goalPosition.getColumn() == state.getCatchColumn() && goalPosition.getLine() == state.getCatchLine();
    }
}
