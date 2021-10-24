package fr.dunan.jx.commun;

import lombok.Getter;
import lombok.Setter;

public abstract class ACombat implements ICombat {

    @Getter
    @Setter
    private APersonnage premier;

    @Getter
    @Setter
    private APersonnage second;

    @Getter
    @Setter
    private APersonnage victorieux;

    protected ACombat() {
        setVictorieux(null);
    }

}
