package Model.Characters.Professions;

import Model.Characters.Person;
import Model.Characters.Profession;
import Model.Characters.Strategies.PeasantStrategy;
import Model.Enviroment.Equipment;
import Model.Enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class PeasantDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 50);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.setReview(100);
        person.setSpeed(70);
        person.setStrategy(new PeasantStrategy());
        person.setProfession(Profession.Peasant);
    }
}
