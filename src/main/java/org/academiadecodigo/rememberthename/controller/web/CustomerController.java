package org.academiadecodigo.rememberthename.controller.web;

import org.academiadecodigo.rememberthename.command.CustomerDto;
import org.academiadecodigo.rememberthename.command.ReservationDto;
import org.academiadecodigo.rememberthename.converters.CustomerDtoToCustomer;
import org.academiadecodigo.rememberthename.converters.CustomerToCustomerDto;
import org.academiadecodigo.rememberthename.converters.ReservationDtoToReservation;
import org.academiadecodigo.rememberthename.converters.ReservationToReservationDto;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.academiadecodigo.rememberthename.service.mock.CustomerServiceMockImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerServiceMockImpl customerService;


    @Autowired
    public void setCustomerService(CustomerServiceMockImpl customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "1234";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "124";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {

        Customer customer = customerService.get(id);

        // command objects for customer show view
        model.addAttribute("customer", customer);
        model.addAttribute("reservation", customer.getReservations());

        // command objects for modals
        Reservation reservation = new Reservation();

        model.addAttribute("reservation", reservation);

        return "1235";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "customer/add-update";
        }

        Customer savedCustomer = customerService.save(customer);

        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.get(id);
        customerService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + customer.getFirstName() + " " + customer.getLastName());
        return "redirect:/customer";
    }
}
