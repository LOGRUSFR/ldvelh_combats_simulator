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

/**
 * @author dunan
 */
public enum Armes {
    RIEN(0), EPEE(10), FLEAU(7), FLECHE(10), GOURDIN(8), HACHE(15), HALLEBARDE(12), JAVELOT(
            12), LANCE(12), MASSE(14), POIGNARD(5), EXTERMINATOR(20), EXTERMINATOR_ECLAIRANTE(20);

    private final int bonusDegat;

    /**
     * Constructeur
     */
    Armes(int bonusDegat) {
        this.bonusDegat = bonusDegat;
    }

    public boolean isExterminator() {
        return this == Armes.EXTERMINATOR || this == Armes.EXTERMINATOR_ECLAIRANTE;
    }

    public int getBonusDegat() {
        return this.bonusDegat;
    }

    public int get_malusPdvParAssaut() {
        return (isExterminator() ? 10 : 0);
    }


}
