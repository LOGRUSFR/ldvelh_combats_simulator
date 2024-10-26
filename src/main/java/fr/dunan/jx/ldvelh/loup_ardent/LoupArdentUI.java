package fr.dunan.jx.ldvelh.loup_ardent;

import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.dunan.jx.commun.AInterfaceUtilisateur;
import fr.dunan.jx.commun.Stockage;
import fr.dunan.jx.commun.Version;

public class LoupArdentUI extends AInterfaceUtilisateur {

    public LoupArdentUI() {
        entree = new Scanner(System.in);
    }

    public LoupArdentUI(Scanner entree) {
        this.entree = entree;
    }

    public void creePersonnage() {
        System.out.println("Veuillez rentrer le nom du personnage :");
        Personnage p = new Personnage(entree.next());
        p.dump();
        System.out.println("Stocke ce personnage ? (o/n)");
        if (entree.next().equals("o"))
            Stockage.serialise(p);
    }

    public void affichePersonnage() {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = entree.next();
        Personnage p = null;
        try {
            p = (Personnage) Stockage.deserialise(nom);
            p.dump();
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
        }
    }

    public void ajouteNouveauPersonnage() {
        Personnage p = new Personnage();
        System.out.println("Veuillez rentrer le nom du personnage :");
        p.setNom(entree.next());
        System.out.println("Veuillez rentrer la force du personnage :");
        p.setForce(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer la rapidité du personnage :");
        p.setRapidite(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer l'endurance du personnage :");
        p.setEndurance(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer le courage du personnage :");
        p.setCourage(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer l'habileté du personnage :");
        p.setHabilete(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer la chance du personnage :");
        p.setChance(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer le magnétisme du personnage :");
        p.setMagnetisme(Integer.valueOf(entree.next()));
        System.out.println("Veuillez rentrer la séduction du personnage :");
        p.setSeduction(Integer.valueOf(entree.next()));
        System.out.println("Choix des armes et armures:");
        choixArme(p);
        choixArmure(p);
        p.calculeCaracteristiquesDerivees();
        p.rendPdvInitiaux();
        // choixBouclier( p );
        p.dump();
        Stockage.serialise(p);
    }

    /*
     * public void choixBouclier( Personnage p ) { System.out.println(
     * "A t il un bouclier ? (o/n)" ); if( _entree.next().equals( "o" ) ) { if(
     * p.get_bonusArmure() > 0 ) p.set_bonusArmure( p.get_bonusArmure() + 5 );
     * else p.set_bonusArmure( p.get_bonusArmure() + 7 ); } }
     */
    public void choixArmure(Personnage p) {
        System.out.println("Choix des armures <");
        for (Armures i : Armures.values())
            System.out.println(i + " -" + i.getBonusProtection());
        System.out.println(">");
        System.out.println("Veuillez choisir une armure :");
        p.setArmure(Armures.valueOf(entree.next()));
    }

    public void choixArme(Personnage p) {
        for (Armes i : Armes.values())
            System.out.println(i + " + " + i.getBonusDegat());
        System.out.println(">");
        System.out.println("Veuillez choisir un nom d'arme :");
        p.setArme(Armes.valueOf(entree.next()));
    }

    public void lanceCombats() {
        System.out.println("Veuillez rentrer le nom du premier assaillant :");
        String nomPremier = entree.next();
        Personnage p1;
        try {
            p1 = (Personnage) Stockage.deserialise(nomPremier);
            System.out
                    .println("Veuillez rentrer le nom du second assaillant :");
            String nomSecond = entree.next();
            Personnage p2 = (Personnage) Stockage.deserialise(nomSecond);
            int p1PdvSauve = p1.getPdv_courant();
            int p2PdvSauve = p2.getPdv_courant();
            Combat c = null;
            do {
                c = new Combat(p1, p2);
                p1.setPdv_courant(p1PdvSauve);
                p2.setPdv_courant(p2PdvSauve);
                c.lanceAssauts();
                System.out.println("rejoue combat avec pdv actuels ?");
            }
            while (entree.next().equals("o"));
            c.recompenseVictorieux();
            Stockage.serialise(p1);
            Stockage.serialise(p2);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
        }
    }

    public void modifieCaracteristiques() {
        System.out.println("Veuillez entrer le nom du personnage :");
        String nom = entree.next();
        Personnage p = null;
        try {
            p = (Personnage) Stockage.deserialise(nom);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
        }
        p.dump();
        System.out.println("Quelle caractéristique modifier ?");
        System.out.println("1: force");
        System.out.println("2: rapidité");
        System.out.println("3: endurance");
        System.out.println("4: courage");
        System.out.println("5: habileté");
        System.out.println("6: chance");
        System.out.println("7: magnétisme");
        System.out.println("8: séduction");
        int entreeCle = entree.nextInt();
        System.out.println("Valeur caractéristique ?");
        int valeurCaracteristique = entree.nextInt();
        switch (entreeCle) {
            case 1:
                p.setForce(valeurCaracteristique);
                break;
            case 2:
                p.setRapidite(valeurCaracteristique);
                break;
            case 3:
                p.setEndurance(valeurCaracteristique);
                break;
            case 4:
                p.setCourage(valeurCaracteristique);
                break;
            case 5:
                p.setHabilete(valeurCaracteristique);
                break;
            case 6:
                p.setChance(valeurCaracteristique);
                break;
            case 7:
                p.setMagnetisme(valeurCaracteristique);
                break;
            case 8:
                p.setSeduction(valeurCaracteristique);
                break;
            default:
                break;
        }
        p.calculeCaracteristiquesDerivees();
        p.dump();
        Stockage.serialise(p);
    }

    public void restaurePdv() {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = entree.next();
        Personnage p = null;
        try {
            p = (Personnage) Stockage.deserialise(nom);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
        }
        p.dump();
        System.out.println("Restauration total de pdv initial ? (o/n)");
        String entree = this.entree.next();
        if (entree.equals("o"))
            p.rendPdvInitiaux();
        else {
            System.out.println("Nouveau total de pdv ?");
            p.restaurePdvCourantsParTotal(this.entree.nextInt());
        }
        p.dump();
        Stockage.serialise(p);
    }

    public void equipePersonnageExistant() {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = entree.next();
        Personnage p;
        try {
            p = (Personnage) Stockage.deserialise(nom);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
        }
        p.dump();
        choixArme(p);
        choixArmure(p);
        // choixBouclier( p );
        p.calculeCaracteristiquesDerivees();
        p.dump();
        Stockage.serialise(p);
    }

    public void menu() {
        boolean fin = false;
        fin:
        while (!fin) {
            Version version = Version.build();
            System.out.println("Loup*ardent version <" + version.getProgramVersion() + ">");
            System.out.println("Choix :");
            System.out.println("1.Cree aléatoirement un personnage");
            System.out.println("2.Crée manuellement un personnage");
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
            try {
                cle = entree.nextInt();
                switch (cle) {
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
                        exportXml();
                        appuieTouche();
                        break;
                    case 0:
                        fin = true;
                        break fin;
                    default:
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("mauvais choix");
                appuieTouche();
            }
        }
    }

    private void exportXml() {
        System.out.println("Veuillez rentrer le nom du personnage :");
        String nom = entree.next();
        Personnage p = null;
        try {
            p = (Personnage) Stockage.deserialise(nom);
            p.exportXml();
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
        }

    }

}
