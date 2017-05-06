/**
 * 
 */
package fr.fdunan.jx.commun;

/**
 * @author fdunan
 * 
 */
public interface IPersonnage {

	public void dump();
	
	public boolean estMort();

	public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes);

	public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants);

	public void rendPdvInitiaux();

}
