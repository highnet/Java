import java.awt.*;
import java.util.ArrayList;


public class Patch {
    private ArrayList<Point> data;

    public Patch() {
        data = new ArrayList<Point>();
    }

    public void addData(Point data) {
        this.data.add(data);
    }

    public Polygon get_Polygon() {
        Polygon p = new Polygon();

        for (int i = 0; i < data.size(); i++) {
            p.addPoint(data.get(i).x, data.get(i).y);
        }

        return p;
    }

    public String toString() {
        String ret = "";

        for (int i = 0; i < data.size(); i++) {
            ret += data + " ; ";
        }

        return ret;
    }
}
