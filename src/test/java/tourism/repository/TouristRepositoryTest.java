package tourism.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourism.model.TouristAttraction;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TouristRepositoryTest {

    @Test
    public void testAddAttraction_SuccessfulAddition() {
        // Arrange - Opretter et nyt repository og en ny attraktion
        TouristRepository repository = new TouristRepository();
        TouristAttraction newAttraction = new TouristAttraction("Tivoli", "En berømt forlystelsespark i København.");

        // Act - Forsøger at tilføje den nye attraktion til repository
        boolean result = repository.addAttraction(newAttraction);

        // Assert - Bekræfter at attraktionen blev tilføjet korrekt
        assertTrue(result, "Attraktionen bør blive tilføjet succesfuldt.");
        assertEquals(6, repository.getAllAttractions().size(), "Repository bør indeholde 6 attraktioner.");
        assertTrue(repository.getAllAttractions().contains(newAttraction), "Den nye attraktion bør være i repository.");
    }

    @Test
    public void testAddAttraction_DuplicateName() {
        // Arrange - Opretter et nyt repository og en attraktion med et duplikatnavn
        TouristRepository repository = new TouristRepository();
        TouristAttraction duplicateAttraction = new TouristAttraction("Glyptoteket", "En opdateret beskrivelse for Glyptoteket.");

        // Act - Forsøger at tilføje den duplikerede attraktion til repository
        boolean result = repository.addAttraction(duplicateAttraction);

        // Assert - Bekræfter at duplikat attraktionen ikke blev tilføjet
        assertFalse(result, "Den duplikerede attraktion bør ikke blive tilføjet.");
        assertEquals(5, repository.getAllAttractions().size(), "Repository bør stadig indeholde 5 attraktioner.");
    }

    @Test
    public void testAddAttraction_EmptyName() {
        // Arrange - Opretter et nyt repository og en attraktion med et tomt navn
        TouristRepository repository = new TouristRepository();
        TouristAttraction invalidAttraction = new TouristAttraction("", "En attraktion uden navn.");

        // Act - Forsøger at tilføje den ugyldige attraktion til repository
        boolean result = repository.addAttraction(invalidAttraction);

        // Assert - Bekræfter at attraktionen med tomt navn blev tilføjet
        assertTrue(result, "Attraktion med tomt navn bør stadig blive tilføjet (ingen begrænsning håndhævet).");
        assertEquals(6, repository.getAllAttractions().size(), "Repository bør indeholde 6 attraktioner.");
        assertTrue(repository.getAllAttractions().contains(invalidAttraction), "Den ugyldige attraktion bør stadig være i repository.");
    }

    @Test
    public void testAddAttraction_NullName() {
        // Arrange - Opretter et nyt repository og en attraktion med null navn
        TouristRepository repository = new TouristRepository();
        TouristAttraction invalidAttraction = new TouristAttraction(null, "En attraktion med null navn.");

        // Act - Forsøger at tilføje den ugyldige attraktion til repository
        boolean result = repository.addAttraction(invalidAttraction);

        // Assert - Bekræfter at attraktionen med null navn blev tilføjet
        assertTrue(result, "Attraktion med null navn bør stadig blive tilføjet (ingen begrænsning håndhævet).");
        assertEquals(6, repository.getAllAttractions().size(), "Repository bør indeholde 6 attraktioner.");
        assertTrue(repository.getAllAttractions().contains(invalidAttraction), "Den ugyldige attraktion bør stadig være i repository.");
    }
}