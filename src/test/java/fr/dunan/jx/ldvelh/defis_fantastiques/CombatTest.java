package fr.dunan.jx.ldvelh.defis_fantastiques;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CombatTest {

    @Test
    public void testLanceAssautsFirstCharacterWins() {
        Personnage p1 = new Personnage();
        p1.setHabileteCourante(20);
        p1.setEnduranceCourante(15);

        Personnage p2 = new Personnage();
        p2.setHabileteCourante(10);
        p2.setEnduranceCourante(10);

        Combat combat = new Combat(p1, p2);
        combat.lanceAssauts();

        Assertions.assertTrue(p2.estMort());
    }

    @Test
    public void testLanceAssautsSecondCharacterWins() {
        Personnage p1 = new Personnage();
        p1.setHabileteCourante(10);
        p1.setEnduranceCourante(10);

        Personnage p2 = new Personnage();
        p2.setHabileteCourante(20);
        p2.setEnduranceCourante(15);

        Combat combat = new Combat(p1, p2);
        combat.lanceAssauts(0);

        Assertions.assertTrue(p1.estMort());
    }

    @Test
    public void testLanceAssautsSameHabilities() {
        Personnage p1 = new Personnage();
        p1.setHabileteCourante(10);
        p1.setEnduranceCourante(10);

        Personnage p2 = new Personnage();
        p2.setHabileteCourante(10);
        p2.setEnduranceCourante(10);

        Combat combat = new Combat(p1, p2);
        combat.lanceAssauts(0);

        Assertions.assertTrue(p1.estMort() || p2.estMort());
    }
}