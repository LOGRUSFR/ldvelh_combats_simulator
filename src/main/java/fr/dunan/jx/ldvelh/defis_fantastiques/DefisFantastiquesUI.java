package fr.dunan.jx.ldvelh.defis_fantastiques;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import fr.dunan.jx.commun.AInterfaceUtilisateur;
import fr.dunan.jx.commun.Stockage;
import fr.dunan.jx.commun.Version;

public class DefisFantastiquesUI extends AInterfaceUtilisateur {

    public DefisFantastiquesUI() {
        entree = new Scanner(System.in);
    }

    public DefisFantastiquesUI(Scanner entree) {
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
        Personnage p;
        try {
            p = (Personnage) Stockage.deserialise(nom);
            p.dump();
        } catch (ClassCastException cce) {
            System.out.println("Fichier incompatible !");
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
        }
    }

    public void ajouteNouveauPersonnage() {
        Personnage p = new Personnage();
        System.out.println("Veuillez rentrer le nom du personnage :");
        p.setNom(entree.next());
        System.out.println("Veuillez rentrer l'habileté initiale du personnage :");
        int valeur = Integer.parseInt(entree.next());
        p.setHabileteInitiale(valeur);
        p.setHabileteCourante(valeur);
        System.out.println("Veuillez rentrer l'endurance initiale du personnage :");
        valeur = Integer.parseInt(entree.next());
        p.setEnduranceInitiale(valeur);
        p.setEnduranceCourante(valeur);
        System.out.println("Veuillez rentrer la chance initiale du personnage :");
        valeur = Integer.parseInt(entree.next());
        p.setChanceInitiale(valeur);
        p.setChanceCourante(valeur);
        System.out.println("Donne des caracteristiques courantes ? (o/n)");
        if (entree.next().equals("o")) {
            System.out.println("Veuillez rentrer l'habileté initiale du personnage :");
            p.setHabileteInitiale(Integer.parseInt(entree.next()));
            System.out.println("Veuillez rentrer l'endurance initiale du personnage :");
            p.setEnduranceInitiale(Integer.parseInt(entree.next()));
            System.out.println("Veuillez rentrer la chance initiale du personnage :");
            p.setChanceInitiale((Integer.parseInt(entree.next())));
        }
        p.dump();
        Stockage.serialise(p);
    }

    public void lanceCombats() {
        System.out.println("Veuillez rentrer le nom du premier assaillant :");
        String nomPremier = entree.next();
        Personnage p1;
        try {
            p1 = (Personnage) Stockage.deserialise(nomPremier);
            System.out.println("Veuillez rentrer le nom du second assaillant :");
            String nomSecond = entree.next();
            Personnage p2 = (Personnage) Stockage.deserialise(nomSecond);
            Combat c;
            do {
                c = new Combat(p1, p2);
                c.lanceAssauts();
                System.out.println("rejoue combat avec pdv actuels ?");
            }
            while (entree.next().equals("o"));
            Stockage.serialise(p1);
            Stockage.serialise(p2);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
        }
    }

    public void modifieCaracteristiques() {
        System.out.println("Veuillez entrer le nom du personnage :");
        String nom = entree.next();
        Personnage p;
        try {
            p = (Personnage) Stockage.deserialise(nom);
        } catch (FileNotFoundException e) {
            System.out.println("Pas de personnage correspondant à ce nom !");
            return;
        }
        p.dump();
        System.out.println("Quelle caractéristique modifier ?");
        System.out.println("1: habileté");
        System.out.println("2: endurance");
        System.out.println("3: chance");
        System.out.println("4: habileté courante");
        System.out.println("5: endurance courante");
        System.out.println("6: chance courante");
        int entreeCle = entree.nextInt();
        System.out.println("Valeur caractéristique ?");
        int valeurCaracteristique = entree.nextInt();
        switch (entreeCle) {
            case 1:
                p.setHabileteInitiale(valeurCaracteristique);
                break;
            case 2:
                p.setEnduranceInitiale(valeurCaracteristique);
                break;
            case 3:
                p.setChanceInitiale(valeurCaracteristique);
                break;
            case 4:
                p.setHabileteCourante(valeurCaracteristique);
                break;
            case 5:
                p.setEnduranceCourante(valeurCaracteristique);
                break;
            case 6:
                p.setChanceCourante(valeurCaracteristique);
                break;
            default:
                break;
        }
        p.dump();
        Stockage.serialise(p);
    }

