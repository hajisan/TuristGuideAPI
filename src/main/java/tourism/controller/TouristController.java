package tourism.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Tillader anmodninger fra alle domæner
@RestController
@RequestMapping("attractions")
public class  TouristController {
    private final TouristService touristService;

    // Constructor injection af service-laget
    public TouristController(TouristService touristService) {

        this.touristService = touristService;
    }

    // Hent alle attraktioner - (GET /attractions)
    @GetMapping
    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        return ResponseEntity.ok(attractions);
    }

    // Hent én attraktion via navn - (GET /attractions/{name})
    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        Optional<TouristAttraction> attraction = touristService.getAttractionByName(name);
        return attraction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tilføj en ny attraktion - (POST /attractions/add)
    @PostMapping("/add")
    public ResponseEntity<String> addAttraction(@RequestBody TouristAttraction attraction) {
        boolean added = touristService.addAttraction(attraction);
        if (added) {
            return ResponseEntity.ok("Attraktion tilføjet.");
        } else {
            return ResponseEntity.badRequest().body("Attraktionen kunne ikke tilføjes (måske findes den allerede eller har ugyldige data).");
        }
    }

    // Opdater en attraktion - (POST /attractions/update)
    @PostMapping("/update")
    public ResponseEntity<String> updateAttraction(@RequestParam String name, @RequestParam String newDescription) {
        String response = touristService.updateAttraction(name, newDescription);
        return ResponseEntity.ok(response);
    }

    // Slet en attraktion - (POST /attractions/delete/{name})
    @PostMapping("/delete/{name}")
    public ResponseEntity<String> deleteAttraction(@PathVariable String name) {
        String response = touristService.deleteAttraction(name);
        return ResponseEntity.ok(response);
    }
}