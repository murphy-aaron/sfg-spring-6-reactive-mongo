package dev.murphyad.reactivemongo.webfn;

import com.mongodb.internal.connection.Server;
import dev.murphyad.reactivemongo.model.BeerDTO;
import dev.murphyad.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BeerHandler {

    private final BeerService beerService;

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }

    public Mono<ServerResponse> getBeer(ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.getById(request.pathVariable("beerId")), BeerDTO.class);
    }

    public Mono<ServerResponse> saveBeer(ServerRequest request) {
        return beerService.saveBeer(request.bodyToMono(BeerDTO.class))
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath(BeerRouterConfig.BEER_PATH_ID)
                                .build(beerDTO.getId()))
                .build());
    }

    public Mono<ServerResponse> updateBeer(ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .flatMap(beerDTO -> beerService.updateBeer(beerDTO.getId(), beerDTO)
                        .flatMap(savedDTO -> ServerResponse
                                .noContent()
                                .build()));
    }

    public Mono<ServerResponse> patchBeer(ServerRequest request) {
        return request.bodyToMono(BeerDTO.class)
                .flatMap(beerDTO -> beerService.patchBeer(beerDTO.getId(), beerDTO)
                        .flatMap(savedDTO -> ServerResponse
                                .noContent()
                                .build()));
    }

    public Mono<ServerResponse> deleteBeer(ServerRequest request) {
        return beerService.deleteBeer(request.pathVariable("beerId"))
                .then(ServerResponse.noContent().build());
    }
}
