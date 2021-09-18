import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Scoreboard extends Actor
{
    //Properties ...
    public int score;
    private String text;
    private int counter;
    private Color bl;
    
    public Scoreboard(String name, int starter)
    {
        // creates a canvas, sets font color to a gray shade, sets font family to Berlin Sans
        // and sets the image to the inpute fields ie. the name of the string and the 
        // starting value
        GreenfootImage img = new GreenfootImage(180,40);

        bl = new Color(50, 50, 50);
        img.setColor(bl);

        img.setFont( new Font( "Berlin Sans FB Demi",true,false,25) );
        img.drawString(name + ": " + starter,5,35);
        setImage(img);
        text = name;
        score = starter;
    }

    // changes the number of kills and changes to you win and plays a sound if reaches 10
    public void changeScore(int number) 
    {
        score = score + number;
        GreenfootImage img = getImage();
        img.clear();
        if(score < 10) {
            img.drawString(text + ": " + score,5,35);  
        } else {
            img.drawString("You Win!",5,35);
            Greenfoot.playSound("win.wav");
        }
    }   

    // changes the number of bullets left and changes to you no more bullets and plays a sound if reaches 0
    public void changeBullets(int number) 
    {
        score = score + number;
        GreenfootImage img = getImage();
        img.clear();
        if(score > 0) {
            img.drawString(text + ": " + score,5,35);  
        } else {
            img.drawString("No More Bullets!",5,35);
            Greenfoot.playSound("lose.wav");
        }
    }

    // changes the number of lives and changes to you died and plays a sound if reaches 0
    public void changeLives(int number) 
    {
        score = score + number;
        GreenfootImage img = getImage();
        img.clear();
        if(score > 0) {
            img.drawString(text + ": " + score,5,35);  
        } else {
            img.drawString("You Died!",5,35);
            Greenfoot.playSound("lose.wav");
        }
    }
}
