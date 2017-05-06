package fr.fdunan.jx.commun;

import java.util.Scanner;

public class AInterfaceUtilisateur implements IInterfaceUtilisateur
    {
    public Scanner      _entree;
    
    @Override
    public void creePersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void affichePersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void ajouteNouveauPersonnage()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void lanceCombats()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void modifieCaracteristiques()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void restaurePdv()
        {
        // TODO Auto-generated method stub
        
        }

    @Override
    public void equipePersonnageExistant()
        {
        // TODO Auto-generated method stub
        
        }
    
    public void appuieTouche()
        {
        System.out.println("Pressez entree pour revenir au menu precedent");
        _entree.nextLine();
        _entree.nextLine();
        }

    }
