import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Smoke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smoke extends Actor
{
    private int trans;
    /**
     * Act - do whatever the Smoke wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Smoke(int img) {
        // sets transparency to 150 initially
        trans = 150;
        // changes the color of the smoke according to the bullet type
        if(img == 1) {
            setImage("smokeWhite2.png");
        }
        if(img == 2) {
            setImage("smokeYellow3.png");
        }
        // scales the image
        getImage().scale(20,20);
    }

    public void act() 
    {
        // sets transparency to "trans" and decreases trans by 5 
        animate();
    }    
    
    public void animate() {
        getImage().setTransparency(trans);
        trans = trans - 5;
        if(trans == 0) {
            getWorld().removeObject(this);
        }
    }
}
