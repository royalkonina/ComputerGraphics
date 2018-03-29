// Geometric_Pirmitive;

import Geometric_Pirmitive.PointDouble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readObjFile {


    private String s;

    public readObjFile(String s) {
        this.s = s;
    }

    public PointDouble [] matV() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(s));
        String str;
        int n =0;
        str = sc.nextLine();
        while(str.toCharArray()[0] == 'v')
        {
            if(str.toCharArray()[1] != ' ')
            {
                break;
            }
            n++;
            str = sc.nextLine();
        }

        Scanner sc1 = new Scanner(new File(s));
        PointDouble[] v = new PointDouble [n];
        String s = sc1.nextLine();
        String [] temp;
        for (int i = 0; i < n; i++) {
            temp = s.split(" ");
            v[i] = new PointDouble(Double.parseDouble(temp[1]), Double.parseDouble(temp[3]), Double.parseDouble(temp[2]));
        }

        return v;
    }

    public PointDouble[] matVt() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(s));
        String str;
        int n =0;
        str = sc.nextLine();
        while(str.toCharArray()[0] == 'v')
        {
            if(str.toCharArray()[1] != ' ')
            {
                break;
            }
            n++;
            str = sc.nextLine();
        }
        while(str.toCharArray()[0] != 'v' && str.toCharArray()[1]!= 't')
        {
            str = sc.nextLine();
        }
        PointDouble[] v = new PointDouble [n];
        String [] temp;
        for (int i = 0; i < n; i++) {
            temp = str.split(" ");
            str = sc.nextLine();
            v[i] = new PointDouble(Double.parseDouble(temp[1]), Double.parseDouble(temp[3]), Double.parseDouble(temp[2]));
        }
        return v;
    }

    public PointDouble[] matVn() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(s));
        String str;
        int n =0;
        str = sc.nextLine();
        while(str.toCharArray()[0] == 'v')
        {
            if(str.toCharArray()[1] != ' ')
            {
                break;
            }
            n++;
            str = sc.nextLine();
        }
        while(str.substring(0,2) != "vn" || str.length()<3)
        {
            str = sc.nextLine();
        }
        PointDouble[] v = new PointDouble [n];
        String [] temp;
        for (int i = 0; i < n; i++) {
            temp = str.split(" ");
            str = sc.nextLine();
            v[i] = new PointDouble(Double.parseDouble(temp[1]), Double.parseDouble(temp[3]), Double.parseDouble(temp[2]));
            System.out.println(v[i].getX() + " " + v[i].getY() + " " + v[i].getZ());
        }
        return v;
    }
}
