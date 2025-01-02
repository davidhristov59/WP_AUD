package mk.ukim.finki.wp_aud.model;

public class Manufacturer {

    private Long id;
    private String name;
    private String address;

    public Manufacturer(String name, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
