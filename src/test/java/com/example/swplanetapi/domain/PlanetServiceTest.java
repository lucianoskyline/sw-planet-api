package com.example.swplanetapi.domain;

import com.example.swplanetapi.common.PlanetConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;

    @Test
    public void createPlanet_withValidData_returnPlanet(){
        Planet sutPlanet = planetService.create(PlanetConstants.PLANET);
        Assertions.assertThat(sutPlanet).isEqualTo(PlanetConstants.PLANET);
    }

}
