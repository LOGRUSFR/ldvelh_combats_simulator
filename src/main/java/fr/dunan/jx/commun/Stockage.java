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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Stockage
    {
    private static final String CHEMIN_DES_PERSONNAGES = "src/main/ressources";

	public static void listePersonnage()
        {
        FilenameFilter serFilter = new FilenameFilter()
            {
                public boolean accept( File arg0, String arg1 )
                    {
                    return arg1.endsWith( ".ser" );
                    }
            };
        File repertoire = new File( CHEMIN_DES_PERSONNAGES );
        String[] children = repertoire.list( serFilter );
        for( int i = 0; i < children.length; i++ )
            {
            System.out.println( children[i].substring( 0,
                                                       children[i].lastIndexOf( ".ser" ) ) );
            }

        }

    public static void serialise( APersonnage p )
        {
        try
            {
            FileOutputStream fichier = new FileOutputStream( p.getNom()
                            + ".ser" );
            ObjectOutputStream oos = new ObjectOutputStream( fichier );
            oos.writeObject( p );
            oos.flush();
            oos.close();
            }
        catch( java.io.IOException e )
            {
            e.printStackTrace();
            }
        }

    public static APersonnage deserialise( String nomPersonnage )
                    throws FileNotFoundException
        {
        APersonnage p = null;
        FileInputStream fichier;
        ObjectInputStream ois = null;
        fichier = new FileInputStream( nomPersonnage + ".ser" );
        try
            {
            ois = new ObjectInputStream( fichier );
            p = (APersonnage) ois.readObject();
            }
        catch( ClassNotFoundException e )
            {
            e.printStackTrace();
            }
        catch( IOException e )
            {
            e.printStackTrace();
            }
        finally
        {
        	try {
				fichier.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("pb au close fichier");
				e.printStackTrace();
			}
        }
        return( p);
        }
    }
