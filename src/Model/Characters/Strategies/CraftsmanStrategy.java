package Model.Characters.Strategies;

import Model.Characters.*;
import Model.Characters.Decisions.ChangeProfession;
import Model.Characters.Decisions.Decision;
import Model.Characters.Decisions.Run;
import Model.Enviroment.Habitat;
import Model.Enviroment.PersonalEnvironment;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class CraftsmanStrategy implements IStrategy {


    public Decision takeDecision(IPersonToStrategy person, PersonalEnvironment personEnvir, Habitat settlement)
    {
        if (person.getStatus() == PersonState.Died)
            return null;
        if (person.getEquipment().howMoneyInCash() < 10){
            ChangeProfession decision = new ChangeProfession();
            decision.newState = PersonState.LastActionCompleted;
            decision.newProfession = Profession.Warrior;
            return decision;
        }
        if (InDangerous(person) && person.getStatus()!=PersonState.Fighting)
        {
            person.changeStrategy(new SafeStrategy());
            return person.getStrategy().takeDecision(person, personEnvir, settlement);
        }
        switch (person.getStatus())
        {
            case LastActionCompleted:
                if (CheckEnviroment(person, personEnvir) == null)
                    return Actions.goingForInstruments(settlement, personEnvir);
                else
                    return CheckEnviroment(person, personEnvir);
            case Moving:
                if (CheckEnviroment(person, personEnvir) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, personEnvir);
            case Fighting:
                return Actions.attacking(person.getOpponent());
            case Ready:
                if (CheckEnviroment(person, personEnvir) == null)
                {
                    switch (person.getLastDecision().nextAction)
                    {
                        case MoveToCrafthouse:
                            return Actions.goingToCrafthouse(settlement, personEnvir);
                        case Craft:
                            return Actions.crafting(settlement, personEnvir);
                        case MoveToTrader:
                            return Actions.goingToTrader(settlement, personEnvir);
                        case Sell:
                            return Actions.sell(settlement, personEnvir);
                        case BuyInstruments:
                            return Actions.buyingInstruments(settlement, personEnvir);
                        case Free:
                            return Actions.free();

                    }
                    return Actions.free();
                }
                else
                    return CheckEnviroment(person, personEnvir);
            case Run:
                if (CheckEnviroment(person,personEnvir)!=null)
                    return CheckEnviroment(person, personEnvir);
                else
                    return Actions.free();
            case Died:
                break;
            case Working:
                if (CheckEnviroment(person, personEnvir) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, personEnvir);

        }
        return Actions.free();
    }
    /// <summary>
    /// Проверка на опсность для жизни.
    /// В случае опасности стратегия переключается на безопасную.
    /// </summary>
    /// <param name="person">Персонаж.</param>
    /// <returns>В опасности или нет.</returns>
    private static boolean InDangerous(IPersonToStrategy person)
    {
        if (person.getHealth() < 25)
            return true;
        else
            return false;
    }
    /// <summary>
    /// Проверка специально для ремесленника на присутствие разбойников в поле зрения.
    /// </summary>
    /// <param name="person">Персонаж.</param>
    /// <param name="pEnvir">Персонажи  области видимости.</param>
    private static Run CheckEnviroment(IPersonToStrategy person, PersonalEnvironment pEnvir)
    {
        for (IPersonToStrategy p : pEnvir.NearestCharacters)
        {
            if (p.getProfession() == Profession.Robber)
            {
                Run run = new Run();
                run.Opponent = (Person) p;
                person.setOpponent((Person)p);
                run.newState = PersonState.Run;
                run.nextAction = ActionType.Attack;
                return run;
            }
        }
        return null;
    }

}

