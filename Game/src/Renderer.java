import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Map;

public class Renderer {
 
    public void paintTilesLayer1(Graphics g, Map<String, BufferedImage> bufferedImageMap,Overworld currentOverWorld,ImageObserver io,Player player1) {

        assert bufferedImageMap != null : "ERROR: bufferedImageMap is null";


        for (int j = 0; j < 24; j++) { // foreach tile outer loop
            for (int i = 31; i > 0; i--) { // foreach tile inner loop

                String tileTypeToPaint = currentOverWorld.tilemap[i][j].type; // store tile type as string
                switch (tileTypeToPaint) { // Rendering unit for each tile type
                    case "tree":
                        g.drawImage(bufferedImageMap.get("TREE"), i * 25 - 19, j * 25 - 80, 65, 100, io);     // draws a tree
                        break;
                    case "stone":
                        g.drawImage(bufferedImageMap.get("STONE"), i * 25 - 5, j * 25 - 10, 40, 40, io);     // draws a tree
                        break;
                    case "woodenfencehorizontal":
                        g.drawImage(bufferedImageMap.get("WOODENFENCEHORIZONTAL"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodenfencevertical":
                        g.drawImage(bufferedImageMap.get("WOODENFENCEVERTICAL"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodenfencenwcorner":
                        g.drawImage(bufferedImageMap.get("WOODENFENCENWCORNER"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodenfenceswcorner":
                        g.drawImage(bufferedImageMap.get("WOODENFENCESWCORNER"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodenfencenecorner":
                        g.drawImage(bufferedImageMap.get("WOODENFENCENECORNER"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodenfencesecorner":
                        g.drawImage(bufferedImageMap.get("WOODENFENCESECORNER"), i * 25, j * 25, 25, 25, io);
                        break;


                }
                if (player1.xPos / 25 == i && player1.yPos / 25 == j) {
              //      paintPlayer(g, 1);
                }

                for (Npc n : currentOverWorld.npcList) {
                    if (j == n.yPos / 25 && i == n.xPos / 25) {
                //        paintNpcs(g, n);

                    }
                }


            }

        }
    }
    public void paintTilesLayer0(Graphics g, Map<String, BufferedImage> bufferedImageMap,Overworld currentOverWorld,ImageObserver io) { // Tile Rendering System


        Npc n = new Npc(0, 0, 0, 0, Color.BLACK, "");


        assert bufferedImageMap != null : "ERROR: bufferedImageMap is null";


        for (int i = 0; i < 32; i++) { // foreach tile outer loop
            for (int j = 0; j < 24; j++) { // foreach tile inner loop

                String tileTypeToPaint = currentOverWorld.tilemap[i][j].type; // store tile type as string
                switch (tileTypeToPaint) { // Rendering unit for each tile type
                    case "grass":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass on top of each "grass" ti
                        break;
                    case "woodfloor":
                        g.drawImage(bufferedImageMap.get("WOODFLOOR"), i * 25, j * 25, 25, 25, io);     // draws a grass on top of each "grass" ti
                        break;
                    case "water":
                        g.setColor(Color.blue);
                        g.drawImage(bufferedImageMap.get("WATER"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "tree":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        g.drawImage(bufferedImageMap.get("TREE"), i * 25 - 19, j * 25 - 80, 65, 100, io);     // draws a tree
                        break;
                    case "stone":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        g.drawImage(bufferedImageMap.get("STONE"), i * 25 - 5, j * 25 - 10, 40, 40, io);     // draws a tree
                        break;
                    case "sand":
                        g.setColor(Color.orange);
                        g.drawImage(bufferedImageMap.get("SAND"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "rakeddirt":
                        g.setColor(new Color(100, 40, 19));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("RAKEDDIRT"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "dirt":
                        g.setColor(new Color(100, 80, 30));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("DIRT"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "plankwall":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        g.drawImage(bufferedImageMap.get("PLANKWALL"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "stonepathgrass":
                        g.drawImage(bufferedImageMap.get("STONEPATHGRASS"), i * 25, j * 25, 25, 25, io);
                        break;

                    case "woodfloordooreast":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOOREAST"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodfloordoornorth":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORNORTH"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodfloordoorsouth":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORSOUTH"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "woodfloordoorwest":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORWEST"), i * 25, j * 25, 25, 25, io);
                        break;

                    case "openwoodfloordooreast":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORSOUTH"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "openwoodfloordoornorth":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOOREAST"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "openwoodfloordoorsouth":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORWEST"), i * 25, j * 25, 25, 25, io);
                        break;
                    case "openwoodfloordoorwest":
                        g.drawImage(bufferedImageMap.get("WOODFLOORDOORNORTH"), i * 25, j * 25, 25, 25, io);
                        break;


                    case "woodenfencehorizontal":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    case "woodenfencevertical":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    case "woodenfencenwcorner":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    case "woodenfencenecorner":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    case "woodenfencesecorner":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    case "woodenfenceswcorner":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, io);     // draws a grass
                        break;
                    default:
                        g.setColor(Color.red);
                        g.drawString("ERR", i * 25, j * 25 + 25);
                        break;
                }

            }

        }
    }
}
