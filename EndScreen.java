import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends ScrollWorld
{
    private int hours;
    private int minutes;
    private int seconds;
    private int millis;
    private PlayerDummy dummy;
    private int frames;
    private Label panel;
    private InGameText congratulation;
    private InGameText timeSpent;
    private InGameText totalJumps;
    private InGameText totalDeaths;
    private Font constantia = new Font("Constantia",true,false,25);
    private BackButton continueButton;
    private InGameText timeLabel;
    private InGameText jumpLabel;
    private InGameText deathLabel;
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {
        super(711,400,1,false);
        calculateTime();
        dummy = new PlayerDummy();
        addObject(dummy,-20,325);
        addObject(new Scroller(2),-1,-1);
        frames = 0;
        panel = new Label(new GreenfootImage("panel.png"));
        congratulation = new InGameText("CONGRATULATIONS",Color.YELLOW,new Font("constantia",true,false,35),false);
        timeLabel = new InGameText("TIME      :",Color.WHITE,constantia,false);
        jumpLabel = new InGameText("JUMPS    :",Color.WHITE,constantia,false);
        deathLabel = new InGameText("DEATHS  :",Color.WHITE,constantia,false);
        timeSpent = new InGameText(hours + ":" + minutes + ":" + seconds + "." + millis,Color.WHITE,constantia,false);
        totalJumps = new InGameText(Integer.toString(Stats.jumps), Color.WHITE,constantia,false);
        totalDeaths = new InGameText(Integer.toString(Stats.deaths),Color.WHITE,constantia,false);
        GreenfootImage button = new GreenfootImage("orangeButton.png");
        button.scale(150,30);
        button.setFont(new Font("Constantia",true,false,20));
        button.drawString("CONTINUE",20,button.getHeight()-7);
        continueButton = new BackButton(new StartingScreen(),button);
        reset();
    }

    public void act(){
        if(dummy.getX() >= getWidth()/2){
            addObject(panel,getWidth()/2,142);
            if(frames == 2){
                addObject(congratulation,525,45);
            }
            if(frames == 7){
                addObject(timeLabel,260,100);
                addObject(timeSpent,360,100);
            }
            if(frames == 12){
                addObject(jumpLabel,240,150);
                addObject(totalJumps,250,150);
            }
            if(frames == 17){
                addObject(deathLabel,220,200);
                addObject(totalDeaths,250,200);
            }
            if(frames == 22){
                addCameraFollower(continueButton,150+119,50+123);
            }
            frames++;
        }
    }

    private void calculateTime(){
        long time = Stats.endTime-Stats.startTime;
        hours = (int)time/3600000;
        minutes = (int)(time - hours*3600000)/60000;
        seconds = (int)(time - hours*3600000 - minutes*60000)/1000;
        millis = (int)(time - hours*3600000 - minutes*60000 - seconds*1000);
    }
    
    private void reset(){
        Player.speed = 5;
        Player.g = 1;
        Player.saveX = 0;
        Player.saveY = 0;
        Player.level = 0;
        Levels[] levels = {new Level1(),new Level2(),new Level3(),new Level4(),new Level5(),new Level6()};
        for(int i = 0; i < 6; i++){
            Player.levels[i] = levels[i];
        }
        Stats.jumps = 0;
        Stats.deaths = 0;
    }
}
