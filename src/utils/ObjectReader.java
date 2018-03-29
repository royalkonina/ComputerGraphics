package utils;

import geometric_primitives.Face;
import geometric_primitives.Model;
import geometric_primitives.PointDouble;

import java.awt.*;
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

    public PointDouble[] readVDouble() throws IOException {
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
        return (PointDouble[]) points.toArray();
    }

    public PointDouble[] readVtDouble() throws IOException {
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
        return (PointDouble[]) points.toArray();
    }

    public PointDouble[] readVnDouble() throws IOException {
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
        return (PointDouble[]) points.toArray();
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    public Face[] readFaces() throws IOException {
        List<Face> faces = new ArrayList<>();
        String sPoint = in.readLine();
        while (sPoint.charAt(0) == 'f') {
            StringTokenizer tok = new StringTokenizer(sPoint, SEPARATORS);
            int[] v = null;
            int[] vt = null;
            int[] vn = null;
            if (tok.countTokens() == 9) {
                v = new int[3];
                vt = new int[3];
                vn = new int[3];
                for (int i = 0; i < 3; i++) {
                    v[i] = Integer.parseInt(tok.nextToken());
                    vt[i] = Integer.parseInt(tok.nextToken());
                    vn[i] = Integer.parseInt(tok.nextToken());
                }
            } else if (tok.countTokens() == 6) {
                v = new int[3];
                vt = new int[3];
                for (int i = 0; i < 3; i++) {
                    v[i] = Integer.parseInt(tok.nextToken());
                    vt[i] = Integer.parseInt(tok.nextToken());
                }
            } else {
                v = new int[3];
                for (int i = 0; i < 3; i++) {
                    v[i] = Integer.parseInt(tok.nextToken());
                }
            }
            faces.add(new Face(v, vt, vn));
            sPoint = in.readLine();
        }
        return (Face[]) faces.toArray();
    }

    public Model readModel() throws IOException {
        PointDouble[] pointsV = readVDouble();
        PointDouble[] pointsVt = readVtDouble();
        PointDouble[] pointsVn = readVnDouble();
        readLine();
        readLine();
        readLine();
        Face[] faces = readFaces();
        return new Model(pointsV, pointsVt, pointsVn, faces);
    }

}
