package tourism.model;

public class TouristAttraction {
    private String name;
    private String description;

    // Standardkonstruktør (kræves til JSON-serilisering)
    public TouristAttraction() {
    }

    // Konstruktør med parametre
    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter og Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Overriding toString() for debugging
    public String toString() {
        return "TouristAttraction{name='" + name + "', description='" + description + "'}";
    }
}
