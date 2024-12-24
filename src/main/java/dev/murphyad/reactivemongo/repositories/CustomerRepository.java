package dev.murphyad.reactivemongo.repositories;

import dev.murphyad.reactivemongo.domain.Beer;
import dev.murphyad.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Mono<Customer> findFirstByCustomerName(String customerName);
}
