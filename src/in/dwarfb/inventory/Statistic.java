package in.dwarfb.inventory;

/**
 * The Statistic class represents a single statistic with a name and a value.
 */
public class Statistic {
    private String name;
    private Object value;

    /**
     * Statistic with the given name and value.
     * @param name  The name of the statistic.
     * @param value The value of the statistic.
     */
    public Statistic(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public Object getValue() { return value; }

    public String toString() {
        return name + ": " + value;
    }
}
