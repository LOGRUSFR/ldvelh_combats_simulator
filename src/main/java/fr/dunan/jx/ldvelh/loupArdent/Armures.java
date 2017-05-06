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
package fr.dunan.jx.ldvelh.loupArdent;

/**
 * @author dunan
 *
 */
public enum Armures
    {
    rien(0), armure(12), armureEtBouclier(17), cotteDeMailles(8), cotteDeMaillesEtBouclier(13), cuirasse(5), cuirasseEtBouclier(10), bouclier(7);

    protected int bonusProtection;

    /** Constructeur */
    Armures( int bonusProtection )
        {
        this.bonusProtection = bonusProtection;
        }

    public int getBonusProtection()
        {
        return this.bonusProtection;
        }

    }
