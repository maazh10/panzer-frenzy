import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrel extends Actor
{
    private boolean remove;
    private int dir = 0;
    private int bulletCounter = 15;
    private BulletPowerUp power;
    private int typeB;
    private int powerLength;
    private int chance;
    private int duration;
    private String fireSound;
    
    /**
     * Act - do whatever the Barrel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Barrel() {
        power = new BulletPowerUp();
    }

    public void act() 
    {
        // removes itself from the world subject to conditions
        remove();
        // moves along with the tank
        move();
        // shoots a bullet when space is pressed subject to the type of the bullet
        shoot();
        // adds bullet power ups at random spots at random times
        addBulletPowerUps();
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

    public void shoot() {
        if(power.fire == true) {
            typeB = 2;
            fireSound = "shot.mp3";
            powerLength++;
            if(powerLength == 1000) {
                power.fire = false; 
                powerLength = 0;
            }
        }
        else {
            typeB = 1;
            fireSound = "gunfire.wav";
        }
        bulletCounter++;
        if(Greenfoot.isKeyDown("Space")) {
            if(bulletCounter >= 13) {
                getWorld().addObject( new Bullet(getRotation(), typeB), getX(), getY() );
                Greenfoot.playSound(fireSound);
                ((TankWorld)getWorld()).drainBullets(-1);
            }
            bulletCounter = 0;
        }
    } 

    public void addBulletPowerUps() {
        chance = Greenfoot.getRandomNumber(600);
        duration++;
        if(chance < 1) {
            getWorld().addObject( power, Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600));
        }
        if(duration == 600) {
            getWorld().removeObject(power);
            duration = 0;
        }
    }
    
    public void remove() {
        if(remove == true) {
            getWorld().removeObject(this);
        }
    }
}

