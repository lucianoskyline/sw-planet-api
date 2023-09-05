package com.example.swplanetapi.domain;

import com.example.swplanetapi.common.PlanetConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @Test
    public void createPlanet_withValidData_returnPlanet(){
        when(planetRepository.save(PlanetConstants.PLANET)).thenReturn(PlanetConstants.PLANET);
        Planet sutPlanet = planetService.create(PlanetConstants.PLANET);
        Assertions.assertThat(sutPlanet).isEqualTo(PlanetConstants.PLANET);
    }

}
