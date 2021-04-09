package org.academiadecodigo.rememberthename.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer extends AbstractModel{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date date;
    private String notes;
    private int numberOfCustomer;

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(int numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    private List<Reservation> reservations = new ArrayList<>();


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setCustomer(this);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservations);
        reservation.setCustomer(null);
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
