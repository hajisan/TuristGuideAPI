package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/* CRUD
- Create    -> Opret ny data
- Read      -> Læs data (hent information)
- Update    -> Opdater eksisterende data
- Delete    -> Slet data

CRUD-modellen bruges typisk i softwareudvikling til at definere de funktioner,
en database eller et API bør have.
 */

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    // Konstruktør med hardkodede attraktioner - (CREATE)
    public TouristRepository() {
        attractions.add(new TouristAttraction("Glyptoteket", "Et kunstmuseum i København."));
        attractions.add(new TouristAttraction("Christiansborg Slot", "Historisk slot og regeringsæde."));
        attractions.add(new TouristAttraction("Frilandsmuseet", "Et åbent museum med gamle danske bygninger."));
        attractions.add(new TouristAttraction("Botanisk Have", "En smuk have med sjældne planter."));
        attractions.add(new TouristAttraction("Marmorkirken", "Imponerende kirke med kuppel i København"));
    }

    // Læs (Hent alle attraktioner) - (READ) - (Getter)
    public List<TouristAttraction> getAllAttractions() {
        return new ArrayList<>(attractions);
    }

    // Læs (Hent én attraktion ved navn) - (READ) - (Getter)
    public TouristAttraction getAttractionByName(String name) {
        if (name == null || name.isBlank()) {
            return null; // Returnér null, hvis navnet er null eller tomt
        }

        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null; // Returnér null, hvis attraktionen ikke findes
    }

    // Opret (Tilføj en ny attraktion) - (CREATE)
    public boolean addAttraction(TouristAttraction attraction) {
        if (attraction == null || attraction.getName() == null || attraction.getName().trim().isEmpty()) {
            return false; // Afvis attraktioner uden navn
        }

        for (TouristAttraction existingAttraction : attractions) {
            if (existingAttraction.getName().equalsIgnoreCase(attraction.getName())) {
                return false; // Afvis duplikater
            }
        }

        attractions.add(attraction);
        return true; // Tilføjet succesfuldt
    }


    // Opdater (Rediger en eksisterende attraktion) - (UPDATE)
    public String updateAttraction(String name, String newDescription) {
        if (name == null || name.trim().isEmpty() || newDescription == null || newDescription.trim().isEmpty()) {
            return "Ugyldige værdier. Opdatering mislykkedes.";
        }

        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setDescription((newDescription));
                return "Attraktion opdateret.";
            }
        }
        return "Attraktion ikke fundet";
    }

    // Slet (Fjern en attraktion) - (DELETE)
    public String deleteAttraction(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Ugyldigt navn. Sletning mislykkedes.";
        }

        boolean removed = attractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));

        return removed ? "Attraktion slettet." : "Attraktion ikke fundet.";
    }
}
