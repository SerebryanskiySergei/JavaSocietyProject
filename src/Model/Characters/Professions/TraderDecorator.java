package Model.Characters.Professions;

import Model.Characters.Person;
import Model.Characters.Profession;
import Model.Characters.Strategies.TraderStrategy;
import Model.Enviroment.Equipment;
import Model.Enviroment.Habitat;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class TraderDecorator implements IProfessionDecorator {
    public void takeProfession(Person person,Habitat settlement)
    {
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 100);
        person.setLocation(new Point((int)(Math.random() * ((settlement.getWight()) + 1)),(int)(Math.random() * ((settlement.getHeight()) + 1))));
        person.changeStrategy(new TraderStrategy());
        person.setReview(100);
        person.setSpeed(70);
        person.setProfession(Profession.Trader);
    }
}
