import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player can change the volume and key binds in this world
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class Settings extends ScrollWorld
{
    private GreenfootImage panel = new GreenfootImage(300,330);
    private GreenfootImage controls;
    private GreenfootImage otherSettings;
    private Color color = new Color(233,252,20);
    private Label controlsPanel;
    private Label othersPanel;
    private Label jumpKey;
    private Label rightKey;
    private Label leftKey;
    private Label dashKey;
    private Label volumeValue;
    private int newVolume = -1;
    private BackButton back;
    private int frames = 0;
    /**
     * Creates an instance of this world that sends the player to the starting screen
     * 
     */

    public Settings(){
        this(null);
    }

    /**
     * Creates an instance of this world that sends the player to the indicated level
     * 
     * @param prevWorld The level that this world was accessed from
     */
    public Settings(Levels prevWorld)
    {
        super(711,400,1,true);

        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(DetailsRenderer.constantiaB40);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        if(prevWorld != null){
            back = new BackButton(prevWorld,backButton);
        }else{
            back = new BackButton(new StartingScreen(),backButton);
        }
        addCameraFollower(back,70-getWidth()/2,22-getHeight()/2);

        panel.setColor(new Color(200,200,200));
        panel.fill();

        controls = new GreenfootImage(panel);
        controls.setColor(color);
        controls.setFont(DetailsRenderer.constantiaB40);
        controls.drawString("Controls",70,50);
        controlsPanel = new Label(controls);
        addObject(controlsPanel,195,210);

        otherSettings  = new GreenfootImage(panel);
        otherSettings.setColor(color);
        otherSettings.setFont(DetailsRenderer.constantiaB40);
        otherSettings.drawString("Settings",75,50);
        othersPanel = new Label(otherSettings);
        addObject(othersPanel,515,210);

        Label jump = new Label("Jump",30);
        addObject(jump,110,140);

        Label right = new Label("Right",30);
        addObject(right,110,200);

        Label left = new Label("Left",30);
        addObject(left,110,260);

        Label dash = new Label("Dash",30);
        addObject(dash,110,320);

        Label volume = new Label("Volume",30);
        addObject(volume,450,200);

        jumpKey = new Label(new GreenfootImage("white.png"));
        jumpKey.setValue(Player.jump);
        jumpKey.setFontSize(20);
        addObject(jumpKey,240,140);

        rightKey = new Label(new GreenfootImage("white.png"));
        rightKey.setValue(Player.right);
        rightKey.setFontSize(20);
        addObject(rightKey,240,200);

        leftKey = new Label(new GreenfootImage("white.png"));
        leftKey.setValue(Player.left);
        leftKey.setFontSize(20);
        addObject(leftKey,240,260);

        dashKey = new Label(new GreenfootImage("white.png"));
        dashKey.setValue(Player.dash);
        dashKey.setFontSize(20);
        addObject(dashKey,240,320);

        volumeValue = new Label(new GreenfootImage("white.png"));
        volumeValue.setValue(BGMManager.volume);
        volumeValue.setFontSize(20);
        addObject(volumeValue,580,200);
    }

    public void act(){
        checkClickedKey();
        if(Greenfoot.mouseClicked(volumeValue)){
            setVolume();
            BGMManager.volume = newVolume;
            volumeValue.setValue(newVolume);
            BGMManager.setVolume(newVolume);
        }
        String clearGetKey = Greenfoot.getKey();
        scroll();
    }

    public void setVolume(){
        try{
            newVolume = Integer.parseInt(Greenfoot.ask("Enter the new volume: "));
        }catch(NumberFormatException e){
            // To prevent strings with chars from causing errors with Integer.parseInt();
        }
        while(newVolume < 0 || newVolume > 100 || newVolume == -1){
            try{
                newVolume = Integer.parseInt(Greenfoot.ask("Invalid value. Please reenter the desired volume (1-100): "));
            }catch(NumberFormatException e){
                // To prevent strings with chars from causing errors with Integer.parseInt();
            }
            scroll();
            repaint();
        }
    }

    public void checkClickedKey(){
        if(Greenfoot.mouseClicked(jumpKey)){
            Player.jump = changeKey(jumpKey);
        }
        if(Greenfoot.mouseClicked(rightKey)){
            Player.right = changeKey(rightKey);
        }
        if(Greenfoot.mouseClicked(leftKey)){
            Player.left = changeKey(leftKey);
        }
        if(Greenfoot.mouseClicked(dashKey)){
            Player.dash = changeKey(dashKey);
        }
    }

    /**
     * Scrolls the world by 1 pixel right
     */
    public void scroll(){
        if(frames%2 == 0){
            moveCam(1,0);
        }
        frames++;
    }

    /**
     * Changes the key bind of the given action
     * 
     * @param action Which movement's keybind to change
     */
    public String changeKey(Label action){
        String newKey = Greenfoot.getKey();
        while(newKey == null || newKey.equals(Player.jump) || newKey.equals(Player.right) || 
        newKey.equals(Player.left) || newKey.equals(Player.dash) || newKey.equals("Enter")){
            newKey = Greenfoot.getKey();
            scroll();
            repaint();
        }
        action.setValue(newKey);
        return newKey;
    }
}
