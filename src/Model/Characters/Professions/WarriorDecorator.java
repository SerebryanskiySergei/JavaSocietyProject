package Model.Characters.Professions;

import Model.Characters.Person;
import Model.Characters.Profession;
import Model.Characters.Strategies.WarriorStrategy;
import Model.Enviroment.Equipment;
import Model.Enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class WarriorDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setEqip(Equipment.StandartEquipment.Weapons.Sword, Equipment.StandartEquipment.Armors.MediumArmor, 30);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.changeStrategy(new WarriorStrategy());
        person.setReview(90);
        person.setSpeed(50);
        person.setProfession(Profession.Warrior);
    }
}
