import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A sign that can be read by pressing the "Enter" key when prompted.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Sign extends Decor
{
    private GreenfootImage sign = new GreenfootImage("sign text.png");
    private Font constantia = new Font("Constantia",30);
    private Decor grayed;
    private Decor readableSign;
    private InGameText text = new InGameText("Press 'Enter' to read.",Color.WHITE,constantia,false);
    private boolean reading = false;
    private int frames = 0;
    private int spamPrevention = 0;
    /**
     * Instantiates a new Sign.
     * 
     * @param sign The image of the sign
     * @param text The text to be displayed when reading
     */
    public Sign(GreenfootImage sign, String text){
        super(sign);
        text = text;
        GreenfootImage blur = new GreenfootImage(711,400);
        blur.setColor(new Color(128,128,128,75));
        blur.fill();
        grayed = new Decor(blur);
        this.sign.setFont(constantia);
        this.sign.drawString(text,30,50);
        this.sign.setFont(new Font("Constantia",20));
        this.sign.drawString("Press 'Enter' to stop reading",30,this.sign.getHeight()-20);
        readableSign = new Decor(this.sign);
    }

    public void act()
    {
        displayPrompt();
        if(reading && Greenfoot.isKeyDown("enter") && frames - spamPrevention > 8){
            stopReadingSign();
        }else if(getOneIntersectingObject(Player.class) != null && Greenfoot.isKeyDown("enter") && !Player.paused && frames - spamPrevention > 8){
            readSign();
        }
        frames++;
    }

    /**
     * Prompts the user to press "Enter" to read the sign
     */
    private void displayPrompt(){
        if(getOneIntersectingObject(Player.class) != null){
            getWorld().addObject(text,670,330);
        }else{
            getWorld().removeObject(text);
        }
    }

    /**
     * Opens up a panel to display what the sign says
     */
    private void readSign(){
        getWorld().addObject(grayed,getWorld().getWidth()/2,getWorld().getHeight()/2);
        getWorld().addObject(readableSign,getWorld().getWidth()/2,getWorld().getHeight()/2);
        Player.paused = true;
        reading = true;
        spamPrevention = frames;
    }

    /**
     * Closes the panel that displays what the sign says
     */
    private void stopReadingSign(){
        Player.paused = false;
        getWorld().removeObject(grayed);
        getWorld().removeObject(readableSign);
        reading = false;
        spamPrevention = frames;
    }
}
