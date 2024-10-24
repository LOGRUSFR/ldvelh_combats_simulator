/**
 * 
 */
package fr.dunan.jx.commun;

/**
 * @author dunan
 * 
 */
public interface IPersonnage {

	void dump();
	
	boolean estMort();

	void restaurePdvCourantsParIncrement(int nombrePdvRegagnes);

	void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants);

	void rendPdvInitiaux();

}
