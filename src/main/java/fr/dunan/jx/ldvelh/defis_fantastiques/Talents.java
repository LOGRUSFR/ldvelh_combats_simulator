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
 *
 * For FF#29
 */
public enum Talents {
    DECYPHER("Décrypteur"),
    BURGLAR("Monte en l'air"),
    PICK_LOCK("Crocheteur"),
    STEALER("Vide goussets"),
    HIDDEN_IN_SHADOWS("Passe murailles"),
    STEALTH("Pas de loup"),
    SPOTTER("Cache tampon");

    private final String name;

    /**
     * Constructeur
     */
    Talents(String name) {
        this.name = name;
    }

    public String getName() {return name;}
}
