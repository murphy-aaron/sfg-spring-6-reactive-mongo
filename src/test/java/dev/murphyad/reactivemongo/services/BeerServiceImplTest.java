package dev.murphyad.reactivemongo.services;

import dev.murphyad.reactivemongo.domain.Beer;
import dev.murphyad.reactivemongo.mappers.BeerMapper;
import dev.murphyad.reactivemongo.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDTO(getTestBeer());
    }

    @Test
    void testSaveBeer() {

        AtomicBoolean bool = new AtomicBoolean(false);

        Mono<BeerDTO> dto = beerService.saveBeer(Mono.just(beerDTO));

        dto.subscribe(savedDto -> {
            System.out.println(savedDto.getId());
            bool.set(true);
        });

        await().untilTrue(bool);
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("Test Beer")
                .beerStyle("Test Style")
                .upc("12345")
                .price(new BigDecimal("8.99"))
                .quantityOnHand(12)
                .build();
    }
}