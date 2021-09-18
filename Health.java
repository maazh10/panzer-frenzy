import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health extends Actor
{
    public Scoreboard lives;
    /**
     * Act - do whatever the Health wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Health() {
        getImage().scale(50,50);
        lives = new Scoreboard( "Lives", 3);
    }
    
    public void act() 
    {
        // increases a life if touching the tank
        increase();
    }    
    
    public void increase() {
        if(isTouching(Tank.class)) {
            ((TankWorld)getWorld()).increaseLives(1);
            Greenfoot.playSound("health.wav");
            getWorld().removeObject(this);
        }
    }
}
