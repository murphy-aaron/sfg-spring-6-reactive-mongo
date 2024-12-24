package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.domain.Customer;
import dev.murphyad.reactivemongo.mappers.CustomerMapperImpl;
import dev.murphyad.reactivemongo.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Testcontainers
@SpringBootTest
public class CustomerServiceImplTest {

    @Container
    @ServiceConnection
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    CustomerService customerService;

    public CustomerDTO getSavedBeerDto(){
        return customerService.saveCustomer(Mono.just(getTestCustomerDto())).block();
    }

    public static CustomerDTO getTestCustomerDto(){
        return new CustomerMapperImpl().customerToCustomerDTO(getTestCustomer());
    }

    public static Customer getTestCustomer() {
        return Customer.builder()
                .customerName("Test")
                .build();
    }
}
