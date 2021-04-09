package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.persistence.dao.CustomerDao;
import org.academiadecodigo.rememberthename.persistence.dao.ReservationDao;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ReservationServiceImpl implements ReservationService{

    private ReservationDao reservationDao;
    private CustomerDao customerDao;

    @Override
    public Reservation get(Integer id) {
        return reservationDao.findById(id);
    }


    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
