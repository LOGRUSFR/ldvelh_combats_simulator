package fr.dunan.jx.ldvelh.defisFantastiques;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import fr.dunan.jx.commun.APersonnage;
import fr.dunan.jx.commun.Des;

@XmlRootElement
public class Personnage extends APersonnage
    {

    /**
	 * 
	 */
    private static final long       serialVersionUID = 2L;

    private int                     _habileteCourante;

    private int                     _habileteInitiale;

    private int                     _enduranceInitiale;

    private int                     _enduranceCourante;

    private int                     _chanceInitiale;

    private int                     _chanceCourante;

    private HashMap<String, String> _equipement;

    private int                     _or;

    public Personnage()
        {
        super();
        }

    public Personnage(String nom)
        {
        super(nom);
        this._enduranceInitiale = Des.lance2d6() + 12;
        _enduranceCourante = _enduranceInitiale;
        this._chanceInitiale = Des.lance1d6() + 6;
        _chanceCourante = _chanceInitiale;
        this._habileteInitiale = Des.lance1d6() + 6;
        _habileteCourante = _habileteInitiale;
        }

    // DUMP
    // *********************************************************************
    public void dump()
        {
        System.out.println("habilete courante / initiale: <"
                + _habileteCourante + "/" + _habileteInitiale + ">");
        System.out.println("endurance courante / initiale: <"
                + _enduranceCourante + "/" + _enduranceInitiale + ">");
        System.out.println("chance courante / initiale: <" + _chanceCourante
                + "/" + _chanceInitiale + ">");
        System.out.println("Or: <" + _or + ">");
        if (_equipement != null)
            {
            System.out.println("Equipement :");
            for (Map.Entry<String, String> e : _equipement.entrySet())
                {
                System.out.println("Objet <" + e.getKey() + "> effet <"
                        + e.getValue() + ">");
                }
            }
        }

    public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes)
        {
        set_enduranceCourante(get_enduranceCourante() + nombrePdvRegagnes);
        }

    public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants)
        {
        set_enduranceCourante(nouveauTotalPdvCourants);
        }

    public void rendPdvInitiaux()
        {
        set_enduranceCourante(get_enduranceInitiale());
        }

    public boolean estMort()
        {
        if (_enduranceCourante <= 0)
            return (true);
        return false;
        }

    // GETTER/SETTERS

    public int get_habileteCourante()
        {
        return _habileteCourante;
        }

    public void set_habileteCourante(int _habileteCourante)
        {
        this._habileteCourante = _habileteCourante;
        }

    public int get_habileteInitiale()
        {
        return _habileteInitiale;
        }

    public void set_habileteInitiale(int _habileteInitiale)
        {
        this._habileteInitiale = _habileteInitiale;
        }

    public int get_enduranceInitiale()
        {
        return _enduranceInitiale;
        }

    public void set_enduranceInitiale(int _enduranceInitiale)
        {
        this._enduranceInitiale = _enduranceInitiale;
        }

    public int get_enduranceCourante()
        {
        return _enduranceCourante;
        }

    public void set_enduranceCourante(int _enduranceCourante)
        {
        this._enduranceCourante = _enduranceCourante;
        }

    public int get_chanceInitiale()
        {
        return _chanceInitiale;
        }

    public void set_chanceInitiale(int _chanceInitiale)
        {
        this._chanceInitiale = _chanceInitiale;
        }

    public int get_chanceCourante()
        {
        return _chanceCourante;
        }

    public void set_chanceCourante(int _chanceCourante)
        {
        this._chanceCourante = _chanceCourante;
        }

    public Map<String, String> get_equipement()
        {
        return _equipement;
        }

    public void set_equipement(HashMap<String, String> equipement)
        {
        this._equipement = equipement;
        }

    public int get_or()
        {
        return _or;
        }

    public void set_or(int _or)
        {
        this._or = _or;
        }

    }
