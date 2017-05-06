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
package fr.fdunan.jx.ldvelh.loupArdent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import fr.fdunan.jx.commun.APersonnage;
import fr.fdunan.jx.commun.Des;

@XmlRootElement
public class Personnage extends APersonnage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	// ----------------------
	private int _force;

	private int _rapidite;

	private int _endurance;

	private int _courage;

	private int _chance;

	private int _magnetisme;

	private int _seduction;

	// ----------------------

	private int _habilete;

	// ----------------------

	private int _pdv_initial;

	private int _pdv_courant;

	private int _baseInit;

	private int _bonusToucher;

	private int _bonusDegats;

	private int _bonusProtection;

	private int _limiteAssaut;

	// ----------------------

	private Armes _arme = Armes.rien;

	private Armures _armure = Armures.rien;

	// ----------------------

	public Personnage() {
		super();
	}

	public Personnage(String nom) {
		super(nom);
		this._force = Des.lance2d6() * 8;
		this._rapidite = Des.lance2d6() * 8;
		this._endurance = Des.lance2d6() * 8;
		this._courage = Des.lance2d6() * 8;
		this._chance = Des.lance2d6() * 8;
		this._magnetisme = Des.lance2d6() * 8;
		this._seduction = Des.lance2d6() * 8;
		this._habilete = 0;
		calculeCaracteristiquesDerivees();
		rendPdvInitiaux();
	}

	/*
	 * public Personnage( String nom, int force, int rapidite, int endurance,
	 * int courage, int chance, int magnetisme, int seduction, int habilete ) {
	 * _nom = nom; this._force = force; this._rapidite = rapidite;
	 * this._endurance = endurance; this._courage = courage; this._chance =
	 * chance; this._magnetisme = magnetisme; this._seduction = seduction;
	 * this._habilete = habilete; calculeCaracteristiquesSecondaires(); }
	 */

	private void calculeLimiteAssaut() {
		_limiteAssaut = Math.round(((Integer) _endurance).floatValue() / 10);
	}

	private void calculeBaseInit() {
		_baseInit = _rapidite + _courage + _chance;
	}

	private void calculePdvInitiaux() {
		_pdv_initial = _force + _rapidite + _endurance + _courage + _chance
				+ _magnetisme + _seduction + _habilete;
	}

	private void calculeBonusToucher() {
		_bonusToucher = _chance > 71 ? 1 : 0;
		_bonusToucher += _habilete / 10;
	}

	private void calculeBonusDegats() {
		_bonusDegats = _force / 8;
		_bonusDegats += _arme.getBonusDegat();
	}

	private void calculeBonusProtection() {
		_bonusProtection = _armure.getBonusProtection();
	}

	public void calculeCaracteristiquesDerivees() {
		calculePdvInitiaux();
		calculeBaseInit();
		calculeLimiteAssaut();
		calculeBonusToucher();
		calculeBonusDegats();
		calculeBonusProtection();
	}

	public String get_nom() {
		return getNom();
	}

	public void set_nom(String _nom) {
		setNom(_nom);
	}

	/**
	 * @return the _pdv_initial
	 */
	public int get_pdv_initial() {
		return _pdv_initial;
	}

	/**
	 * @param _pdv_initial
	 *            the _pdv_initial to set
	 */
	public void set_pdv_initial(int _pdv_initial) {
		this._pdv_initial = _pdv_initial;
	}

	/**
	 * @return the _pdv_courant
	 */
	public int get_pdv_courant() {
		return _pdv_courant;
	}

	/**
	 * @param _pdv_courant
	 *            the _pdv_courant to set
	 */
	public void set_pdv_courant(int _pdv_courant) {
		this._pdv_courant = _pdv_courant;
	}

	// GETTER CARACTERISTIQUES DERIVEES****************************************

	public int get_baseInit() {
		return _baseInit;
	}

	public int get_limiteAssaut() {
		return _limiteAssaut;
	}

	/**
	 * @return the _bonusToucher
	 */
	public int get_bonusToucher() {
		return _bonusToucher;
	}

	/**
	 * @return the _bonusDegats
	 */
	public int get_bonusDegats() {
		return _bonusDegats;
	}

	public int get_bonusProtection() {
		return this._bonusProtection;
	}

	// SETTER CARACTERISTIQUES PRINCIPALES*************************************

	/**
	 * @param _force
	 *            the _force to set
	 */
	public void set_force(int _force) {
		this._force = _force;
	}

	/**
	 * @param _rapidite
	 *            the _rapidite to set
	 */
	public void set_rapidite(int _rapidite) {
		this._rapidite = _rapidite;
	}

	public void set_endurance(int _endurance) {
		this._endurance = _endurance;
	}

	public void set_courage(int _courage) {
		this._courage = _courage;
	}

	public void set_chance(int _chance) {
		this._chance = _chance;
	}

	public void set_magnetisme(int _magnetisme) {
		this._magnetisme = _magnetisme;
	}

	public void set_seduction(int _seduction) {
		this._seduction = _seduction;
	}

	public void set_habilete(int _habilete) {
		this._habilete = _habilete;
	}

	/**
	 * @return the _habilete
	 */
	public int get_habilete() {
		return _habilete;
	}

	public Armes get_arme() {
		return _arme;
	}

	public void set_arme(Armes _arme) {
		this._arme = _arme;

	}

	public Armures get_armure() {
		return _armure;
	}

	public void set_armure(Armures _armure) {
		this._armure = _armure;
	}

	// DUMP
	// *********************************************************************
	public void dump() {
		System.out.println("nom: <" + getNom() + ">");
		System.out.println("_force: <" + _force + ">");
		System.out.println("_rapidite: <" + _rapidite + ">");
		System.out.println("_endurance: <" + _endurance + ">");
		System.out.println("_courage: <" + _courage + ">");
		System.out.println("_chance: <" + _chance + ">");
		System.out.println("_magnetisme: <" + _magnetisme + ">");
		System.out.println("_seduction: <" + _seduction + ">");
		System.out.println("_habilete: <" + _habilete + ">");
		System.out.println("_pdv_initial: <" + _pdv_initial + ">");
		System.out.println("_pdv_courant: <" + _pdv_courant + ">");
		System.out.println("_baseInit: <" + _baseInit + ">");
		System.out.println("_bonusToucher: <" + _bonusToucher + ">");
		System.out.println("_bonusDegats: <" + _bonusDegats + ">");
		System.out.println("_bonusProtection: <" + _bonusProtection + ">");
		System.out.println("_limiteAssaut: <" + _limiteAssaut + ">");
		System.out.println("_arme: <" + _arme + ">");
		System.out.println("_armure: <" + _armure + ">");
	}

	// UTILS
	// *********************************************************************

	public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes) {
		int nouveauTotalPdvCourant = _pdv_courant + nombrePdvRegagnes;
		_pdv_courant = (nouveauTotalPdvCourant > _pdv_initial ? _pdv_initial
				: nouveauTotalPdvCourant);
	}

	public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants) {
		_pdv_courant = (nouveauTotalPdvCourants > _pdv_initial ? _pdv_initial
				: nouveauTotalPdvCourants);
	}

	public void rendPdvInitiaux() {
		_pdv_courant = _pdv_initial;
	}

	@Override
	public boolean estMort() {
		if (_pdv_courant <= 0)
			return (true);
		return false;
	}
	
}
