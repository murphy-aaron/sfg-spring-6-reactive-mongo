package dev.murphyad.reactivemongo.webfn;

import dev.murphyad.reactivemongo.model.CustomerDTO;
import dev.murphyad.reactivemongo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static dev.murphyad.reactivemongo.webfn.CustomerRouterConfig.CUSTOMER_PATH_ID;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerService customerService;
    private final Validator validator;

    private void validate(CustomerDTO dto) {
        Errors errors = new BeanPropertyBindingResult(dto, "customerDto");
        validator.validate(dto, errors);

        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> listCustomers(ServerRequest request) {
        return ServerResponse.ok()
                .body(customerService.listCustomers(), CustomerDTO.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        return ServerResponse.ok()
                .body(customerService.getById(request.pathVariable("customerId"))
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))), CustomerDTO.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        return customerService.saveCustomer(request.bodyToMono(CustomerDTO.class).doOnNext(this::validate))
                .flatMap(customerDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath(CUSTOMER_PATH_ID)
                                .build(customerDTO.getId()))
                        .build());
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerDTO -> customerService.updateCustomer(request.pathVariable("customerId"), customerDTO)
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                        .flatMap(savedDTO -> ServerResponse
                                .noContent()
                                .build()));
    }

    public Mono<ServerResponse> patchCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerDTO.class)
                .doOnNext(this::validate)
                .flatMap(customerDTO -> customerService.patchCustomer(request.pathVariable("customerId"), customerDTO)
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                        .flatMap(savedDTO -> ServerResponse
                                .noContent()
                                .build()));
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        return customerService.getById(request.pathVariable("customerId"))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(customerDTO -> customerService.deleteCustomer(customerDTO.getId())
                        .then(ServerResponse.noContent().build()));
    }
}
