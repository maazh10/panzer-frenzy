import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int eLocation;
    private int change;
    private boolean bigTank = false; 
    private int timer;
    private Tank tank;
    private Barrel barrel;
    private boolean remove = false;
    private int counter;
    private Shield shield;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Enemy(int loc, int type) {
        // takes the values from the parameters to get to know its location and type
        eLocation = loc;
        getImage().scale(50,50);
        change = Greenfoot.getRandomNumber(100);
        if(type == 2) {
            setImage("tankBlack_outline.png");
            getImage().scale(85,85);
            bigTank = true;
        }

    }

    public void act() 
    {
        // decreases a life from the tank if its not protected by the shield
        // or just kills the tank and ends the game if the type is the black tank
        shield = ((TankWorld)getWorld()).shield;
        if(shield.protect == false) {
            hitTank();        
        }
        // moves according to the location got from the constructor 
        move();
        // removes it itself from the edge
        remove();
    }    

    public void move() {
        if(eLocation == 1) {
            setLocation( getX(), getY()+2 );
            setRotation(180);
        }
        if(eLocation == 2) {
            setLocation( getX(), getY()-2 );
            setRotation(0);
        }
        if(eLocation == 3) {
            setLocation( getX()-2, getY() );
            setRotation(-90);
        }
        if(eLocation == 4) {
            setLocation( getX()+2, getY() );
            setRotation(90);
        }

        if(isAtEdge()) {
            remove = true;
        }
    }

    public void hitTank() {
        if(bigTank == false) {
            timer++;
            if(timer == 50) {
                if(isTouching(Tank.class)) {
                    Greenfoot.playSound("Sound Effect- Crash.mp3");
                    ((TankWorld)getWorld()).reduceLives(-1);
                    remove = true;
                }
                timer = 0;
            } 
        } else {
            if(isTouching(Tank.class) && isTouching(Barrel.class)) {
                removeTouching(Tank.class);
                removeTouching(Barrel.class);
                remove = true;
                getWorld().addObject(new Explosion(), getX(), getY()); 
            }
        }
    }

    public void remove() {
        if(remove == true) {
            getWorld().removeObject(this);
        }
    }
}
