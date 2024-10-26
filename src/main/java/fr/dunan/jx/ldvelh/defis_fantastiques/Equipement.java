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
package fr.dunan.jx.ldvelh.defis_fantastiques;

/**
 * @author dunan
 */
public enum Equipement {
    WEAPON("Epée"),
    ARMOR("Armure de cuir"),
    BAG("Sac à dos (6 objets speciaux)"),
    LIGHT_KIT("Lanterne, torche et briquet"),
    FOOD("Nourriture"),
    POTION_H("Potion d'Habileté"),
    POTION_E("Potion d'Endurance"),
    POTION_C("Potion de Chance");

    private final String name;

    /**
     * Constructeur
     */
    Equipement(String nom) {
        this.name = nom;
    }

    public String getName() {return name;}
}
