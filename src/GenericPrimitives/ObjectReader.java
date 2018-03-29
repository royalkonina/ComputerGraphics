package GenericPrimitives;

import Geometric_Pirmitive.PointDouble;
import Geometric_Pirmitive.PolygonInt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ObjectReader {


    public static final String SEPARATORS = "vtf \n\r\t\\/n";
    private BufferedReader in;

    public ObjectReader(String filename) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(filename));
    }

    public List<PointDouble> readVerticesDouble() throws IOException {
        List<PointDouble> points = new ArrayList<>();
        String sPoint = in.readLine();
        while (sPoint.charAt(0) == 'v') {
            StringTokenizer tok = new StringTokenizer(sPoint, SEPARATORS);
            double x = Double.parseDouble(tok.nextToken());
            double y = Double.parseDouble(tok.nextToken());
            double z = tok.hasMoreTokens() ? Double.parseDouble(tok.nextToken()) : 0;
            PointDouble point = new PointDouble(x, y, z);
            points.add(point);
            sPoint = in.readLine();
        }
        return points;
    }

    public List<PointDouble> readVtDouble() throws IOException {
        List<PointDouble> points = new ArrayList<>();
        String sPoint = in.readLine();
        while (true) {
            if (sPoint.length() < 2) {
                sPoint = in.readLine();
            } else if (sPoint.charAt(0) == 'v' && sPoint.charAt(1) != 't') {
                sPoint = in.readLine();
            } else
                break;
        }

        while (sPoint.charAt(0) == 'v' && sPoint.charAt(1) == 't') {
            StringTokenizer tok = new StringTokenizer(sPoint, SEPARATORS);
            double x = Double.parseDouble(tok.nextToken());
            double y = Double.parseDouble(tok.nextToken());
            double z = tok.hasMoreTokens() ? Double.parseDouble(tok.nextToken()) : 0;
            PointDouble point = new PointDouble(x, y, z);
            points.add(point);
            sPoint = in.readLine();
        }
        return points;
    }

    public List<PointDouble> readVnDouble() throws IOException {
        List<PointDouble> points = new ArrayList<>();
        String sPoint = in.readLine();
        while (true) {
            if (sPoint.length() < 2) {
                sPoint = in.readLine();
            } else if (sPoint.charAt(0) == 'v' && sPoint.charAt(1) != 'n') {
                sPoint = in.readLine();
            } else
                break;
        }
        while (sPoint.charAt(0) == 'v' && sPoint.charAt(1) == 'n') {
            StringTokenizer tok = new StringTokenizer(sPoint, SEPARATORS);
            double x = Double.parseDouble(tok.nextToken());
            double y = Double.parseDouble(tok.nextToken());
            double z = tok.hasMoreTokens() ? Double.parseDouble(tok.nextToken()) : 0;
            PointDouble point = new PointDouble(x, y, z);
            points.add(point);
            sPoint = in.readLine();
        }
        return points;
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    public List<PolygonInt> readPolygonInt() throws IOException {
        List<PolygonInt> polygons = new ArrayList<>();
        String sPoint = in.readLine();
        while (sPoint.charAt(0) == 'f') {
            StringTokenizer tok = new StringTokenizer(sPoint, SEPARATORS);
            if (tok.countTokens() == 9) {
                int[] x = new int[3];
                int[] y = new int[3];
                int[] z = new int[3];
                for (int i = 0; i < 3; i++) {
                    x[i] = Integer.parseInt(tok.nextToken());
                    y[i] = Integer.parseInt(tok.nextToken());
                    z[i] = Integer.parseInt(tok.nextToken());
                }
                polygons.add(new PolygonInt(x, y, z));
            }
            sPoint = in.readLine();
        }
        return polygons;
    }

}
