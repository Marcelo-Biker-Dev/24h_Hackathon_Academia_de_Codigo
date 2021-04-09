package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.persistence.dao.CustomerDao;
import org.academiadecodigo.rememberthename.persistence.dao.ReservationDao;
import org.academiadecodigo.rememberthename.persistence.model.AbstractModel;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao;
    private ReservationDao reservationDao;

    @Override
    public Customer get(Integer id) {
        return customerDao.findById(id);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {

        return customerDao.saveOrUpdate(customer);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        customerDao.delete(id);
    }

    @Override
    public List<Customer> list() {
        return customerDao.findAll();
    }


    @Override
    public Set<Integer> listCustomerReservationIds (Customer customer) {

        List<Reservation> reservations = customer.getReservations();

        return reservations.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public Reservation addReservation(Integer id, Reservation reservation) {
        Customer customer = customerDao.findById(id);

        customer.addReservation(reservation);
        customerDao.saveOrUpdate(customer);

        return customer.getReservations().get(customer.getReservations().size() - 1);
    }

    @Transactional
    @Override
    public void deleteReservation(Integer id, Integer rid) {
        Customer customer = customerDao.findById(id);
        Reservation reservation = reservationDao.findById(rid);

        customer.removeReservation(reservation);
        customerDao.saveOrUpdate(customer);
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
