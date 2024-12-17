package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.mappers.BeerMapper;
import dev.murphyad.reactivemongo.model.BeerDTO;
import dev.murphyad.reactivemongo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO) {
        return beerDTO.map(beerMapper::beerDTOToBeer)
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> getBeerById(BeerDTO beerDTO) {
        return null;
    }
}
