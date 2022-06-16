import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sets the player's spawn to this object.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Checkpoint extends ScrollActor
{
    /**
     * Instantiates a new Checkpoint.
     */
    public Checkpoint(){
        GreenfootImage image = getImage();
        image.scale(48,58);
        setImage(image);
    }
    
    public void act(){
        if(getOneIntersectingObject(Player.class) != null){
            setCheckpointLocation();
        }
    }
    
    /**
     * Sets the player's spawn point to this Checkpoint object.
     */
    public void setCheckpointLocation(){
        Player player = (Player)getOneIntersectingObject(Player.class);
        player.movedX = player.getX() - getX();
        player.movedY = player.getY() - getY();
    }
}
