package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.model.Customer;
import org.academiadecodigo.rememberthename.model.Reservation;

import java.util.*;

public class CustomerServiceImpl {

    private Map<Integer, Customer> customerMap = new HashMap<>();

    private Integer getNextId() {
        return customerMap.isEmpty() ? 1 :Collections.max(customerMap.keySet()) +1;
    }

    public Customer get(Integer id) {
        return customerMap.get(id);
    }

    public Set<Integer> listCustomerReservationsIds (Integer id) {

        Set<Integer> reservationIds = new HashSet<>();
        List<Reservation> reservationList = get(id).getReservations();

        for (Reservation reservation : reservationList) {
            reservationIds.add(reservation.getId());
        }

        return reservationIds;
    }

    public void add(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        customerMap.put(customer.getId(), customer);
    }
}
