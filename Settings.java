import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends ScrollWorld
{
    private GreenfootImage panel = new GreenfootImage(300,330);
    private GreenfootImage controls;
    private GreenfootImage otherSettings;
    private Color color = new Color(233,252,20);
    private Font constantia = new Font("Constantia",true,false,40);
    private Label controlsPanel;
    private Label othersPanel;
    private Label jumpKey;
    private Label rightKey;
    private Label leftKey;
    private Scroller scroller = new Scroller();
    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {
        super(711,400,1,true);

        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(constantia);
        backButton.drawString("BACK",45,30);
        backButton.scale(100,18);
        BackButton back = new BackButton(new StartingScreen(),backButton);
        addObject(back,70,22);

        panel.setColor(new Color(200,200,200));
        panel.fill();

        controls = new GreenfootImage(panel);
        controls.setColor(color);
        controls.setFont(constantia);
        controls.drawString("Controls",70,50);
        controlsPanel = new Label(controls);
        addObject(controlsPanel,195,210);

        otherSettings  = new GreenfootImage(panel);
        otherSettings.setColor(color);
        otherSettings.setFont(constantia);
        otherSettings.drawString("Settings",75,50);
        othersPanel = new Label(otherSettings);
        addObject(othersPanel,515,210);

        Label jump = new Label("Jump",30);
        addObject(jump,110,150);

        Label right = new Label("Right",30);
        addObject(right,110,220);

        Label left = new Label("Left",30);
        addObject(left,110,290);

        jumpKey = new Label(new GreenfootImage("white.png"));
        jumpKey.setValue(Player.jump);
        jumpKey.setFontSize(20);
        addObject(jumpKey,240,150);

        rightKey = new Label(new GreenfootImage("white.png"));
        rightKey.setValue(Player.right);
        rightKey.setFontSize(20);
        addObject(rightKey,240,220);

        leftKey = new Label(new GreenfootImage("white.png"));
        leftKey.setValue(Player.left);
        leftKey.setFontSize(20);
        addObject(leftKey,240,290);

        addCameraFollower(scroller,0,0);
    }

    public void act(){
        if(Greenfoot.mouseClicked(jumpKey)){
            Player.jump = changeKey(jumpKey);
        }
        if(Greenfoot.mouseClicked(rightKey)){
            Player.right = changeKey(rightKey);
        }
        if(Greenfoot.mouseClicked(leftKey)){
            Player.left = changeKey(leftKey);
        }
        String clearGetKey = Greenfoot.getKey();
    }

    public String changeKey(Label action){
        String newKey = Greenfoot.getKey();
        while(newKey == null || newKey.equals(Player.jump) || newKey.equals(Player.right) || newKey.equals(Player.left)){
            newKey = Greenfoot.getKey();
            scroller.act();
            repaint();
        }
        action.setValue(newKey);
        action.updateImage();
        return newKey;
    }
}
