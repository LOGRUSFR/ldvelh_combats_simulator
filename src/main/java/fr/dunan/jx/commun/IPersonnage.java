/**
 * 
 */
package fr.dunan.jx.commun;

/**
 * @author dunan
 * 
 */
public interface IPersonnage {

	public void dump();
	
	public boolean estMort();

	public void restaurePdvCourantsParIncrement(int nombrePdvRegagnes);

	public void restaurePdvCourantsParTotal(int nouveauTotalPdvCourants);

	public void rendPdvInitiaux();

}
