import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    public int trans = 255;
    private Shield sh;
    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tank() {
        // sets transparency to a variable
        getImage().setTransparency(trans);
    }

    public void act() 
    {
        // moves up down left right according to the key presses and also changes direction
        move();
        // changes image according to whether or not guarded by the shield
        appearance();
    }    

    public void move() {
        if(Greenfoot.isKeyDown("right")) {
            setLocation(getX()+2, getY());
            setRotation(90);
        }
        if(Greenfoot.isKeyDown("left")) {
            setLocation(getX()-2, getY());
            setRotation(-90);
        }
        if(Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY()-2);
            setRotation(0);
        }
        if(Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY()+2);
            setRotation(180);
        }
    }
    
    public void appearance() {
        sh = ((TankWorld)getWorld()).shield;
        if(sh.protect == true) {
            setImage("shieldtank.png");
        } else {
            setImage("tankGreen_outline.png");
        }
        getImage().setTransparency(trans);
    }
}
