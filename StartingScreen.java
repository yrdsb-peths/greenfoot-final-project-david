import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that the game starts on; the title screen
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class StartingScreen extends ScrollWorld
{
    private final int totalColorChangeSteps = 250;
    private int currentStep = 0;
    
    Color color1 = new Color(20,226,176);
    Color color2 = new Color(250,247,136);
    
    Label start;
    Label help;
    Label settings;
    Label story;
    Label title;
    
    private int frames = 0;
    /**
     * Constructthe starting screen
     */
    public StartingScreen()
    {
        super(711, 400, 1,true);
        
        GreenfootImage startButton = new GreenfootImage("orangeButton.png");
        GreenfootImage helpButton = new GreenfootImage("orangeButton.png");
        GreenfootImage settingsButton = new GreenfootImage("orangeButton.png");
        GreenfootImage storyButton = new GreenfootImage("orangeButton.png");
        
        startButton.setFont(DetailsRenderer.constantiaB30);
        startButton.drawString("START",50,28);
        helpButton.setFont(DetailsRenderer.constantiaB30);
        helpButton.drawString("HELP",60,28);
        settingsButton.setFont(DetailsRenderer.constantiaB30);
        settingsButton.drawString("SETTINGS",30,28);
        storyButton.setFont(DetailsRenderer.constantiaB30);
        storyButton.drawString("STORY",50,28);
        
        start = new Label(startButton);
        help = new Label(helpButton);
        settings = new Label(settingsButton);
        story = new Label(storyButton);
        
        title = new Label("The Journey",60);
        title.setFillColor(color1);
        
        addObject(start,175,150);
        addObject(story,175,200);
        addObject(settings,175,250);
        addObject(title,200,70);
        addObject(help,175,300);
        
        Player player = new Player();
        Player.help = false;
    }

    public void act(){
        checkMouseClick();
        if(currentStep < totalColorChangeSteps){
            title.setFillColor(fade(title.getFillColor(),color2,currentStep));
        }else if(currentStep > totalColorChangeSteps){
            title.setFillColor(fade(title.getFillColor(),color1,currentStep));
        }
        if(currentStep == totalColorChangeSteps*2){
            currentStep = 0;
        }
        BGMManager.setMusic(this);
        currentStep++;
        if(frames%2 == 0){
            moveCam(1,0);
        }
        frames++;
    }

    /**
     * Slowly changes one color to another
     * 
     * @param color1 The original colour
     * @param color2 The desired colour
     * @param currentStep The current step in the process of changing the colour 
     */
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

    /**
     * Checks if the mouse clicked on any buttons. If it did, it performs the appropriate methods.
     */
    public void checkMouseClick(){
        if(Greenfoot.mouseClicked(start)){
            changeWorld(Player.levels[Player.level]);
        }
        if(Greenfoot.mouseClicked(help)){
            changeWorld(new Help());
        }
        if(Greenfoot.mouseClicked(settings)){
            changeWorld(new Settings());
        }
        if(Greenfoot.mouseClicked(story)){
            changeWorld(new Story());
        }
    }
}
