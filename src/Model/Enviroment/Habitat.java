package Model.Enviroment;

import Model.Characters.Person;
import Model.Characters.Profession;
import Model.RandomContainer;

import java.awt.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Среда обитания.
 */
public class Habitat {

    private Hashtable <Integer,Profession> settlersProfessions;
    private Hashtable<Point,Integer> locationsOfSettlers;
    public TraderPlace[] MarketPlace = new TraderPlace[10];
    private int wight;
    private int height;
    public Rectangle Forest;
    public Rectangle Farm;
    public Rectangle CraftHouse;
    public Rectangle Castle;

    public Habitat(Dictionary<Integer,Person> settlers, int wight, int height )
    {
        this.wight = wight;
        this.height = height;
        Forest = new Rectangle(0, 0, 170, 170);             //
        Farm = new Rectangle(200, 30, 100, 100);            //      Убрать константы.
        CraftHouse = new Rectangle(300, 500, 100, 100);     //
        Castle = new Rectangle(wight - 324,0,324,285);       //
        for (int i = 0; i < 10; i++)
            MarketPlace[i] = new TraderPlace(new Rectangle(Castle.x+i*30,(int)(Castle.y+Castle.getSize().getHeight()+10),30,30));
        setLocationsOfSettlers(new Hashtable<Point, Integer>());
        setSettlersProfessions(new Hashtable<Integer, Profession>());

    }
    public void refreshLocation(Dictionary<Integer,Person> settlers)
    {
        getLocationsOfSettlers().clear();
        Enumeration<Integer> enumKey = settlers.keys();
        while(enumKey.hasMoreElements()) {
            Integer key = enumKey.nextElement();
            Person person = settlers.get(key);
            if (!getLocationsOfSettlers().isEmpty()&& getLocationsOfSettlers().containsKey(person.getLocation()))
                getLocationsOfSettlers().remove(person.getLocation());
            getLocationsOfSettlers().put(person.getLocation(), person.getId());
        }

    }

    public Hashtable<Integer, Profession> getSettlersProfessions() {
        return settlersProfessions;
    }

    public void setSettlersProfessions(Hashtable<Integer, Profession> settlersProfessions) {
        this.settlersProfessions = settlersProfessions;
    }

    public Hashtable<Point, Integer> getLocationsOfSettlers() {
        return locationsOfSettlers;
    }

    public void setLocationsOfSettlers(Hashtable<Point, Integer> locationsOfSettlers) {
        this.locationsOfSettlers = locationsOfSettlers;
    }
    public Point findTrader(){
        RandomContainer random = new RandomContainer();
        TraderPlace p = MarketPlace[random.next(0,8)];
        while (p.isEmpty)
            p = MarketPlace[random.next(0,8)];
        Point destination = new Point(p.place.x+p.place.getSize().width/2,p.place.y+p.place.getSize().height);
        return destination;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public class TraderPlace
    {
        public Rectangle place;
        public boolean isEmpty;

        public TraderPlace(Rectangle place)
        {
            this.place = place;
            isEmpty = true;
        }
    }
}
