import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class to display text. Provides more versatility than the Label class
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class InGameText extends Actor
{
    private int frames = 0;
    
    // Information for constructing the text's appearance
    private String text;
    private Color color;
    private Font font;
    private boolean fade;
    private GreenfootImage image;
    
    /**
     * The constructor for the class.
     * 
     * @param text The text to be displayed
     * @param color The color of the text to be displayed
     * @param font The font of the text to be displayed
     * @param fade Whether or not the text will fade in and then out
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
        fade();
        frames++;
    }
    
    /**
     * If the text is made to fade, this will cause the text to fade in, stop for a while, and then fade out
     */
    public void fade(){
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
    }
    
    /**
     * Updates the text image with the appearance information 
     */
    public void updateImage(){
        image = new GreenfootImage(font.getSize()*4*text.length()/3,font.getSize()*5/3+10);
        image.setColor(color);
        image.setFont(font);
        image.drawString(text,0,font.getSize()*4/3+10);
        setImage(image);
    }
}
