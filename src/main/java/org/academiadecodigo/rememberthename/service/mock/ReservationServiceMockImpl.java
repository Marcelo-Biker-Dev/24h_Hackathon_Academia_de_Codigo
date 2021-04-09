package org.academiadecodigo.rememberthename.service.mock;

import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.academiadecodigo.rememberthename.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceMockImpl extends AbstractMockService<Reservation> implements ReservationService {

    private CustomerServiceMockImpl customerService;



    public void setCustomerService(CustomerServiceMockImpl customerService) {
        this.customerService = customerService;
    }


    @Override
    public Reservation get(Integer id) {
        return modelMap.get(id);
    }
}
