/**
 * Created by Manu on 03/04/16.
 */

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Npc extends Entity implements Serializable{

    public String orientation = "EAST";
    public Float HP;
    public String ai;
    public Map<String, BufferedImage> spriteMap = new HashMap<>();



    public Npc(int setID,int setxPos, int setyPos, float setHP, Color setColor, String setAi){

        super(setID,setxPos,setyPos,setColor);
        this.HP = setHP;
        this.ai = setAi;

        if (this.ai.equals("Sheep")){

            BufferedImage northSheep;
            BufferedImage southSheep;
            BufferedImage eastSheep;
            BufferedImage westSheep;


            try {
                northSheep = ImageIO.read(new File("Data/GFX/NorthSheep.png"));
                southSheep = ImageIO.read(new File("Data/GFX/SouthSheep.png"));
                eastSheep = ImageIO.read(new File("Data/GFX/EastSheep.png"));
                westSheep = ImageIO.read(new File("Data/GFX/WestSheep.png"));

            } catch (IOException e) {
                northSheep = null;
                southSheep = null;
                eastSheep = null;
                westSheep = null;

            }

            spriteMap.put("NORTH",northSheep);
            spriteMap.put("SOUTH",southSheep);
            spriteMap.put("EAST",eastSheep);
            spriteMap.put("WEST",westSheep);

        } else if (this.ai.equals("Chaser")){

            BufferedImage northZombie;
            BufferedImage southZombie;
            BufferedImage eastZombie;
            BufferedImage westZombie;


            try {
                northZombie = ImageIO.read(new File("Data/GFX/NorthZombie.png"));
                southZombie = ImageIO.read(new File("Data/GFX/SouthZombie.png"));
                eastZombie = ImageIO.read(new File("Data/GFX/EastZombie.png"));
                westZombie = ImageIO.read(new File("Data/GFX/WestZombie.png"));

            } catch (IOException e) {
                northZombie = null;
                southZombie = null;
                eastZombie = null;
                westZombie = null;

            }


            spriteMap.put("NORTH",northZombie);
            spriteMap.put("SOUTH",southZombie);
            spriteMap.put("EAST",eastZombie);
            spriteMap.put("WEST",westZombie);

        }


    }



}
