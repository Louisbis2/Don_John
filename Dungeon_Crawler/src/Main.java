import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import static java.awt.AWTEventMulticaster.add;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame title;
    JFrame displayZoneFrame;


    RenderEngine renderEngine;
    RenderEngine renderTitle;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        title = new JFrame("Ecran titre");
        title.setSize(1339,630);
        title.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(900,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(500,300,
                ImageIO.read(new File("C:\\Users\\pepit\\IdeaProjects\\Dungeon_Crawler\\img\\heroTileSheetLowRes.png")),48,50);

        Enemy garde = new Enemy(150,100,
                ImageIO.read(new File("C:\\Users\\pepit\\IdeaProjects\\Dungeon_Crawler\\img\\heroTileSheetLowRes.png")),48,50);


        renderTitle = new RenderEngine(title);
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero,title);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer titleTimer = new Timer(50,(time)-> renderTitle.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());


        titleTimer.start();
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        gameEngine.addToEnemyList(garde);

        title.getContentPane().add(renderTitle);
        title.setVisible(true);
        displayZoneFrame.getContentPane().add(renderEngine);

        Playground level = new Playground("C:\\Users\\pepit\\IdeaProjects\\Dungeon_Crawler\\data\\level1.txt");
        Playground titleGround = new Playground("C:\\Users\\pepit\\IdeaProjects\\Dungeon_Crawler\\data\\title.txt");


        renderTitle.addToRenderList(titleGround.getSpriteList());

        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        renderEngine.addToRenderList(garde);
        physicEngine.addToMovingSpriteList(garde);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
        title.addKeyListener(gameEngine);

        while(title.isShowing()){
            displayZoneFrame.setVisible(false);
        }
        displayZoneFrame.setVisible(true);

    }

    public static void main (String[] args) throws Exception {
        // write your code here
        Main main = new Main();
    }
}