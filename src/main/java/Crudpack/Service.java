package Crudpack;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeservice;

    private String intituleService;

    @ManyToMany(mappedBy = "services")
    private List<Task> tasks = new ArrayList<>();

    public int getCodeservice() {
        return codeservice;
    }

    public void setCodeservice(int codeservice) {
        this.codeservice = codeservice;
    }

    public String getIntituleService() {
        return intituleService;
    }

    public void setIntituleService(String intituleService) {
        this.intituleService = intituleService;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.getServices().add(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.getServices().remove(this);
    }


    @Override
    public String toString() {
        return "Service [codeservice=" + codeservice + ", intituleService=" + intituleService + "]";
    }

    public Service() {
    }

    public Service(String name) {
        this.intituleService = name;
    }
}
