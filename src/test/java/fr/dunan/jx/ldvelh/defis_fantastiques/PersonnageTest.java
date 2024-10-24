package fr.dunan.jx.ldvelh.defis_fantastiques;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest {
    /**
     * The PersonnageTest is a test class for the Personnage class.
     * This class with test the 'estMort' method which checks if the character is dead or not.
     * It returns true if the current endurance is less than or equal to 0, otherwise false.
     */

    @Test
    void testEstMort_WithPositiveEndurance() {
        // Arrange
        Personnage personnage = new Personnage("Test");
        personnage.setEnduranceCourante(5); // make sure the character is alive

        // Act
        boolean result = personnage.estMort();

        // Assert
        assertFalse(result, "Character should not be dead if endurance is positive");
    }

    @Test
    void testEstMort_WithEnduranceZero() {
        // Arrange
        Personnage personnage = new Personnage("Test");
        personnage.setEnduranceCourante(0); // make sure the character has zero endurance

        // Act
        boolean result = personnage.estMort();

        // Assert
        assertTrue(result, "Character should be dead if endurance is zero");
    }

    @Test
    void testEstMort_WithNegativeEndurance() {
        // Arrange
        Personnage personnage = new Personnage("Test");
        personnage.setEnduranceCourante(-5); // make sure the character has negative endurance

        // Act
        boolean result = personnage.estMort();

        // Assert
        assertTrue(result, "Character should be dead if endurance is negative");
    }
}