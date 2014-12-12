package Model.Prototypes;

import Model.Characters.Person;
import Model.Characters.Professions.IProfessionDecorator;
import Model.Enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public abstract class PersonPrototype implements Cloneable {

    protected Person prototype;
    private IProfessionDecorator decorator;
    private Habitat settlemtnt;
    protected static int id = 0;

    protected PersonPrototype(Habitat setllement, IProfessionDecorator decorator)
    {
        this.prototype = new Person(0);
        this.decorator = decorator;
        this.settlemtnt = setllement;
    }

    public Person clone() throws CloneNotSupportedException
    {
        Person newMan = this.prototype.clone();
        decorator.takeProfession(newMan, settlemtnt);
        return newMan;
    }
}

