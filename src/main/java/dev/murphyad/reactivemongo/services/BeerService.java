package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Mono;

public interface BeerService {

    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO);
    Mono<BeerDTO> getBeerById(BeerDTO beerDTO);
}
