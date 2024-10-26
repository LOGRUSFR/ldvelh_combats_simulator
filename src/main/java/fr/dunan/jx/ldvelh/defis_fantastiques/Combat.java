package fr.dunan.jx.ldvelh.defis_fantastiques;

import fr.dunan.jx.commun.ACombat;
import fr.dunan.jx.commun.Des;

import java.text.MessageFormat;

public class Combat extends ACombat {
    private Personnage _p1;
    private Personnage _p2;

    public static final int DEFAULT_PAUSE_TIME_BETWEEN_ASSAULT_MS=2500;

    public Combat(Personnage p1, Personnage p2) {
        super();
        setP1(p1);
        setP2(p2);
    }

    private void setP2(Personnage p2) {
        _p2 = p2;
    }

    private void setP1(Personnage p1) {
        _p1 = p1;
    }

    public void lanceAssauts() {
        lanceAssauts(DEFAULT_PAUSE_TIME_BETWEEN_ASSAULT_MS);
    }

    public void lanceAssauts(int pauseTime) {
        int numeroAssaut = 1;
        while (_p1.getEnduranceCourante() >= 0
                || _p2.getEnduranceCourante() >= 0) {
            System.out.println(String.valueOf('*').repeat(60));
            System.out.println("Assaut <" + numeroAssaut + ">");
            System.out.println(String.valueOf('-').repeat(60));
            int jetToucherP1 = Des.lance2d6() + _p1.getHabileteCourante();
            int jetToucherP2 = Des.lance2d6() + _p2.getHabileteCourante();
            System.out.println(MessageFormat.format("Force d''attaque 1 <{0}>,  Force d''attaque 2 <{1}>",jetToucherP1, jetToucherP2));
            if (jetToucherP1 > jetToucherP2) {
                this.calculeBlessure(_p2);
                //TODO serialiser la perte de PE
                if (_p2.estMort())
                    break;
            } else if (jetToucherP1 < jetToucherP2) {
                this.calculeBlessure(_p1);
                if (_p1.estMort())
                    break;
            } else {
                System.out
                        .println("Tous les protagonistes ont manqué leur but");
            }
            try {
                Thread.sleep(pauseTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numeroAssaut++;
        }
    }

    private void calculeBlessure(Personnage p) {
        //TODO chance
        int blessure = 2;
        p.setEnduranceCourante(p.getEnduranceCourante() - blessure);
        System.out.println(p.getNom() + " touché ! perd <" + blessure
                + "> reste <" + p.getEnduranceCourante() + ">");
    }
}