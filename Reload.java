import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reload here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reload extends Actor
{
    public Scoreboard bullets;
    /**
     * Act - do whatever the Reload wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Reload() {
        // sets the variable bullets to an instance of the Scoreboard
        bullets = new Scoreboard( "Bullets Left", 30);
        // scales the image
        getImage().scale(50,50);
    }

    public void act() 
    {
        // icreases the amount of bullets by 5 if touching tank
        increase();
    }    

    public void increase() {
        if(isTouching(Tank.class)) {
            ((TankWorld)getWorld()).increaseBullets(5);
            Greenfoot.playSound("Gun Reload sound effect.mp3");
            getWorld().removeObject(this);
        }
    }
}
