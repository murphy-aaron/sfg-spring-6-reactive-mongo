package dev.murphyad.reactivemongo.repositories;

import dev.murphyad.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
