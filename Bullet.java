import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    private int dir = 0;
    private boolean remove;
    private int speed;
    private int smokeType;
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bullet(int barrelDirection, int type) {
        // sets the direction of the bullet to the direction where ever the barrel shoots
        dir = barrelDirection;
        setRotation(barrelDirection);
        
        // acts accordingly if the user has picked up the bullet powerup or not
        if(type == 1) {
            setImage("bulletGreenSilver.png");
            smokeType = 1;
            speed = 3;
        }
        if(type == 2) {
            setImage("bulletRed_outline.png");
            smokeType = 2;
            speed = 7;
        }
    }

    public void act() 
    {
        // kills the enemy if it toches them
        killEnemy();
        // moves at a designated speed
        move();
        // adds smoke while moving
        releaseSmoke();
        // removes itself from the world 
        removeIt(); 
    }    

    public void move() {
        if(dir == 180) {
            setLocation(getX(), getY()+speed);
        }

        if(dir == 0) {
            setLocation(getX(), getY()-speed);
        }

        if(dir == 270) {
            setLocation(getX()-speed, getY());
        }

        if(dir == 90) {
            setLocation(getX()+speed, getY());
        }

        if(isAtEdge()) {
            remove = true;
        }
    }

    public void killEnemy() {
        if(isTouching(Enemy.class)) {
            Greenfoot.playSound("tank shot.mp3");
            removeTouching(Enemy.class);
            remove = true;
            ((TankWorld)getWorld()).update(1);
            int random = Greenfoot.getRandomNumber(25);
            for(int i = 0; i < 25; i++) {
                getWorld().addObject( new Debris(1), getX(), getY() );
            }
            for(int i = 0; i < random; i++) {
                getWorld().addObject( new Debris(2), getX(), getY() );
            }
        }
    }

    public void releaseSmoke() {
        getWorld().addObject( new Smoke(smokeType), getX(), getY() );
    }

    public void removeIt() {
        if(remove == true) {
            getWorld().removeObject(this);
        }
    }
}
