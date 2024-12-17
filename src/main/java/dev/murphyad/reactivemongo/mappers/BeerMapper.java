package dev.murphyad.reactivemongo.mappers;

import dev.murphyad.reactivemongo.domain.Beer;
import dev.murphyad.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDTO beerToBeerDTO(Beer beer);
    Beer beerDTOToBeer(BeerDTO beerDTO);
}
