package dev.murphyad.reactivemongo.mappers;

import dev.murphyad.reactivemongo.domain.Customer;
import dev.murphyad.reactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
