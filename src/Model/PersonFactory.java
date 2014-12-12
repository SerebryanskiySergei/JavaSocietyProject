package Model;

import Model.Characters.Person;
import Model.Enviroment.Habitat;
import Model.Prototypes.*;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class PersonFactory
{
    private int id;
    private WarriorPrototype _warriorPrototype;
    private TraderPrototype _traderPrototype;
    private RobberPrototype _robberPrototype;
    private PeasantPrototype _peasantPrototype;
    private CraftsmanPrototype _craftsmanPrototype;

    public Person getMan()
    {
        RandomContainer random = new RandomContainer();
        Person man = new Person(id);
        byte luck = (byte)random.next(1,6);
        try {
            if (luck == 1)
                man = _warriorPrototype.clone();
            if (luck == 2)
                man = _traderPrototype.clone();
            if (luck == 3)
                man = _robberPrototype.clone();
            if (luck == 4)
                man = _peasantPrototype.clone();
            if (luck == 5)
                man = _craftsmanPrototype.clone();
        }
        catch (CloneNotSupportedException e){
            man = new Person(luck);
        }
        man.setId(id++);
        return man;
    }
    public PersonFactory(Habitat settlement)
    {
        _warriorPrototype = new WarriorPrototype(settlement);
        _traderPrototype = new TraderPrototype(settlement);
        _robberPrototype = new RobberPrototype(settlement);
        _peasantPrototype = new PeasantPrototype(settlement);
        _craftsmanPrototype = new CraftsmanPrototype(settlement);
        id = 0;
    }
}