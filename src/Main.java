import GenericPrimitives.ObjectReader;
import Geometric_Pirmitive.PointDouble;
import Geometric_Pirmitive.PolygonInt;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String FILENAME = "/home/egor/labs/l1/obj/african_head.obj";

    public static void main(String[] args) throws IOException {

        ObjectReader reader = new ObjectReader(FILENAME);
        List<PointDouble> pointsV = reader.readVerticesDouble();
        List<PointDouble> pointsVt = reader.readVtDouble();
        List<PointDouble> pointsVn = reader.readVnDouble();
        reader.readLine();
        reader.readLine();
        reader.readLine();
        List<PolygonInt> polygonInts = reader.readPolygonInt();
        System.out.println(polygonInts.size());
        //  System.out.println(points.get(points.size() - 1).toString());
    }
}
