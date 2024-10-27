package fr.dunan.jx.commun;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ACombat implements ICombat {

    private APersonnage premier;

    private APersonnage second;

    private APersonnage victorieux;

    protected ACombat() {
        setVictorieux(null);
    }
}
