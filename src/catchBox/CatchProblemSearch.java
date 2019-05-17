package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    //TODO this class might require the definition of additional methods and/or attributes

    private final CatchState goalState;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        initialCatchState.setGoal(goalPosition.getLine(), goalPosition.getColumn());
        this.goalState = new CatchState(initialCatchState.getMatrix());
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        List<S> successors = new ArrayList<>();
        for (Action action : availableActions) {
            if(action.isValid(state)) {
                S successor = (S) state.clone();
                action.execute(successor);
                successors.add(successor);
            }
        }
        //TODO
        return successors;
    }

    public boolean isGoal(S state) {
        return goalState.getColumnGoal() == state.getCatchColumn() && goalState.getLineGoal() == state.getCatchLine();
    }
}
