/*
 *
 * This file is part of ldvelh_combats_simulator.

    ldvelh_combats_simulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ldvelh_combats_simulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ldvelh_combats_simulator.  If not, see <http://www.gnu.org/licenses/>
 *
 */
package fr.dunan.jx.ldvelh.defis_fantastiques;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;

import fr.dunan.jx.commun.APersonnage;
import fr.dunan.jx.commun.Des;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlRootElement
public class Personnage extends APersonnage {
    private static final long serialVersionUID = 2L;

    private int habileteCourante;

    private int habileteInitiale;

    private int enduranceInitiale;

    private int enduranceCourante;

    private int chanceInitiale;

    private int chanceCourante;

    private HashMap<String, String> equipement;

    private List<String> talents;

    private int or;

    public Personnage() {
        super();
        if(equipement == null)
            equipement = new LinkedHashMap<String,String>();
        if(talents == null)
            talents = new ArrayList<String>();
    }

    public Personnage(String nom) {
        super(nom);
        this.enduranceInitiale = Des.lance2d6() + 12;
        enduranceCourante = enduranceInitiale;
        this.chanceInitiale = Des.lance1d6() + 6;
        chanceCourante = chanceInitiale;
        this.habileteInitiale = Des.lance1d6() + 6;
        habileteCourante = habileteInitiale;
        if(equipement == null)
            equipement = new LinkedHashMap<String,String>();
        if(talents == null)
            talents = new ArrayList<String>();
    }

    // DUMP
    // *********************************************************************
    public void dump() {
        System.out.println("Caracteristiques " + String.valueOf('-').repeat(50));
        System.out.println("habilete courante / initiale: <"
                + habileteCourante + "/" + habileteInitiale + ">");
        System.out.println("endurance courante / initiale: <"
                + enduranceCourante + "/" + enduranceInitiale + ">");
        System.out.println("chance courante / initiale: <" + chanceCourante
                + "/" + chanceInitiale + ">");
        System.out.println("Or: <" + or + ">");
        if (equipement != null) {
            System.out.println("Equipement " + String.valueOf('-').repeat(50));
            for (Map.Entry<String, String> e : equipement.entrySet()) {
                System.out.println("Objet <" + e.getKey() + "> effet <"
                        + e.getValue() + ">");
            }
        }
        if (talents != null) {
            System.out.println("Talents " + String.valueOf('-').repeat(50));
            for (String talent : talents) {
                System.out.println("Talent <" + talent + ">");
            }
        }
        System.out.println(String.valueOf('-').repeat(70));
    }

    public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes) {
        setEnduranceCourante(getEnduranceCourante() + nombrePdvRegagnes);
    }

    public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants) {
        setEnduranceCourante(nouveauTotalPdvCourants);
    }

    public void rendPdvInitiaux() {
        setEnduranceCourante(getEnduranceInitiale());
    }

    public boolean estMort() {
        return (enduranceCourante <= 0);
    }
}
