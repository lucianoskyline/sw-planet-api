package com.example.swplanetapi.domain;

import com.example.swplanetapi.common.PlanetConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    @Test
    public void createPlanet_withInvalidData_throwsException(){
        when(planetRepository.save(PlanetConstants.INVALID_PLANET)).thenThrow(RuntimeException.class);
        Assertions.assertThatThrownBy(()->planetService.create(PlanetConstants.INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_ByExistingId_ReturnsPlanet() {
        when(planetRepository.findById(1l)).thenReturn(Optional.of(PlanetConstants.PLANET));
        Optional<Planet> sutPlanet = planetService.get(1l);
        Assertions.assertThat(sutPlanet).isNotEmpty();
        Assertions.assertThat(sutPlanet.get()).isEqualTo(PlanetConstants.PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(1l)).thenReturn(Optional.empty());
        Optional<Planet> sutPlanet = planetService.get(1l);
        Assertions.assertThat(sutPlanet).isEmpty();
    }

}
