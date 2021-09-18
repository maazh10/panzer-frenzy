import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BarrelPoweUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Actor
{
    public boolean protect;
    /**
     * Act - do whatever the BarrelPoweUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Shield() {
        getImage().scale(50,60);
    }
    
    public void act() 
    {
        // keeps the tank from losing lives if this is picked
        protection();
    }
    
    public void protection() {
        if(isTouching(Tank.class)) {
            protect = true;
            Greenfoot.playSound("shield.wav");
            getWorld().removeObject(this);
        }
    } 
}
