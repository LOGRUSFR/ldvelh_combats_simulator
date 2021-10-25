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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

public class Stockage {

    public static void listePersonnage() {
        FilenameFilter filtreExtensionFichier = new FilenameFilter() {
            public boolean accept(File arg0, String arg1) {
                return arg1.endsWith(".xml");
            }
        };
        File repertoire = new File(".");
        String[] childrens = repertoire.list(filtreExtensionFichier);
        for (String children : childrens) {
            System.out.println(children.substring(0,
                    children.lastIndexOf(".xml")));
        }

    }

    public static void serialise(APersonnage p) {
        FileOutputStream fichier = null;
        try {
            fichier = new FileOutputStream(p.getNom() + ".xml");
        } catch (java.io.IOException e) {
            System.err.println("Erreur lors de la création du fichier de sortie");
            e.printStackTrace();
        }
        try (XMLEncoder xmlEnc = new XMLEncoder(new BufferedOutputStream(fichier))) {
            xmlEnc.writeObject(p);
            xmlEnc.flush();
        } catch (Exception e) {
            System.err.println("Erreur lors de la serialisation de l'objet de sortie");
            e.printStackTrace();
        }
    }

    public static APersonnage deserialise(String nomPersonnage)
            throws FileNotFoundException {
        APersonnage p = null;
        FileInputStream fichier;
        fichier = new FileInputStream(nomPersonnage + ".xml");
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fichier))) {
            p = (APersonnage) decoder.readObject();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return (p);
    }
}
