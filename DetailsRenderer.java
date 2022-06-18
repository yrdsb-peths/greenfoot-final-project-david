import greenfoot.GreenfootImage;
import greenfoot.Font;

/**
 * A class used solely to store commonly used images and fonts. 
 * Saves memory space by removing the need to repeatedly instantiate the same GreenfootImages and fonts
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class DetailsRenderer  
{
    public static GreenfootImage grassBlock = new GreenfootImage("grass block.png");
    public static GreenfootImage arrowSign = new GreenfootImage("arrow sign.png");
    public static GreenfootImage cobblestoneBlock = new GreenfootImage("cobblestone.png");
    public static GreenfootImage dirtBlock = new GreenfootImage("dirt.png");
    public static GreenfootImage iceBlock = new GreenfootImage("ice.png");
    public static GreenfootImage magmaBlock = new GreenfootImage("magma block.png");
    public static GreenfootImage mossBlock = new GreenfootImage("moss.png");
    public static GreenfootImage mossStoneBlock = new GreenfootImage("moss stone.png");
    public static GreenfootImage slimeBlock = new GreenfootImage("slime.png");
    public static GreenfootImage pause = new GreenfootImage("pause.png");
    public static GreenfootImage signText = new GreenfootImage("sign text.png");
    public static GreenfootImage barrier = new GreenfootImage(50,1000);
    public static GreenfootImage redArrow = new GreenfootImage("arrow.png");
    
    public static GreenfootImage[] breaking = {new GreenfootImage("destroy_stage_0.png"),new GreenfootImage("destroy_stage_1.png"),
        new GreenfootImage("destroy_stage_2.png"),new GreenfootImage("destroy_stage_3.png"),new GreenfootImage("destroy_stage_4.png"),
        new GreenfootImage("destroy_stage_5.png"),new GreenfootImage("destroy_stage_6.png"),new GreenfootImage("destroy_stage_7.png"),
        new GreenfootImage("destroy_stage_8.png"),new GreenfootImage("destroy_stage_9.png")};
        
    public static GreenfootImage[] stones = {new GreenfootImage("stone0.png"),new GreenfootImage("stone1.png"),
        new GreenfootImage("stone2.png"),new GreenfootImage("stone3.png"),new GreenfootImage("stone4.png"),new GreenfootImage("stone5.png"),
        new GreenfootImage("stone6.png"),new GreenfootImage("stone7.png")};
    
    public static Font constantiaB40 = new Font("Constantia",true,false,40);
    public static Font constantiaB30 = new Font("Constantia",true,false,30);
    public static Font constantiaB25 = new Font("Constantia",true,false,25);
}
