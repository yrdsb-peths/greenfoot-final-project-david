import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingScreen extends ScrollWorld
{
    private final int totalColorChangeSteps = 250;
    private int currentStep = 0;
    Color color1 = new Color(20,226,176);
    Color color2 = new Color(250,247,136);
    Font constantia = new Font("Constantia",true,false,30);
    GreenfootImage startButton = new GreenfootImage("orangeButton.png");
    GreenfootImage helpButton = new GreenfootImage("orangeButton.png");
    GreenfootImage settingsButton = new GreenfootImage("orangeButton.png");
    GreenfootImage storyButton = new GreenfootImage("orangeButton.png");
    public static Levels[] levels = new Levels[2];
    Label start;
    Label help;
    Label settings;
    Label story;
    Label title;
    /**
     * Constructor for objects of class StartingScreen.
     * 
     */
    public StartingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(711, 400, 1,true);
        startButton.setFont(constantia);
        startButton.drawString("START",50,28);
        helpButton.setFont(constantia);
        helpButton.drawString("HELP",50,28);
        settingsButton.setFont(constantia);
        settingsButton.drawString("SETTINGS",30,28);
        storyButton.setFont(constantia);
        storyButton.drawString("STORY",50,28);
        start = new Label(startButton);
        help = new Label(helpButton);
        settings = new Label(settingsButton);
        story = new Label(storyButton);
        title = new Label("Traveller's Journey",60);
        title.setFillColor(color1);
        addObject(start,175,150);
        addObject(story,175,200);
        addObject(settings,175,250);
        addObject(title,260,70);
        addObject(help,175,300);
        addCameraFollower(new Scroller(),0,0);
        
        levels[0] = new Level1();
        levels[1] = new Level2();
    }

    public void act(){
        MouseInfo m = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(start)){
            changeWorld(levels[Player.level]);
        }
        if(Greenfoot.mouseClicked(help)){
            changeWorld(new Help());
        }
        if(Greenfoot.mouseClicked(settings)){
            changeWorld(new Settings(this));
        }
        if(Greenfoot.mouseClicked(story)){
            changeWorld(new Story());
        }
        if(currentStep < totalColorChangeSteps){
            title.setFillColor(fade(title.getFillColor(),color2,currentStep));
        }else if(currentStep > totalColorChangeSteps){
            title.setFillColor(fade(title.getFillColor(),color1,currentStep));
        }
        if(currentStep == totalColorChangeSteps*2){
            currentStep = 0;
        }
        currentStep++;
    }

    private Color fade(Color color1, Color color2,int currentStep){
        int dRed = color2.getRed() - color1.getRed();
        int dGreen = color2.getGreen() - color1.getGreen();
        int dBlue = color2.getBlue() -color1.getBlue();
        Color c = color1;
        if (dRed != 0 || dGreen != 0 || dBlue != 0) {
                c = new Color(
                        color1.getRed() + ((dRed * (currentStep%totalColorChangeSteps)) / totalColorChangeSteps),
                        color1.getGreen() + ((dGreen * (currentStep%totalColorChangeSteps)) / totalColorChangeSteps),
                        color1.getBlue() + ((dBlue * (currentStep%totalColorChangeSteps)) / totalColorChangeSteps));
        }
        return c;
    }

}
