package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {

        Manufacturer manufacturer = new Manufacturer(name, address);

        return Optional.of(manufacturerRepository.save(manufacturer));
    }

    @Override
    public void deleteById(Long id) {
         manufacturerRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return manufacturerRepository.existsById(id);
    }
}
