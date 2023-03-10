package com.zahariaca.nflowtest.flow;

import org.joda.time.DateTime;

import io.nflow.engine.workflow.definition.NextAction;
import io.nflow.engine.workflow.definition.StateExecution;
import io.nflow.engine.workflow.definition.WorkflowDefinition;
import io.nflow.engine.workflow.definition.WorkflowStateType;
import org.springframework.stereotype.Component;

import static io.nflow.engine.workflow.definition.WorkflowStateType.manual;
import static io.nflow.engine.workflow.definition.WorkflowStateType.start;

@Component
public class ExampleWorkflow extends WorkflowDefinition {

    public static final String TYPE = "repeatingWorkflow";
    public static final String VAR_COUNTER = "VAR_COUNTER";

    public enum State implements io.nflow.engine.workflow.definition.WorkflowState {
        repeat(start, "Repeating state"),
        error(manual, "Error state");

        private WorkflowStateType type;
        private String description;

        State(WorkflowStateType type, String description) {
            this.type = type;
            this.description = description;
        }

        @Override
        public WorkflowStateType getType() {
            return type;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    public ExampleWorkflow() {
        super(TYPE, State.repeat, State.error);
        permit(State.repeat, State.repeat);
    }

    public NextAction repeat(StateExecution execution) {
        System.out.println("Counter: " + execution.getVariable(VAR_COUNTER));
        execution.setVariable(VAR_COUNTER, execution.getVariable(VAR_COUNTER, Integer.class) + 1);
        if (Integer.valueOf(execution.getVariable(VAR_COUNTER)) == 100) {
            return NextAction.moveToState(State.error, "counter over 100");
        }
        return NextAction.moveToStateAfter(State.repeat, DateTime.now().plusSeconds(10), "Next iteration");
    }
}