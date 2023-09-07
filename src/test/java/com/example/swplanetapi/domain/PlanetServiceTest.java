package com.example.swplanetapi.domain;

import com.example.swplanetapi.common.PlanetConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @Test
    public void createPlanet_withValidData_returnPlanet() {
        when(planetRepository.save(PlanetConstants.PLANET)).thenReturn(PlanetConstants.PLANET);
        Planet sutPlanet = planetService.create(PlanetConstants.PLANET);
        Assertions.assertThat(sutPlanet).isEqualTo(PlanetConstants.PLANET);
    }

    @Test
    public void createPlanet_withInvalidData_throwsException() {
        when(planetRepository.save(PlanetConstants.INVALID_PLANET)).thenThrow(RuntimeException.class);
        Assertions.assertThatThrownBy(() -> planetService.create(PlanetConstants.INVALID_PLANET)).isInstanceOf(RuntimeException.class);
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

    @Test
    public void getPlanet_ByExistingName_ReturnsPlanet() {
        when(planetRepository.findByName(PlanetConstants.PLANET.getName())).thenReturn(Optional.of(PlanetConstants.PLANET));
        Optional<Planet> sutPlanet = planetService.getByName(PlanetConstants.PLANET.getName());
        Assertions.assertThat(sutPlanet).isNotEmpty();
        Assertions.assertThat(sutPlanet.get()).isEqualTo(PlanetConstants.PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingName_ReturnsEmpty() {
        when(planetRepository.findByName(PlanetConstants.PLANET.getName())).thenReturn(Optional.empty());
        Optional<Planet> sutPlanet = planetService.getByName(PlanetConstants.PLANET.getName());
        Assertions.assertThat(sutPlanet).isEmpty();
    }

    @Test
    public void listPlanets_ReturnsAllPlanets() {
        List<Planet> planets = new ArrayList<>() {
            {
                add(PlanetConstants.PLANET);
            }
        };
        Example<Planet> query = QueryBuilder.makeQuery(new Planet(PlanetConstants.PLANET.getTerrain(), PlanetConstants.PLANET.getClimate()));
        when(planetRepository.findAll(query)).thenReturn(planets);
        List<Planet> sutPlanets = planetService.list(PlanetConstants.PLANET.getTerrain(), PlanetConstants.PLANET.getClimate());

        Assertions.assertThat(sutPlanets).isNotEmpty();
        Assertions.assertThat(sutPlanets).hasSize(1);
        Assertions.assertThat(sutPlanets.get(0)).isEqualTo(PlanetConstants.PLANET);
    }

    @Test
    public void listPlanets_ReturnsNotPlanets() {
        when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());
        List<Planet> sutPlanets = planetService.list(PlanetConstants.PLANET.getTerrain(), PlanetConstants.PLANET.getClimate());
        Assertions.assertThat(sutPlanets).isEmpty();
    }

}
