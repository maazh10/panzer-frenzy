import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PoweUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletPowerUp extends Actor
{
    public boolean fire = false;
    /**
     * Act - do whatever the PoweUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BulletPowerUp() {
        // scales the image
        getImage().scale(100,40);
    }
    
    public void act() 
    {
        // changes a boolean to true to tell other classes to change the bullet type to fire
        fireBullet();
    }
    
    public void fireBullet() {
        if(isTouching(Tank.class)) {
            fire = true;
            Greenfoot.playSound("power-up.wav");
            getWorld().removeObject(this);
        }
    }
}
