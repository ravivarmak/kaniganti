package ravi.varma.repository;

import org.springframework.stereotype.Repository;

import ravi.varma.model.Customer;

@Repository
public interface CustomerRepository extends org.springframework.data.mongodb.repository.MongoRepository<Customer, Long> {

}
