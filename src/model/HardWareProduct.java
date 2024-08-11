package model;

import java.io.Serializable;

public class HardWareProduct extends Product implements Serializable {

    private int warrantyPeriod;
    enum type {HARDWARE,SOFTWARE};

    public HardWareProduct(long id, String name, String description, float pricePerUnit, int warrantyPeriod) {
        super(id, name, description, pricePerUnit);
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString()+ " model.HardWareProduct{" +
                "warrantyPeriod=" + warrantyPeriod +
                '}';
    }

    @Override
    public float getPrice() {
        return this.warrantyPeriod+getPricePerUnit();
    }
}
