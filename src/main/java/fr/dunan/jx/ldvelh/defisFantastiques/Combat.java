package fr.dunan.jx.ldvelh.defisFantastiques;

import fr.dunan.jx.commun.ACombat;
import fr.dunan.jx.commun.Des;
import fr.dunan.jx.ldvelh.defisFantastiques.Personnage;

public class Combat extends ACombat
    {
    private Personnage _p1;
    private Personnage _p2;

    public Combat(Personnage p1, Personnage p2)
        {
        super(p1, p2);
        setP1(p1);
        setP2(p2);
        }

    private void setP2(Personnage p2)
        {
        _p2 = p2;
        }

    private void setP1(Personnage p1)
        {
        _p1 = p1;
        }

    public void lanceAssauts()
        {
        int numeroAssaut = 1;
        while (_p1.get_enduranceCourante() >= 0
                || _p2.get_enduranceCourante() >= 0)
            {
            System.out.println("*********************************************");
            System.out.println("Assaut <" + numeroAssaut + ">");
            System.out.println("*********************************************");
            int jetToucherP1 = Des.lance2d6() + _p1.get_habileteCourante();
            int jetToucherP2 = Des.lance2d6() + _p2.get_habileteCourante();
            if (jetToucherP1 > jetToucherP2)
                {
                this.calculeBlessure(_p2);
                if (_p2.estMort())
                    break;
                }
            else
                if (jetToucherP1 < jetToucherP2)
                    {
                    this.calculeBlessure(_p1);
                    if (_p1.estMort())
                        break;
                    }
                else
                    {
                    System.out
                            .println("Tous les protagonistes ont manqué leur but");
                    }
            try
                {
                Thread.sleep( 2500 );
                }
            catch( InterruptedException e )
                {
                e.printStackTrace();
                }
            numeroAssaut++;
            }
        }

    private void calculeBlessure( Personnage p )
        {
        //TODO chance
        int blessure = 2;
        p.set_enduranceCourante(p.get_enduranceCourante() - blessure);
        System.out.println( p.getNom() + " touché ! perd <" + blessure
                + "> reste <" + p.get_enduranceCourante() + ">" );
    }
}