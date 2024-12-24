package dev.murphyad.reactivemongo.webfn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class CustomerRouterConfig {

    public static final String CUSTOMER_PATH = "/api/v3/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> customerRoutes() {
        return route()
                .GET(CUSTOMER_PATH, accept(APPLICATION_JSON), handler::listCustomers)
                .GET(CUSTOMER_PATH_ID, accept(APPLICATION_JSON), handler::getCustomer)
                .POST(CUSTOMER_PATH, accept(APPLICATION_JSON), handler::saveCustomer)
                .PUT(CUSTOMER_PATH_ID, accept(APPLICATION_JSON), handler::updateCustomer)
                .PATCH(CUSTOMER_PATH_ID, accept(APPLICATION_JSON), handler::patchCustomer)
                .DELETE(CUSTOMER_PATH_ID, accept(APPLICATION_JSON), handler::deleteCustomer)
                .build();
    }
}
