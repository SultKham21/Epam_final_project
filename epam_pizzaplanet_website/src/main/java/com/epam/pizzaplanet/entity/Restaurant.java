package com.epam.pizzaplanet.entity;

public class Restaurant {
    private long id;
    private String adressOfRestarant;

    public Restaurant(long id, String adressOfRestarant) {
        this.id = id;
        this.adressOfRestarant = adressOfRestarant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdressOfRestarant() {
        return adressOfRestarant;
    }

    public void setAdressOfRestarant(String adressOfRestarant) {
        this.adressOfRestarant = adressOfRestarant;
    }
}
