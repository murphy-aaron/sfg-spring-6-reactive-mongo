package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Mono<BeerDTO> findFirstByBeerName(String beerName);
    Flux<BeerDTO> findByBeerStyle(String beerStyle);
    Flux<BeerDTO> listBeers();
    Mono<BeerDTO> getById(String beerId);
    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDto);
    Mono<BeerDTO> saveBeer(BeerDTO beerDTO);
    Mono<BeerDTO> updateBeer(String beerId, BeerDTO beerDTO);
    Mono<BeerDTO> patchBeer(String beerId, BeerDTO beerDTO);
    Mono<Void> deleteBeer(String beerId);
}
