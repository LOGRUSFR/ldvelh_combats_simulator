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
package fr.dunan.jx.commun;

import java.util.Scanner;

import fr.dunan.jx.ldvelh.defis_fantastiques.DefisFantastiquesUI;
import fr.dunan.jx.ldvelh.loup_ardent.LoupArdentIU;

public class AutoCombatIU
    {
    /**
     * RELEASE NOTES
     * 
     * V1.0: - Utilisation du projet loup ardent - Refactor code (design) -
     * Ajout packages defis fantastiques
     */
    private static Scanner      _entree  = new Scanner(System.in);

    private static void appuieTouche()
        {
        System.out.println("Pressez entree pour revenir au menu precedent");
        _entree.nextLine();
        _entree.nextLine();
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
            while (true) {
                Version version = Version.build();
                System.out.println(version.getProgramName() + " version <" + version.getProgramVersion() + ">");
                System.out.println("Choix :");
                System.out.println("1.Loup*Ardent");
                System.out.println("2.Defis Fantastiques");
                System.out.println("0.Sortir");
                // console peut etre null String entree =
                // System.console().readLine();
                int cle = -1;
                try {
                    cle = _entree.nextInt();
                    switch (cle) {
                        case 1:
                            LoupArdentIU laiu = new LoupArdentIU(_entree);
                            laiu.menu();
                            appuieTouche();
                            break;
                        case 2:
                            DefisFantastiquesUI dfiu = new DefisFantastiquesUI(_entree);
                            dfiu.menu();
                            appuieTouche();
                            break;
                    case 0:
                        System.exit(0);
                    break;
                    default:
                    break;
                    }
                }
            catch (java.util.InputMismatchException e)
                {
                System.out.println("mauvais choix");
                appuieTouche();
                }
            }
        }

    }
