package dev.murphyad.reactivemongo.repositories;

import dev.murphyad.reactivemongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
