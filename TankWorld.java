import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TankWorld extends World
{
    private String scene;
    private int sceneNo;
    private int rnd;
    private int freq;
    public int enemyType = 1;
    private int rndEnemy;
    private Scoreboard kills;
    public Scoreboard lives;
    private Scoreboard bullets;
    private Health health;
    private Reload reload;
    private int healthFreq;
    private int r;
    private int count;
    private int reloadFreq;
    private int r1;
    private int count1;
    private BulletPowerUp power;
    private int winCounter;
    private int bCount;
    private Tank tank;
    private Barrel barrel;
    public Shield shield;
    private int shieldLength;
    private int shieldCount;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public TankWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        
        // sets scene to a random background each time
        sceneNo = Greenfoot.getRandomNumber(2);
        if(sceneNo == 0) {
            scene = "dirt.png"; 
        } else if(sceneNo == 1) {
            scene = "sand.png"; 
        }
        setBackground(scene);

        setPaintOrder( Scoreboard.class, Barrel.class, Bullet.class, Smoke.class, Tank.class);
        
        // adds tank barrel and all 3 scoreboards at their locations
        tank = new Tank();
        barrel = new Barrel();
        addObject( tank, getWidth()/2, getHeight()/2);
        addObject( barrel , getWidth()/2, getHeight()/2);

        health = new Health();
        reload = new Reload();

        kills = new Scoreboard( "Kills", 0);
        addObject( kills, 100, 25);

        lives = health.lives;
        addObject( lives, 100, 60);

        bullets = reload.bullets;
        addObject( bullets, 100, 95);

        shield = new Shield();
    }

    public void act() {
        // adds different types of enemies at different location according to a frequency
        addEnemies();
        // adds healths and reloads to the world if the user is low on any of those
        if(count < 6) {
            addHealth();
        }
        if(count1 < 6) {
            addReload();
        }
        // adds shields to the world at random times
        addShield();
        // protects the tank from losing lives
        shieldProtection();
        // stops the game after a counter when the user wins
        win();
        // stops the game after a counter when the user dies
        die();
        // stops the game after a counter when the user is out of bullets
        noBullets();
    }

    public void addEnemies() {
        rnd = Greenfoot.getRandomNumber(100);
        rndEnemy = Greenfoot.getRandomNumber(100);
        if(rndEnemy > 10) {
            enemyType = 1;
        } else {
            enemyType = 2;
        }
        freq++;
        if(freq > 60) {
            if(rnd < 10) {
                addObject( new Enemy(1,enemyType), Greenfoot.getRandomNumber(getWidth()), 0);
            }
            if(rnd > 10 && rnd < 20) {
                addObject( new Enemy(2,enemyType), Greenfoot.getRandomNumber(getWidth()), getHeight() );
            }
            if(rnd < 20 && rnd > 30) {
                addObject( new Enemy(3,enemyType), getWidth(), Greenfoot.getRandomNumber(getHeight()));
            }
            if(rnd > 30 && rnd < 40) {
                addObject( new Enemy(4,enemyType), 0, Greenfoot.getRandomNumber(getHeight()));
            }
            freq = 0;
        }
    }

    public void update(int amount) {
        kills.changeScore(amount);
    }

    public void reduceLives(int amount) {
        lives.changeLives(amount);
    }

    public void drainBullets(int amount) {
        bullets.changeBullets(amount);
    }

    public void increaseLives(int amount) {
        lives.changeLives(amount);
    }

    public void increaseBullets(int amount) {
        bullets.changeLives(amount);
    }

    public void win () {
        if(kills.score >= 10) {
            winCounter++;
            if(winCounter == 120) {
                Greenfoot.stop();
            }
        }
    }
    
    public void noBullets() {
        if(bullets.score <= 0) {
            bCount++;
            if(bCount == 120) {
                Greenfoot.stop();
            }
        }
    }

    public void die() {
        if(lives.score == 0) {
            tank.trans = tank.trans - 3;
            removeObject(barrel);
            if(tank.trans == 0) {
                removeObject(tank);
                Greenfoot.stop();
            }
        }
    }

    public void addHealth() {
        if(lives.score < 2) {
            healthFreq++;
            r = Greenfoot.getRandomNumber(10)+50;
            if(healthFreq == r) {
                addObject( health, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
            }
            if(healthFreq == 300) {
                removeObject(health);
                healthFreq = 0;
                count++;
            }
        }
    }

    public void addReload() {
        if(bullets.score < 10) {
            reloadFreq++;
            r1 = Greenfoot.getRandomNumber(150)+50;
            if(reloadFreq == r1) {
                addObject( reload, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
            }
            if(reloadFreq == 300) {
                removeObject(reload);
                reloadFreq = 0;
                count1++;
            }
        }
    }

    public void addShield() {
        shieldCount++;
        if(Greenfoot.getRandomNumber(900) < 1) {
            addObject( shield, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        if(shieldCount == 500) {
            removeObject(shield);
            shieldCount = 0;
        }
    }

    public void shieldProtection() {
        if(shield.protect == true) {
            shieldLength++;
            if(shieldLength == 800) {
                shield.protect = false;
                shieldLength = 0;
            }
        }
    }
}
