package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<CustomerDTO> findFirstByCustomerName(String customerName);
    Flux<CustomerDTO> listCustomers();
    Mono<CustomerDTO> getById(String customerId);
    Mono<CustomerDTO> saveCustomer(Mono<CustomerDTO> customerDto);
    Mono<CustomerDTO> saveCustomer(CustomerDTO customerDTO);
    Mono<CustomerDTO> updateCustomer(String customerId, CustomerDTO customerDTO);
    Mono<CustomerDTO> patchCustomer(String customerId, CustomerDTO customerDTO);
    Mono<Void> deleteCustomer(String customerId);
}
