package org.example;

import java.util.logging.*;

public class Panel extends RealEstate implements PanelInterface {
    private int floor;
    private boolean isInsulated;

    private static final Logger logger = LoggerConfig.getLogger();

    public Panel() {
        super();
    }

    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        logger.info("Creating Panel in " + city + " on floor " + floor);
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
        logger.info("Calculating total price for Panel in " + getCity());
        double basePrice = getPrice() * getSqm();
        double modifier = 1.0;

        if (floor >= 0 && floor <= 2) {
            modifier += 0.05;
        } else if (floor == 10) {
            modifier -= 0.05;
        }

        if (isInsulated) {
            modifier += 0.05;
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
        logger.info("Comparing total price with another property");
        return this.getTotalPrice() == other.getTotalPrice();
    }

    @Override
    public int roomprice() {
        logger.info("Calculating average room price for Panel in " + getCity());
        if (getNumberOfRooms() == 0) {
            return 0;
        }
        return (int) Math.round((getPrice() * getSqm()) / getNumberOfRooms());
    }
}
