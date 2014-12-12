package Model.Characters.Professions;

import Model.Characters.Person;
import Model.Characters.Profession;
import Model.Characters.Strategies.CraftsmanStrategy;
import Model.Enviroment.Equipment;
import Model.Enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class CraftsmanDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setSpeed(50);
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.UsualArmor, 75);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.setReview(100);
        person.setStrategy(new CraftsmanStrategy());
        person.setProfession(Profession.Craftsman);
    }
}
