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

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlRootElement;

import fr.dunan.jx.commun.APersonnage;
import fr.dunan.jx.commun.Des;
import lombok.Getter;
import lombok.Setter;


@XmlRootElement
public class Personnage extends APersonnage {
    /**
     *
     */
    private static final long serialVersionUID = 2L;

    @Getter
    @Setter
    private int force;

    @Getter
    @Setter
    private int rapidite;

    @Getter
    @Setter
    private int endurance;

    @Getter
    @Setter
    private int courage;

    @Getter
    @Setter
    private int chance;

    @Getter
    @Setter
    private int magnetisme;

    @Getter
    @Setter
    private int seduction;

    @Getter
    @Setter
    private int habilete;

    @Getter
    @Setter
    private int pdv_initial;

    @Getter
    @Setter
    private int pdv_courant;

    @Getter
    @Setter
    private int baseInit;

    @Getter
    @Setter
    private int bonusToucher;

    @Getter
    @Setter
    private int bonusDegats;

    @Getter
    @Setter
    private int bonusProtection;

    @Getter
    @Setter
    private int limiteAssaut;

    @Getter
    @Setter
    private Armes arme = Armes.RIEN;

    @Getter
    @Setter
    private Armures armure = Armures.RIEN;

    public Personnage() {
        super();
    }

    public Personnage(String nom) {
        super(nom);
        this.force = Des.lance2d6() * 8;
        this.rapidite = Des.lance2d6() * 8;
        this.endurance = Des.lance2d6() * 8;
        this.courage = Des.lance2d6() * 8;
        this.chance = Des.lance2d6() * 8;
        this.magnetisme = Des.lance2d6() * 8;
        this.seduction = Des.lance2d6() * 8;
        this.habilete = 0;
        calculeCaracteristiquesDerivees();
        rendPdvInitiaux();
    }

    /*
     * public Personnage( String nom, int force, int rapidite, int endurance,
     * int courage, int chance, int magnetisme, int seduction, int habilete ) {
     * _nom = nom; this._force = force; this._rapidite = rapidite;
     * this._endurance = endurance; this._courage = courage; this._chance =
     * chance; this._magnetisme = magnetisme; this._seduction = seduction;
     * this._habilete = habilete; calculeCaracteristiquesSecondaires(); }
     */

    private void calculeLimiteAssaut() {
        limiteAssaut = Math.round(((Integer) endurance).floatValue() / 10);
    }

    private void calculeBaseInit() {
        baseInit = rapidite + courage + chance;
    }

    private void calculePdvInitiaux() {
        pdv_initial = force + rapidite + endurance + courage + chance
                + magnetisme + seduction + habilete;
    }

    private void calculeBonusToucher() {
        bonusToucher = chance > 71 ? 1 : 0;
        bonusToucher += habilete / 10;
    }

    private void calculeBonusDegats() {
        bonusDegats = force / 8;
        bonusDegats += arme.getBonusDegat();
    }

    private void calculeBonusProtection() {
        bonusProtection = armure.getBonusProtection();
    }

    public void calculeCaracteristiquesDerivees() {
        calculePdvInitiaux();
        calculeBaseInit();
        calculeLimiteAssaut();
        calculeBonusToucher();
        calculeBonusDegats();
        calculeBonusProtection();
    }

    // DUMP
    // *********************************************************************
    public void dump() {
        System.out.println("nom: <" + getNom() + ">");
        System.out.println("_force: <" + force + ">");
        System.out.println("_rapidite: <" + rapidite + ">");
        System.out.println("_endurance: <" + endurance + ">");
        System.out.println("_courage: <" + courage + ">");
        System.out.println("_chance: <" + chance + ">");
        System.out.println("_magnetisme: <" + magnetisme + ">");
        System.out.println("_seduction: <" + seduction + ">");
        System.out.println("_habilete: <" + habilete + ">");
        System.out.println("_pdv_initial: <" + pdv_initial + ">");
        System.out.println("_pdv_courant: <" + pdv_courant + ">");
        System.out.println("_baseInit: <" + baseInit + ">");
        System.out.println("_bonusToucher: <" + bonusToucher + ">");
        System.out.println("_bonusDegats: <" + bonusDegats + ">");
        System.out.println("_bonusProtection: <" + bonusProtection + ">");
        System.out.println("_limiteAssaut: <" + limiteAssaut + ">");
        System.out.println("_arme: <" + arme + ">");
        System.out.println("_armure: <" + armure + ">");
    }

    // UTILS
    // *********************************************************************

    public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes) {
        int nouveauTotalPdvCourant = pdv_courant + nombrePdvRegagnes;
        pdv_courant = (nouveauTotalPdvCourant > pdv_initial ? pdv_initial
                : nouveauTotalPdvCourant);
    }

    public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants) {
        pdv_courant = (nouveauTotalPdvCourants > pdv_initial ? pdv_initial
                : nouveauTotalPdvCourants);
    }

    public void rendPdvInitiaux() {
        pdv_courant = pdv_initial;
    }

    public boolean estMort() {
        return pdv_courant <= 0;
    }

    public void exportXml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Personnage.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(this, System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
