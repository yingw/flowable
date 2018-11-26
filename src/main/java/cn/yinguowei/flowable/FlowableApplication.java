package cn.yinguowei.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yinguowei
 */
@SpringBootApplication
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }

}

@Order(1)
@Component
class DemoUser implements CommandLineRunner {

    private final FlowableService myService;

    DemoUser(FlowableService myService) {
        this.myService = myService;
    }

    @Override
    public void run(String... args) {
        System.out.println("FlowableApplication.initUser");
        myService.createDemoUsers();
    }
}

@Order(2)
@Component
class FlowableInfo implements CommandLineRunner {
    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    FlowableInfo(RepositoryService repositoryService, RuntimeService runtimeService, TaskService taskService) {
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) {
        System.out.println("FlowableApplication.flowableInfo");
        System.out.println("Number of process definitions : "
                + repositoryService.createProcessDefinitionQuery().count());
        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());

        Person person = new Person();
        person.setId(1L);
        Map<String, Object> variables = new HashMap<>();
        variables.put("person", person);

        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);

        System.out.println("Number of tasks after process start: "
                + taskService.createTaskQuery().count());

    }
}