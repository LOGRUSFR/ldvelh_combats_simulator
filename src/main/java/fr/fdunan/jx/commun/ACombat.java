package fr.fdunan.jx.commun;

public abstract class ACombat implements ICombat
    {

    private APersonnage _premier;

    private APersonnage _second;

    private APersonnage _victorieux;

    public ACombat( APersonnage p1, APersonnage p2 )
        {
        setVictorieux(null);
        }
    
    public APersonnage getPremier()
        {
        return _premier;
        }

    public void setPremier( APersonnage _premier)
        {
        this._premier = _premier;
        }

    public APersonnage getSecond()
        {
        return _second;
        }

    public void setSecond( APersonnage _second)
        {
        this._second = _second;
        }

    public APersonnage getVictorieux()
        {
        return _victorieux;
        }

    public void setVictorieux( APersonnage _victorieux)
        {
        this._victorieux = _victorieux;
        }
    
    }
