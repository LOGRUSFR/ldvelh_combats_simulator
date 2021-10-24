package fr.dunan.jx.commun;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public abstract class APersonnage implements java.io.Serializable, IPersonnage {
	/**
	 *
	 */
	private static final long serialVersionUID = 2;

	private String _nom;

	public APersonnage() {
	}

	public APersonnage(String nom) {
		this.setNom(nom);
	}

	/**
	 * @return the _nom
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * @param _nom the _nom to set
	 */
	public void setNom(String _nom) {
		this._nom = _nom;
	}

	public void exportXml()
    {
	try {
	      JAXBContext context = JAXBContext.newInstance(APersonnage.class);
	      Marshaller m = context.createMarshaller();
	      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	      m.marshal(this, System.out);
	    } catch (JAXBException ex) {
	      ex.printStackTrace();
	    }
	  }
}
