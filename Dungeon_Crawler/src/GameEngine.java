import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    ArrayList<Enemy> enemyList = new ArrayList<>();
    JFrame title;

    public GameEngine(DynamicSprite hero,JFrame title) {
        this.hero = hero;
        this.title = title;

    }


    public void addToEnemyList(Enemy enemy){
        enemyList.add(enemy);
    }
    @Override
    public void update(){
        for (Enemy e : enemyList){
            e.Distance(hero);
            e.patrol();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        hero.isWalking = true;
        title.dispose();
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.isWalking =false;
    }
}