package org.example;

public class Panel extends RealEstate implements PanelInterface {
    private int floor;
    private boolean isInsulated;

    public Panel() {
        super();
    }

    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isInsulated() {
        return isInsulated;
    }

    public void setInsulated(boolean insulated) {
        isInsulated = insulated;
    }

    @Override
    public int getTotalPrice() {
        double basePrice = getPrice() * getSqm();
        double modifier = 1.0;

        if (floor >= 0 && floor <= 2) {
            modifier += 0.05;  // +5%
        } else if (floor == 10) {
            modifier -= 0.05;  // -5%
        }

        if (isInsulated) {
            modifier += 0.05;  // +5%
        }

        return (int) Math.round(basePrice * modifier);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPanel { " +
                "floor: " + floor +
                "\nisInsulated: " + isInsulated +
                "\ntotalPrice: " + getTotalPrice() +
                " }";
    }

    @Override
    public boolean hasSameAmount(RealEstate other) {
        return this.getTotalPrice() == other.getTotalPrice();
    }

    @Override
    public int roomprice() {
        if (getNumberOfRooms() == 0) {
            return 0;
        }
        return (int) Math.round((getPrice() * getSqm()) / getNumberOfRooms());
    }
}
