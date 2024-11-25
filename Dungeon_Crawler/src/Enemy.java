import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Enemy extends DynamicSprite {
    protected boolean isIdle = true;
    protected int cnt = 1;

    public Enemy(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    protected void patrol(){
        if(isIdle){
            int cas = (cnt / 20)%4 + 1;
            //System.out.println(cas);
            switch (cas){
                case 1:
                    isWalking = true;
                    direction = Direction.NORTH;
                    cnt = cnt + 1;
                    break;
                case 2:
                    isWalking = true;
                    direction = Direction.EAST;
                    cnt = cnt + 1;
                    break;
                case 3:
                    isWalking = true;
                    direction = Direction.SOUTH;
                    cnt = cnt + 1;
                    break;
                case 4:
                    isWalking = true;
                    direction = Direction.WEST;
                    cnt = cnt + 1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cnt);
            }


        }
    }

    public void Distance(Sprite sprite) {
        double distx = x - sprite.x;
        double disty = y - sprite.y;
        if(abs(distx) > abs(disty)) {
            if (distx < 150 && distx > 40) {
                this.isWalking = true;
                isIdle = false;
                direction = Direction.WEST;
            }
            else if(distx < -40 && distx > -150) {
                this.isWalking = true;
                isIdle = false;
                direction = Direction.EAST;
            }
            else if((disty > 150 || disty < -150) || (distx > 150 || distx < -150)) {
                this.isIdle = true;
            }
            else{
                this.isWalking = false;
            }
        }
        else {
            if (disty < 150 && disty > 40) {
                this.isWalking = true;
                isIdle = false;
                direction = Direction.NORTH;
            }
            else if(disty < -40 && disty > -150) {
                this.isWalking = true;
                isIdle = false;
                direction = Direction.SOUTH;
            }
            else if((distx > 150 || distx< -150) || (disty > 150 || disty < -150)) {
                this.isIdle = true;
            }
            else{
                this.isWalking = false;
            }
        }
        }
    }
