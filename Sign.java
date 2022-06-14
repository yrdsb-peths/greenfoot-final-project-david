import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Signs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sign extends Decor
{
    private GreenfootImage blur = new GreenfootImage(711,400);
    private GreenfootImage sign = new GreenfootImage("sign text.png");
    private Font constantia = new Font("Constantia",30);
    private Decor grayed;
    private Decor readableSign;
    private InGameText text = new InGameText("Press 'Enter' to read.",Color.WHITE,constantia,false);
    private boolean reading = false;
    private int frames = 0;
    private int spamPrevention = 0;
    /**
     * Act - do whatever the Signs wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Sign(GreenfootImage sign, String text){
        super(sign);
        text = text;
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
        if(getOneIntersectingObject(Player.class) != null){
            getWorld().addObject(text,670,330);
        }else{
            getWorld().removeObject(text);
        }
        if(reading && Greenfoot.isKeyDown("enter") && frames - spamPrevention > 8){
            Player.paused = false;
            getWorld().removeObject(grayed);
            getWorld().removeObject(readableSign);
            reading = false;
            spamPrevention = frames;
        }else if(getOneIntersectingObject(Player.class) != null && Greenfoot.isKeyDown("enter") && !Player.paused && frames - spamPrevention > 8){
            getWorld().addObject(grayed,getWorld().getWidth()/2,getWorld().getHeight()/2);
            getWorld().addObject(readableSign,getWorld().getWidth()/2,getWorld().getHeight()/2);
            Player.paused = true;
            reading = true;
            spamPrevention = frames;
        }
        frames++;
    }
}
