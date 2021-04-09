package org.academiadecodigo.rememberthename.service.mock;

import org.academiadecodigo.rememberthename.persistence.model.AbstractModel;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.academiadecodigo.rememberthename.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceMockImpl extends AbstractMockService<Customer> implements CustomerService {

    private ReservationServiceMockImpl reservationService;
    private CustomerServiceMockImpl customerService;

    public CustomerServiceMockImpl(){
        super();
        Customer customer = new Customer();
        customer.setFirstName("Rodrigo");
        customer.setLastName("Dinis");
        customer.setId(1);
        customer.setPhone("96634812");
        customer.setEmail("rodrigodinis@gmail.com");
        customer.setDate(new Date());
        customer.setNotes("Vegetarian");
        customer.setNumberOfCustomer(4);
        modelMap.put(customer.getId(), customer);


    }

    public void setReservationService(ReservationServiceMockImpl reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Customer get(Integer id) {
        return modelMap.get(id);
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        modelMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void delete(Integer id) {

        modelMap.remove(id);
    }

    @Override
    public List<Customer> list() {
        return new ArrayList<>(modelMap.values());
    }

    @Override
    public Set<Integer> listCustomerReservationIds(Customer customer) {
        List<Reservation> accounts = customer.getReservations();

        return accounts.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());

    }

    @Override
    public Reservation addReservation(Integer id, Reservation reservation) {
        Customer customer = get(id);
        customer.addReservation(reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Integer cid, Integer rid) {
        Customer customer = modelMap.get(cid);

        customer.getReservations().stream()
                .filter(reservation -> reservation.getId().equals(rid))
                .findFirst()
                .ifPresent(customer::removeReservation);
    }
}
