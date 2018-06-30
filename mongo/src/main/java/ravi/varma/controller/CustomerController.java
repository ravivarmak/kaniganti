package ravi.varma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ravi.varma.model.Customer;
import ravi.varma.repository.CustomerRepository;


@RestController
public class CustomerController {
@Autowired
CustomerRepository repo;
	@RequestMapping(value="/customers")
	public List<Customer> getCustomers(){
		
	return repo.findAll();
	}
	@RequestMapping(value="/customers",method = RequestMethod.POST)
	public String postCustomers(@RequestBody Customer customer){
		repo.save(customer);
	return "Success";
	}
	
}
