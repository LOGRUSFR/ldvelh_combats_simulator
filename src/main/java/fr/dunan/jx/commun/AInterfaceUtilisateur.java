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
package fr.dunan.jx.commun;

import java.util.Scanner;

public class AInterfaceUtilisateur implements IInterfaceUtilisateur
    {
        public Scanner entree;
    
    public void creePersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    public void affichePersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    public void ajouteNouveauPersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    public void lanceCombats()
        {
        // TODO Auto-generated method stub
        
        }

    public void modifieCaracteristiques() {
        // TODO Auto-generated method stub

    }

        public void restaurePdv() {
            // TODO Auto-generated method stub

        }

        public void equipePersonnageExistant() {
            // TODO Auto-generated method stub

        }

        public void appuieTouche() {
            System.out.println("Pressez entree pour revenir au menu precedent");
            entree.nextLine();
            entree.nextLine();
        }

    }
