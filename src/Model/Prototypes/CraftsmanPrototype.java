package Model.Prototypes;

import Model.Characters.Professions.CraftsmanDecorator;
import Model.Enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class CraftsmanPrototype  extends PersonPrototype implements Cloneable{
    private static CraftsmanDecorator decorator = new CraftsmanDecorator();

    public CraftsmanPrototype(Habitat setllement){
        super(setllement, decorator);
    }
}
