/*
 *  Copyright 2012, 2012 Fabrice DUNAN
 *
 * This file is part of loupArdent.jar.

    loupArdent.jar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>
 *
 */
package fr.fdunan.jx.ldvelh.loupArdent;

import fr.fdunan.jx.commun.ACombat;
import fr.fdunan.jx.commun.Des;

public class Combat extends ACombat
    {
    private int _seuilReussitePremier;

    private int _seuilReussiteSecond;

    private int _limiteAssautPremier;

    private int _limiteAssautSecond;

    private int _jetToucherPremier;

    private int _jetToucherSecond;

    private int _assautsAveugleSecond;

    private int _assautsAveuglePremier;

    public Combat( Personnage p1, Personnage p2 )
        {
        super(p1, p2);
        int initiativeP1 = p1.get_baseInit() + Des.lance2d6();
        int initiativeP2 = p2.get_baseInit() + Des.lance2d6();
        // TODO gerer cas limite egalite
        if( initiativeP1 < initiativeP2 )
            {
            setPremier( p2 );
            setSecond( p1 );
            }
        else
            {
            setPremier(p1);
            setSecond( p2 );
            }
        _seuilReussitePremier = 7 - getPremier().get_bonusToucher();
        _seuilReussiteSecond = 7 - getSecond().get_bonusToucher();
        _limiteAssautPremier = getPremier().get_limiteAssaut();
        _limiteAssautSecond = getSecond().get_limiteAssaut();
        setVictorieux( null );
        }

    private boolean peutAttaquer( Personnage attaquant,
                    int limiteAssautCourantAtaquant )
        {
        if( limiteAssautCourantAtaquant <= 0 )
            {
            System.out.println( attaquant.get_nom() + " fatigué !" );
            return( false);
            }
        return( true);
        }

    public void lanceAssauts()
        {
        int numeroAssaut = 1;
        while (getPremier().get_pdv_courant() >= 0
                        || getSecond().get_pdv_courant() >= 0)
            {
            System.out.println("*********************************************");
            System.out.println( "Assaut <" + numeroAssaut + ">" );
            System.out.println("*********************************************");
            if( peutAttaquer( getPremier(), _limiteAssautPremier )
                            && testCecite( getPremier(), _assautsAveuglePremier ) )
                {
                _jetToucherPremier = Des.lance2d6();
                // l'arme peut elle aveugler ?
                if( numeroAssaut == 1
                                && getPremier().get_arme() == Armes.exterminator_eclairante
                                && _jetToucherPremier < getPremier().get_habilete() )
                    _assautsAveugleSecond = 3;
                // l'arme coute t elle des pdv à "premier"?
                int malusPdvParAssautPremier = getPremier().get_arme()
                                                       .get_malusPdvParAssaut();
                if( malusPdvParAssautPremier > 0 )
                    {
                    getPremier().restaurePdvCourantsParIncrement( -malusPdvParAssautPremier );
                    System.out.println( getPremier().get_arme() + " absorbe a <"
                                    + getPremier().get_nom() + "> <"
                                    + malusPdvParAssautPremier + "> reste <"
                                    + getPremier().get_pdv_courant() + ">" );
                    if( estMort( getPremier(), getSecond() ) )
                        break;
                    }
                // attaque "premier" proprement dite
                if( _jetToucherPremier >= _seuilReussitePremier )
                    {
                    calculeBlessure( _jetToucherPremier - _seuilReussitePremier,
                                     getPremier(),
                                     getSecond() );
                    if( estMort( getSecond(), getPremier() ) )
                        break;
                    }
                else
                    System.out.println( getPremier().get_nom() + " rate !" );
                }
            else
                {
                if( _limiteAssautPremier == -1 )
                    _limiteAssautPremier = getPremier().get_limiteAssaut();
                if( _assautsAveuglePremier > 0 )
                    _assautsAveuglePremier--;
                }
            System.out.println("---------------------------------------------");
            if( peutAttaquer( getSecond(), _limiteAssautSecond )
                            && testCecite( getSecond(), _assautsAveugleSecond ) )
                {
                _jetToucherSecond = Des.lance2d6();
                // l'arme peut elle aveugler ?
                if( numeroAssaut == 1
                                && getSecond().get_arme() == Armes.exterminator_eclairante
                                && _jetToucherSecond < getSecond().get_habilete() )
                    _assautsAveuglePremier = 3;
                // l'arme coute t elle des pdv a second ?
                int malusPdvParAssautSecond = getSecond().get_arme()
                                                     .get_malusPdvParAssaut();
                if( malusPdvParAssautSecond > 0 )
                    {
                    getSecond().restaurePdvCourantsParIncrement( -malusPdvParAssautSecond );
                    System.out.println( getSecond().get_arme() + " absorbe a <"
                                    + getSecond().get_nom() + "> <"
                                    + malusPdvParAssautSecond + "> reste <"
                                    + getSecond().get_pdv_courant() + ">" );
                    if( estMort( getSecond(), getPremier() ) )
                        break;
                    }
                // attaque "second" proprement dite
                if( _jetToucherSecond >= _seuilReussiteSecond )
                    {
                    calculeBlessure( _jetToucherSecond - _seuilReussiteSecond,
                                     getSecond(),
                                     getPremier() );
                    if( estMort( getPremier(), getSecond() ) )
                        break;
                    }
                else
                    System.out.println( getSecond().get_nom() + " rate !" );
                }
            else
                {
                if( _limiteAssautSecond == -1 )
                    _limiteAssautSecond = getSecond().get_limiteAssaut();
                if( _assautsAveugleSecond > 0 )
                    _assautsAveugleSecond--;
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
            _limiteAssautPremier--;
            _limiteAssautSecond--;
            }
        }

    private boolean testCecite( Personnage p, int nbAssautCecite )
        {
        if( nbAssautCecite > 0 )
            {
            System.out.println( p.get_nom() + " aveuglé !" );
            return false;
            }
        return( true);
        }

    private void calculeBlessure( int marge, Personnage attaquant,
                    Personnage defenseur )
        {
        int blessure = marge * 10;
        blessure += attaquant.get_bonusDegats();
        blessure -= defenseur.get_bonusProtection();
        defenseur.set_pdv_courant( defenseur.get_pdv_courant() - blessure );
        System.out.println( defenseur.get_nom() + " touché ! perd <" + blessure
                        + "> reste <" + defenseur.get_pdv_courant() + ">" );
        if( blessure >= 0 )
            {
            if( attaquant.get_arme().isExterminator() )
                {
                attaquant.restaurePdvCourantsParIncrement( blessure );
                System.out.println( "Exterminator restaure <" + blessure
                                + "> a <" + attaquant.get_nom() + "> reste <"
                                + attaquant.get_pdv_courant() + ">" );
                }
            }
        }

    private boolean estMort( Personnage victime, Personnage assaillant )
        {
        if( victime.get_pdv_courant() <= 0 )
            {
            System.out.println( victime.get_nom() + " mort !" );
            System.out.println( "Pdv de " + assaillant.get_nom()
                            + " restants = <" + assaillant.get_pdv_courant()
                            + ">" );
            setVictorieux( assaillant );
            System.out.println( "Combat fini !" );
            return( true);
            }
        return( false);
        }

    public void recompenseVictorieux()
        {
        Personnage victorieux = getVictorieux();
        int habilete = victorieux.get_habilete();
        victorieux.set_habilete( habilete++ );
        }

    @Override
    public Personnage getPremier()
        {
        return (Personnage) super.getPremier();
        }

    @Override
    public Personnage getSecond()
        {
        return (Personnage) super.getSecond();
        }

    @Override
    public Personnage getVictorieux()
        {
        return (Personnage) super.getVictorieux();
        }
    }
