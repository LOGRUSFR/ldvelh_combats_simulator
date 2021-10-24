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
package fr.dunan.jx.ldvelh.loup_ardent;

import fr.dunan.jx.commun.ACombat;
import fr.dunan.jx.commun.Des;

public class Combat extends ACombat {
    private int seuilReussitePremier;

    private int seuilReussiteSecond;

    private int limiteAssautPremier;

    private int limiteAssautSecond;

    private int jetToucherPremier;

    private int jetToucherSecond;

    private int assautsAveugleSecond;

    private int assautsAveuglePremier;

    public Combat(Personnage p1, Personnage p2) {
        super();
        int initiativeP1 = p1.getBaseInit() + Des.lance2d6();
        int initiativeP2 = p2.getBaseInit() + Des.lance2d6();
        // TODO gerer cas limite egalite
        if (initiativeP1 < initiativeP2) {
            setPremier(p2);
            setSecond(p1);
        } else {
            setPremier(p1);
            setSecond(p2);
        }
        seuilReussitePremier = 7 - getPremier().getBonusToucher();
        seuilReussiteSecond = 7 - getSecond().getBonusToucher();
        limiteAssautPremier = getPremier().getLimiteAssaut();
        limiteAssautSecond = getSecond().getLimiteAssaut();
        setVictorieux(null);
    }

    private boolean peutAttaquer(Personnage attaquant,
                                 int limiteAssautCourantAtaquant) {
        if (limiteAssautCourantAtaquant <= 0) {
            System.out.println(attaquant.getNom() + " fatigué !");
            return (false);
        }
        return (true);
    }

    public void lanceAssauts() {
        int numeroAssaut = 1;
        while (getPremier().getPdv_courant() >= 0
                || getSecond().getPdv_courant() >= 0) {
            System.out.println("*********************************************");
            System.out.println("Assaut <" + numeroAssaut + ">");
            System.out.println("*********************************************");
            if (peutAttaquer(getPremier(), limiteAssautPremier)
                    && testCecite(getPremier(), assautsAveuglePremier)) {
                jetToucherPremier = Des.lance2d6();
                // l'arme peut elle aveugler ?
                if (numeroAssaut == 1
                        && getPremier().getArme() == Armes.EXTERMINATOR_ECLAIRANTE
                        && jetToucherPremier < getPremier().getHabilete())
                    assautsAveugleSecond = 3;
                // l'arme coute t elle des pdv à "premier"?
                int malusPdvParAssautPremier = getPremier().getArme()
                        .get_malusPdvParAssaut();
                if (malusPdvParAssautPremier > 0) {
                    getPremier().restaurePdvCourantsParIncrement(-malusPdvParAssautPremier);
                    System.out.println(getPremier().getArme() + " absorbe a <"
                            + getPremier().getNom() + "> <"
                            + malusPdvParAssautPremier + "> reste <"
                            + getPremier().getPdv_courant() + ">");
                    if (estMort(getPremier(), getSecond()))
                        break;
                }
                // attaque "premier" proprement dite
                if (jetToucherPremier >= seuilReussitePremier) {
                    calculeBlessure(jetToucherPremier - seuilReussitePremier,
                            getPremier(),
                            getSecond());
                    if (estMort(getSecond(), getPremier()))
                        break;
                } else
                    System.out.println(getPremier().getNom() + " rate !");
            } else {
                if (limiteAssautPremier == -1)
                    limiteAssautPremier = getPremier().getLimiteAssaut();
                if (assautsAveuglePremier > 0)
                    assautsAveuglePremier--;
            }
            System.out.println("---------------------------------------------");
            if (peutAttaquer(getSecond(), limiteAssautSecond)
                    && testCecite(getSecond(), assautsAveugleSecond)) {
                jetToucherSecond = Des.lance2d6();
                // l'arme peut elle aveugler ?
                if (numeroAssaut == 1
                        && getSecond().getArme() == Armes.EXTERMINATOR_ECLAIRANTE
                        && jetToucherSecond < getSecond().getHabilete())
                    assautsAveuglePremier = 3;
                // l'arme coute t elle des pdv a second ?
                int malusPdvParAssautSecond = getSecond().getArme()
                        .get_malusPdvParAssaut();
                if (malusPdvParAssautSecond > 0) {
                    getSecond().restaurePdvCourantsParIncrement(-malusPdvParAssautSecond);
                    System.out.println(getSecond().getArme() + " absorbe a <"
                            + getSecond().getNom() + "> <"
                            + malusPdvParAssautSecond + "> reste <"
                            + getSecond().getPdv_courant() + ">");
                    if (estMort(getSecond(), getPremier()))
                        break;
                }
                // attaque "second" proprement dite
                if (jetToucherSecond >= seuilReussiteSecond) {
                    calculeBlessure(jetToucherSecond - seuilReussiteSecond,
                            getSecond(),
                            getPremier());
                    if (estMort(getPremier(), getSecond()))
                        break;
                } else
                    System.out.println(getSecond().getNom() + " rate !");
            } else {
                if (limiteAssautSecond == -1)
                    limiteAssautSecond = getSecond().getLimiteAssaut();
                if (assautsAveugleSecond > 0)
                    assautsAveugleSecond--;
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numeroAssaut++;
            limiteAssautPremier--;
            limiteAssautSecond--;
        }
    }

    private boolean testCecite(Personnage p, int nbAssautCecite) {
        if (nbAssautCecite > 0) {
            System.out.println(p.getNom() + " aveuglé !");
            return false;
        }
        return (true);
    }

    private void calculeBlessure(int marge, Personnage attaquant,
                                 Personnage defenseur) {
        int blessure = marge * 10;
        blessure += attaquant.getBonusDegats();
        blessure -= defenseur.getBonusProtection();
        defenseur.setPdv_courant(defenseur.getPdv_courant() - blessure);
        System.out.println(defenseur.getNom() + " touché ! perd <" + blessure
                + "> reste <" + defenseur.getPdv_courant() + ">");
        if (blessure >= 0) {
            if (attaquant.getArme().isExterminator()) {
                attaquant.restaurePdvCourantsParIncrement(blessure);
                System.out.println("Exterminator restaure <" + blessure
                        + "> a <" + attaquant.getNom() + "> reste <"
                        + attaquant.getPdv_courant() + ">");
            }
        }
    }

    private boolean estMort(Personnage victime, Personnage assaillant) {
        if (victime.getPdv_courant() <= 0) {
            System.out.println(victime.getNom() + " mort !");
            System.out.println("Pdv de " + assaillant.getNom()
                    + " restants = <" + assaillant.getPdv_courant()
                    + ">");
            setVictorieux(assaillant);
            System.out.println("Combat fini !");
            return (true);
        }
        return (false);
    }

    public void recompenseVictorieux() {
        Personnage victorieux = getVictorieux();
        int habilete = victorieux.getHabilete();
        victorieux.setHabilete(habilete++);
    }

    @Override
    public Personnage getPremier() {
        return (Personnage) super.getPremier();
    }

    @Override
    public Personnage getSecond() {
        return (Personnage) super.getSecond();
    }

    @Override
    public Personnage getVictorieux() {
        return (Personnage) super.getVictorieux();
    }
}
