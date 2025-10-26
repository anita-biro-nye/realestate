package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RealEstateTest {

    private RealEstate baseEstate;
    private Panel panelEstate;

    @BeforeEach
    void setup() {
        baseEstate = new RealEstate("Budapest", 200000, 100, 4, Genre.CONDOMINIUM);
        panelEstate = new Panel("Debrecen", 150000, 60, 3, Genre.CONDOMINIUM, 2, true);
    }

    @Test
    void testMakeDiscountUnderTest() {
        double oldPrice = baseEstate.getPrice();
        baseEstate.makeDiscount(15);
        double expected = oldPrice * 0.85;
        assertEquals(expected, baseEstate.getPrice(), 0.01,
                "Price should decrease by the correct percentage after discount");
    }

    @Test
    void testCityModifierUnderTest() {
        double expected = 200000 * 100 * 1.30;
        assertEquals((int) Math.round(expected), baseEstate.getTotalPrice(),
                "Total price should include the correct city modifier for Budapest");
    }

    @Test
    void testAverageSqmPerRoomUnderTest() {
        double result = baseEstate.averageSqmPerRoom();
        assertEquals(25.0, result, 0.001,
                "Average sqm per room should equal total sqm divided by number of rooms");
    }

    @Test
    void testPanelModifiersUnderTest() {
        double base = 150000 * 60;
        double withModifiers = base * 1.10; // +10% total (5% + 5% added linearly)
        int expected = (int) Math.round(withModifiers);

        assertEquals(expected, panelEstate.getTotalPrice(),
                "Panel total price should reflect +5% floor and +5% insulation (added linearly)");
    }

    @Test
    void testHasSameAmountUnderTest() {
        Panel anotherPanel = new Panel("Debrecen", 150000, 60, 3, Genre.CONDOMINIUM, 2, true);
        assertTrue(panelEstate.hasSameAmount(anotherPanel),
                "Panels with same attributes should have equal total price");
    }
}
