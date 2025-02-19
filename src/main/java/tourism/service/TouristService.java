package tourism.service;

import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    // Constructor injection
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    // Læs (Hent alle attraktioner) - (READ)
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    // Læs (Hent én attraktion ved navn) - (READ)
    public Optional<TouristAttraction> getAttractionByName(String name) {
        return Optional.ofNullable(touristRepository.getAttractionByName(name));
    }

    // Opret (Tilføj en ny attraktion) - (CREATE)
    public boolean addAttraction(TouristAttraction attraction) {
        return touristRepository.addAttraction(attraction);
    }

    // Opdater (Rediger en eksisterende attraktion) - (UPDATE)
    public String updateAttraction(String name, String newDescription) {
        return touristRepository.updateAttraction(name, newDescription);
    }

    // Slet (Fjern en attraktion) - (DELETE)
    public String deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }
}