    public void restaurePdv() {
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

    private void modifieTalents() {
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
        String[] tableauTalents = Arrays.stream(Talents.values())
                .map(Talents::getName)
                .toArray(String[]::new);
        int i=0;
        for (String talent : tableauTalents) {
            System.out.println("Talent " + i++ + " " + talent);
        }
        System.out.println("Veuillez rentrer le numero du talent choisi:");
        int numeroTalent = Integer.parseInt(entree.next());
        if(numeroTalent > 6) {
            System.out.println("Talent inconnu !");
            return;
        }
        if(p.getTalents().size()==3) {
            System.out.println("Nombre de talents maximal atteint !");
            return;
        }
        p.getTalents().add(tableauTalents[numeroTalent]);
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
        System.out.println("Quel equipement ajouter ?");
        System.out.println("1: Or");
        System.out.println("2: Arme");
        System.out.println("3: Armure");
        System.out.println("4: Sac à dos");
        System.out.println("5: Lanterne, torche et briquet");
        System.out.println("6: Nourriture");
        System.out.println("7: Potion magique");
        int entreeCle = entree.nextInt();
        switch (entreeCle) {
            case 1:
                System.out.println("Quantité ?");
                p.setOr(entree.nextInt());
                break;
            case 2:
                p.getEquipement().put(Equipement.WEAPON.getName(), "");
                break;
            case 3:
                p.getEquipement().put(Equipement.ARMOR.getName(), "");
                break;
            case 4:
                p.getEquipement().put(Equipement.BAG.getName(), "");
                break;
            case 5:
                p.getEquipement().put(Equipement.LIGHT_KIT.getName(), "");
                break;
            case 6:
                p.getEquipement().put(Equipement.FOOD.getName(), "");
                break;
            case 7:
                System.out.println("Type de potion ?");
                System.out.println("1: Habileté");
                System.out.println("2: Endurance");
                System.out.println("3: Chance");
                entreeCle = entree.nextInt();
                switch (entreeCle) {
                    case 1:
                        p.getEquipement().put(Equipement.POTION_H.getName(), "");
                        break;
                    case 2:
                        p.getEquipement().put(Equipement.POTION_E.getName(), "");
                        break;
                    case 3:
                        p.getEquipement().put(Equipement.POTION_C.getName(), "");
                        break;
                }
                break;
            default:
                break;
        }
        Stockage.serialise(p);
    }

    public void menu() {
        boolean fin = false;
        fin:
        while (!fin) {
            System.out.println("defis fantastiques version <" + Version.build().getProgramVersion() + ">");
            System.out.println("Choix :");
            System.out.println("1.Crée automatiquement un personnage");
            System.out.println("2.Crée manuellement un personnage");
            System.out.println("3.Modifie les caractéristiques");
            System.out.println("4.Modifie les talents");
            System.out.println("5.Equipe un personnage existant");
            System.out.println("6.Liste les personnages disponibles");
            System.out.println("7.Affiche le détail d'un personnage");
            System.out.println("8.Deroule un combat");
            System.out.println("9.Restaure pdv");
            System.out.println("0.Sortir");
            // console peut etre null String entree =
            // System.console().readLine();
            int cle;
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
                        modifieCaracteristiques();
                        appuieTouche();
                        break;
                    case 4:
                        modifieTalents();
                        appuieTouche();
                        break;
                    case 5:
                        equipePersonnageExistant();
                        appuieTouche();
                        break;
                    case 6:
                        Stockage.listePersonnage();
                        appuieTouche();
                        break;
                    case 7:
                        affichePersonnage();
                        appuieTouche();
                        break;
                    case 8:
                        lanceCombats();
                        appuieTouche();
                        break;
                    case 9:
                        restaurePdv();
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
}
