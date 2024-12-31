abstract class Person {
    protected String name;
    protected String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract void displayDetails();


}
