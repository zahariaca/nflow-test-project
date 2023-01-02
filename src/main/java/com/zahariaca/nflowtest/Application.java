package com.zahariaca.nflowtest;

import com.zahariaca.nflowtest.flow.ExampleWorkflow;
import io.nflow.engine.service.WorkflowInstanceService;
import io.nflow.engine.workflow.instance.WorkflowInstanceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;

@SpringBootApplication
public class Application {

	@Inject
	private WorkflowInstanceService workflowInstances;

	@Inject
	private WorkflowInstanceFactory workflowInstanceFactory;

	@EventListener(ApplicationReadyEvent.class)
	public void insertWorkflowInstance() {
		workflowInstances.insertWorkflowInstance(workflowInstanceFactory.newWorkflowInstanceBuilder()
				.setType(ExampleWorkflow.TYPE)
				.setExternalId("example")
				.putStateVariable(ExampleWorkflow.VAR_COUNTER, 0)
				.build());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
