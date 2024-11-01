package Crudpack;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codetask;

    private String intituleTask;

    @ManyToOne
    private User user;
    
    @ManyToMany
    @JoinTable(
        name = "task_service", // Name of the join table
        joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "codetask"),
        inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "codeservice")
    )
    private List<Service> services = new ArrayList<>(); 



    public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Task() {
        super();
    }

    public Task(int codetask, String intituleTask) {
        this.codetask = codetask;
        this.intituleTask = intituleTask;
    }

    @Override
	public String toString() {
		return "Task [codetask=" + codetask + ", intituleTask=" + intituleTask + ", user=" + user + ", services="
				+ services + "]";
	}

    public int getCodetask() {
        return codetask;
    }

    public void setCodetask(int codetask) {
        this.codetask = codetask;
    }

    public String getIntituleTask() {
        return intituleTask;
    }

    public void setIntituleTask(String intituleTask) {
        this.intituleTask = intituleTask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addService(Service service) {
        services.add(service);
        service.getTasks().add(this);
    }

    public void removeService(Service service) {
        services.remove(service);
        service.getTasks().remove(this);
    }
}
