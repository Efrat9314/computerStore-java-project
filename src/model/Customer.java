package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private long id;
    private String name;
    private String addres;

    public Customer(long id, String name, String addres) {
        this.id = id;
        this.name = name;
        this.addres = addres;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                '}';
    }
}
