package model;

import java.io.Serializable;

public class SoftWareProduct extends Product implements Serializable {
    private  int numOfUsers;

    public SoftWareProduct(long id, String name, String description, float pricePerUnit, int numOfUsers) {
        super(id, name, description, pricePerUnit);
        this.numOfUsers = numOfUsers;
    }

    public int getNumOfUsers() {
        return numOfUsers;
    }

    public void setNumOfUsers(int numOfUsers) {
        this.numOfUsers = numOfUsers;
    }

    @Override
    public float getPrice() {
        return this.numOfUsers+getPricePerUnit();
    }
}
