import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world to display when the player successfully completes the game.
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class EndScreen extends ScrollWorld
{
    private int hours;
    private int minutes;
    private int seconds;
    private int millis;
    private PlayerDummy dummy;
    private int setupFrames;
    private int frames = 0;
    private Label panel;
    private InGameText congratulation;
    private InGameText timeSpent;
    private InGameText totalJumps;
    private InGameText totalDeaths;
    private BackButton continueButton;
    private InGameText timeLabel;
    private InGameText jumpLabel;
    private InGameText deathLabel;
    /**
     * Constructor for the EndScreen world.
     * 
     */
    public EndScreen() 
    {
        super(711,400,1,false);
        
        calculateTime();
        setupFrames = 0;
        
        dummy = new PlayerDummy();
        addObject(dummy,-20,325);
        
        panel = new Label(new GreenfootImage("panel.png"));
        
        congratulation = new InGameText("CONGRATULATIONS",Color.YELLOW,new Font("Constantia",true,false,35),false);
        timeLabel = new InGameText("TIME      :",Color.WHITE,DetailsRenderer.constantiaB25,false);
        jumpLabel = new InGameText("JUMPS    :",Color.WHITE,DetailsRenderer.constantiaB25,false);
        deathLabel = new InGameText("DEATHS  :",Color.WHITE,DetailsRenderer.constantiaB25,false);
        
        timeSpent = new InGameText(hours + ":" + minutes + ":" + seconds + "." + millis,Color.WHITE,DetailsRenderer.constantiaB25,false);
        totalJumps = new InGameText(Integer.toString(Stats.jumps), Color.WHITE,DetailsRenderer.constantiaB25,false);
        totalDeaths = new InGameText(Integer.toString(Stats.deaths),Color.WHITE,DetailsRenderer.constantiaB25,false);
        
        GreenfootImage button = new GreenfootImage("orangeButton.png");
        button.scale(150,30);
        button.setFont(new Font("Constantia",true,false,20));
        button.drawString("CONTINUE",20,button.getHeight()-7);
        continueButton = new BackButton(new StartingScreen(),button);
        
        reset();
    }

    public void act(){
        if(dummy.getX() >= getWidth()/2){
            addScoreboard();
            setupFrames++;
        }
        if(frames%2 == 0){
            moveCam(2,0);
        }
        frames++;
    }

    /**
     * Adds the score panel, as well as the scores
     */
    public void addScoreboard(){
        addObject(panel,getWidth()/2,142);
        if(setupFrames == 2){
            addObject(congratulation,525,45);
        }
        if(setupFrames == 7){
            addObject(timeLabel,260,100);
            addObject(timeSpent,360,100);
        }
        if(setupFrames == 12){
            addObject(jumpLabel,240,150);
            addObject(totalJumps,250,150);
        }
        if(setupFrames == 17){
            addObject(deathLabel,220,200);
            addObject(totalDeaths,250,200);
        }
        if(setupFrames == 22){
            addCameraFollower(continueButton,269,173);
        }
    }

    /**
     * Calculates the time taken to finish the game in hours,minutes,seconds,milliseconds
     */
    private void calculateTime(){
        long time = Stats.endTime-Stats.startTime;
        hours = (int)time/3600000;
        minutes = (int)(time - hours*3600000)/60000;
        seconds = (int)(time - hours*3600000 - minutes*60000)/1000;
        millis = (int)(time - hours*3600000 - minutes*60000 - seconds*1000);
    }

    /**
     * Resets the player's statistics
     */
    private void reset(){
        Player.speed = 5;
        Player.g = 1;
        Player.saveX = 0;
        Player.saveY = 0;
        Player.level = 0;
        Stats.jumps = 0;
        Stats.deaths = 0;
    }
}
