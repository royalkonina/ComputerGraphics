package Geometric_Pirmitive;

public class PolygonDouble {

    private double [] x;
    private double [] y;
    private double [] z;

    public PolygonDouble(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        z = new double[]{0.0, 0.0, 0.0};
    }

    public PolygonDouble(double[] x, double [] y, double[] z)
    {
        this.x = x;

        this.y = y;
        this.z = z;
    }



    public double[] getX() {
        return x;
    }

    public double getX0(){
        return x[0];
    }

    public double getX1(){
        return x[1];
    }

    public double getX2(){
        return x[2];
    }


    public void setX(double[] x) {
        this.x = x;
    }

    public void setX0(double x) {
        this.x[0] = x;
    }

    public void setX1(double x) {
        this.x[1] = x;
    }

    public void setX2(double x) {
        this.x[2] = x;
    }




    public double[] getY() {
        return y;
    }

    public double getY0(){
        return y[0];
    }

    public double getY1(){
        return y[1];
    }

    public double getY2(){
        return y[2];
    }


    public void setY(double[] y) {
        this.y = y;
    }

    public void setY0(double y) {
        this.y[0] = y;
    }

    public void setY1(double y) {
        this.y[1] = y;
    }

    public void setY2(double y) {
        this.y[2] = y;
    }



    public double[] getZ() {
        return z;
    }

    public double getZ0(){
        return z[0];
    }

    public double getZ1(){
        return z[1];
    }

    public double getZ2(){
        return z[2];
    }


    public void setZ(double[] z) {
        this.z = z;
    }

    public void setZ0(double z) {
        this.z[0] = z;
    }

    public void setZ1(double z) {
        this.z[1] = z;
    }

    public void setZ2(double z) {
        this.z[2] = z;
    }
}
