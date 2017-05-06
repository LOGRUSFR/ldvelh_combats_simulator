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
public enum Armes
    {
    rien(0), epee(10), fleau(7), fleche(10), gourdin(8), hache(15), hallebarde(12), javelot(
                    12), lance(12), masse(14), poignard(5), exterminator (20), exterminator_eclairante (20);

    private int bonusDegat;

    /** Constructeur */
    Armes( int bonusDegat )
        {
        this.bonusDegat = bonusDegat;
        }

    public boolean isExterminator()
        {
        if( this == Armes.exterminator  || this == Armes.exterminator_eclairante )
            return( true );
        return( false );
        }

    public int getBonusDegat()
        {
        return this.bonusDegat;
        }

    public int get_malusPdvParAssaut()
        {
        return( isExterminator() ? 10 :0);
        }


    }
