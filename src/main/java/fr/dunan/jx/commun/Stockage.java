/*
 *
 * This file is part of ldvelh-combats.

    ldvelh-combats is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ldvelh-combats is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ldvelh-combats.  If not, see <http://www.gnu.org/licenses/>
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

public class Stockage
    {
    private static final String CHEMIN_DES_PERSONNAGES = "src/main/ressources";

    public static void listePersonnage()
        {
        FilenameFilter filtreExtensionFichier = new FilenameFilter()
            {
            public boolean accept( File arg0, String arg1 )
                {
                return arg1.endsWith( ".xml" );
                }
            };
        File repertoire = new File( CHEMIN_DES_PERSONNAGES );
        String[] children = repertoire.list( filtreExtensionFichier );
        for( int i = 0; i < children.length; i++ )
            {
            System.out.println( children[i].substring( 0,
                                                       children[i].lastIndexOf( ".xml" ) ) );
            }

        }

    public static void serialise( APersonnage p )
        {
        FileOutputStream fichier = null;
        try
            {
            fichier = new FileOutputStream( CHEMIN_DES_PERSONNAGES + "/"
                            + p.getNom() + ".xml" );
            }
        catch( java.io.IOException e )
            {
            System.err.println( "Erreur lors de la création du fichier de sortie" );
            e.printStackTrace();
            }
        XMLEncoder xmlEnc = null;
        try
            {
            xmlEnc = new XMLEncoder( new BufferedOutputStream( fichier ) );
            xmlEnc.writeObject( p );
            xmlEnc.flush();
            }
        catch( Exception e )
            {
            System.err.println( "Erreur lors de la serialisation de l'objet de sortie" );
            e.printStackTrace();
            }
        finally
            {
            if( xmlEnc != null )
                {
                xmlEnc.close();
                }
            }
        }
    
    

    public static APersonnage deserialise( String nomPersonnage )
                    throws FileNotFoundException
        {
        APersonnage p = null;
        FileInputStream fichier;
        XMLDecoder decoder = null;
        fichier = new FileInputStream( CHEMIN_DES_PERSONNAGES + "/"
                        + nomPersonnage + ".xml" );
        try
            {
            decoder = new XMLDecoder( new BufferedInputStream( fichier ) );
            p = (APersonnage) decoder.readObject();
            }
        catch( final Exception e )
            {
            e.printStackTrace();
            }
        finally
            {
            if( decoder != null )
                {
                decoder.close();
                }
            }
        return( p);
        }
    }
