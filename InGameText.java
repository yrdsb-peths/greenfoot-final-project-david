import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InGameText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InGameText extends Actor
{
    private String text;
    private Color color;
    private Font font;
    private boolean fade;
    private int frames = 0;
    private GreenfootImage image;
    /**
     * Act - do whatever the InGameText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public InGameText(String text,Color color,Font font,boolean fade){
        this.text = text;
        if(!fade){
            this.color = color;
        }else{
            this.color = new Color(color.getRed(),color.getGreen(),color.getBlue(),0);
        }
        this.font = font;
        this.fade = fade;
        updateImage();
    }

    public void act(){
        if(fade){
            if(frames <= 51){
                color = new Color(color.getRed(),color.getGreen(),color.getBlue(),frames*5);
                updateImage();
            }else if(frames <= 253 && frames >= 202){
                image.clear();
                color = new Color(color.getRed(),color.getGreen(),color.getBlue(),255-(frames-202)*5);
                updateImage();
            }else if(frames > 304){
                getWorld().removeObject(this);
            }
        }
        frames++;
    }

    public void updateImage(){
        image = new GreenfootImage(font.getSize()*4*text.length()/3,font.getSize()*5/3+10);
        image.setColor(color);
        image.setFont(font);
        image.drawString(text,0,font.getSize()*4/3+10);
        setImage(image);
    }
}
