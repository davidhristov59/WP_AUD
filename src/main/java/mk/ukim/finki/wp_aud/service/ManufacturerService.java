package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
}
