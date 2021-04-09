package org.academiadecodigo.rememberthename.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation extends AbstractModel {

    private String name;
    private int numberOfClients;
    private int pickUpDate;

    @ManyToOne
    private Customer customer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(int pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
}
