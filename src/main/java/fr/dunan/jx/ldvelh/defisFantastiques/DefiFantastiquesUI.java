package fr.dunan.jx.ldvelh.defisFantastiques;

import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.dunan.jx.commun.AInterfaceUtilisateur;
import fr.dunan.jx.commun.Stockage;

public class DefiFantastiquesUI extends AInterfaceUtilisateur
    {
    // *****************************************************************************

    public final String _version = "v1.0";

    // *****************************************************************************

    public DefiFantastiquesUI()
        {
        _entree = new Scanner(System.in);
        }

    public DefiFantastiquesUI( Scanner entree )
        {
        _entree = entree;
        }

    public void creePersonnage()
        {
        System.out.println("Veuillez rentrer le nom du personnage :");
        Personnage p = new Personnage(_entree.next());
        p.dump();
        System.out.println("Stocke ce personnage ? (o/n)");
        if (_entree.next().equals("o"))
            Stockage.serialise(p);
        }

    public void affichePersonnage()
        {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = _entree.next();
        Personnage p = null;
        try
            {
            p = (Personnage) Stockage.deserialise(nom);
            p.dump();
            }
        catch( ClassCastException cce )
            {
            System.out.println("Fichier incompatible !");
            }
        catch( FileNotFoundException e )
            {
            System.out.println("Pas de personnage correspondant à ce nom !");
            }
        }

    public void ajouteNouveauPersonnage()
        {
        Personnage p = new Personnage();
        System.out.println("Veuillez rentrer le nom du personnage :");
        p.setNom(_entree.next());
        System.out.println("Veuillez rentrer l'habileté initiale du personnage :");
        int valeur = Integer.valueOf(_entree.next());
        p.set_habileteInitiale(valeur);
        p.set_habileteCourante(valeur);
        System.out.println("Veuillez rentrer l'endurance initiale du personnage :");
        valeur = Integer.valueOf(_entree.next());
        p.set_enduranceInitiale(valeur);
        p.set_enduranceCourante(valeur);
        System.out.println("Veuillez rentrer la chance initiale du personnage :");
        valeur = Integer.valueOf(_entree.next());
        p.set_chanceInitiale(valeur);
        p.set_chanceCourante(valeur);
        System.out.println("Donne des caracteristiques courantes ? (o/n)");
        if (_entree.next().equals("o"))
            {
            System.out.println("Veuillez rentrer l'habileté initiale du personnage :");
            p.set_habileteInitiale(Integer.valueOf(_entree.next()));
            System.out.println("Veuillez rentrer l'endurance initiale du personnage :");
            p.set_enduranceInitiale(Integer.valueOf(_entree.next()));
            System.out.println("Veuillez rentrer la chance initiale du personnage :");
            p.set_chanceInitiale((Integer.valueOf(_entree.next())));
            }
        p.dump();
        Stockage.serialise(p);
        }

    public void lanceCombats()
        {
        System.out.println("Veuillez rentrer le nom du premier assaillant :");
        String nomPremier = _entree.next();
        Personnage p1;
        try
            {
            p1 = (Personnage) Stockage.deserialise(nomPremier);
            System.out.println("Veuillez rentrer le nom du second assaillant :");
            String nomSecond = _entree.next();
            Personnage p2 = (Personnage) Stockage.deserialise(nomSecond);
            Combat c = null;
            do
                {
                c = new Combat(p1, p2);
                c.lanceAssauts();
                System.out.println("rejoue combat avec pdv actuels ?");
                }
            while( _entree.next().equals("o") );
            Stockage.serialise(p1);
            Stockage.serialise(p2);
            }
        catch( FileNotFoundException e )
            {
            System.out.println("Pas de personnage correspondant à ce nom !");
            }
        }

    public void modifieCaracteristiques()
        {
        System.out.println("Veuillez entrer le nom du personnage :");
        String nom = _entree.next();
        Personnage p = null;
        try
            {
            p = (Personnage) Stockage.deserialise(nom);
            }
        catch( FileNotFoundException e )
            {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
            }
        p.dump();
        System.out.println("Quelle caractéristique modifier ?");
        System.out.println("1: habileté");
        System.out.println("2: endurance");
        System.out.println("3: chance");
        int entreeCle = _entree.nextInt();
        System.out.println("Valeur caractéristique ?");
        int valeurCaracteristique = _entree.nextInt();
        switch (entreeCle)
            {
            case 1:
                p.set_habileteInitiale(valeurCaracteristique);
            break;
            case 2:
                p.set_enduranceInitiale(valeurCaracteristique);
            break;
            case 3:
                p.set_chanceInitiale(valeurCaracteristique);
            break;
            default:
            break;
            }
        p.dump();
        Stockage.serialise(p);
        }

    public void restaurePdv()
        {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = _entree.next();
        Personnage p = null;
        try
            {
            p = (Personnage) Stockage.deserialise(nom);
            }
        catch( FileNotFoundException e )
            {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
            }
        p.dump();
        System.out.println("Restauration total de pdv initial ? (o/n)");
        String entree = _entree.next();
        if (entree.equals("o"))
            p.rendPdvInitiaux();
        else
            {
            System.out.println("Nouveau total de pdv ?");
            p.restaurePdvCourantsParTotal(_entree.nextInt());
            }
        p.dump();
        Stockage.serialise(p);
        }

    public void equipePersonnageExistant()
        {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = _entree.next();
        Personnage p;
        try
            {
            p = (Personnage) Stockage.deserialise(nom);
            }
        catch( FileNotFoundException e )
            {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
            }
        p.dump();
        p.dump();
        Stockage.serialise(p);
        }

    public void menu()
        {
        boolean fin = false;
        fin: while( !fin )
            {
            System.out.println("defis fantastiques version <" + _version + ">");
            System.out.println("Choix :");
            System.out.println("1.Cree un personnage");
            System.out.println("2.Ajoute un personnage");
            System.out.println("3.Liste les personnages disponibles");
            System.out.println("4.Affiche le détail d'un personnage");
            System.out.println("5.Equipe un personnage existant");
            System.out.println("6.Deroule un combat");
            System.out.println("7.Modifie caractéristiques");
            System.out.println("8.Restaure pdv");
            System.out.println("9.Export du personnage en fichier xml");
            System.out.println("0.Sortir");
            // console peut etre null String entree =
            // System.console().readLine();
            int cle = -1;
            try
                {
                cle = _entree.nextInt();
                switch (cle)
                    {
                    case 1:
                        creePersonnage();
                        appuieTouche();
                    break;
                    case 2:
                        ajouteNouveauPersonnage();
                        appuieTouche();
                    break;
                    case 3:
                        Stockage.listePersonnage();
                        appuieTouche();
                    break;
                    case 4:
                        affichePersonnage();
                        appuieTouche();
                    break;
                    case 5:
                        equipePersonnageExistant();
                        appuieTouche();
                    break;
                    case 6:
                        lanceCombats();
                        appuieTouche();
                    break;
                    case 7:
                        modifieCaracteristiques();
                        appuieTouche();
                    break;
                    case 8:
                        restaurePdv();
                        appuieTouche();
                    break;
                    case 9:
                        exportText();
                        appuieTouche();
                    break;
                    case 0:
                        fin = true;
                    break fin;
                    default:
                    break;
                    }
                }
            catch( java.util.InputMismatchException e )
                {
                System.out.println("mauvais choix");
                appuieTouche();
                }
            }
        }

    public void exportText()
        {
        // TODO Auto-generated method stub
        }
    }
