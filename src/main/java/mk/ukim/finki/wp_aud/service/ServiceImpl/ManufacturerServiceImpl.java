package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final InMemoryManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository) {
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
}
