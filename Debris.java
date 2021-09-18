import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Debris here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Debris extends Actor
{
    private int speed;

    /**
     * Act - do whatever the Debris wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Debris(int color) {
        // sets debris to different color images according to parameter values
        if(color == 1) {
            setImage("Debris.png");
        }
        if(color == 2) {
            setImage("DebrisCopy.png");
        }
        // sets width height and rotation to random values each time 
        int width = Greenfoot.getRandomNumber(10)+1;
        int height = Greenfoot.getRandomNumber(10)+1;
        int rotation = Greenfoot.getRandomNumber(360);

        getImage().scale(width,height);
        turn(rotation);
        
        // sets speed to a different value each time
        speed = Greenfoot.getRandomNumber(3)+5;

    }

    public void act() 
    {
        // moves according to the speed variable and removes at the edge
        move();
    }

    public void move() {
        move(speed);
        if(isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
