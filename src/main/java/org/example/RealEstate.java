package org.example;

import java.util.logging.*;

/*
city – the property is located in this city (textual)
price – the price of one square meter of the property (real)
sqm – the value of the property area in square meters (integer)
numberOfRooms – the number of rooms in the property (real)
genre – Type Genre
Genre – static enumerated type with the following options: FAMILYHOUSE, CONDOMINIUM,
FARM (enum) -- added FLAT later
 */

public class RealEstate implements PropertyInterface{
    String city;
    double price;
    int sqm;
    double numberOfRooms;
    Genre genre;

    private static final Logger logger = LoggerConfig.getLogger();

    public RealEstate() {
    }
    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        logger.info("Creating RealEstate object in " + city);
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSqm() {
        return sqm;
    }
    public void setSqm(int sqm) {
        this.sqm = sqm;
    }
    public double getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void makeDiscount(int percent) {
        logger.info("Applying discount of " + percent + "% to property in " + city);
        double discount = price * (percent / 100.0);
        price -= discount;
    }

    @Override
    public int getTotalPrice() {
        logger.info("Calculating total price for property in " + city);
        double basePrice = price * sqm;
        if (city.equalsIgnoreCase("Budapest")) {
            basePrice *= 1.3;
        } else if (city.equalsIgnoreCase("Debrecen")) {
            basePrice *= 1.2;
        } else if (city.equalsIgnoreCase("Nyíregyháza")) {
            basePrice *= 1.15;
        }
        return (int) Math.round(basePrice);
    }

    @Override
    public double averageSqmPerRoom() {
        logger.info("Calculating average sqm per room for property in " + city);
        if (numberOfRooms == 0) return 0;
        return (double) sqm / numberOfRooms;
    }

    @Override
    public String toString() {
        return "RealEstate { " +
                "city='" + city + '\'' +
                ", price=" + price +
                ", sqm=" + sqm +
                ", numberOfRooms=" + numberOfRooms +
                ", genre=" + genre +
                ", totalPrice=" + getTotalPrice() +
                ", sqm/room=" + String.format("%.2f", averageSqmPerRoom()) +
                " }";
    }
}