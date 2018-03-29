package Geometric_Pirmitive;

public class PolygonInt {

        private int [] x;
        private int [] y;
        private int [] z;

        public PolygonInt(int[] x, int[] y) {
            this.x = x;
            this.y = y;
            z = new int[]{0, 0, 0};
        }

        public PolygonInt(int [] x, int [] y, int [] z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }



        public int[] getX() {
            return x;
        }

        public int getX0(){
            return x[0];
        }

        public int getX1(){
            return x[1];
        }

        public int getX2(){
            return x[2];
        }


        public void setX(int [] x) {
            this.x = x;
        }

        public void setX0(int x) {
            this.x[0] = x;
        }

        public void setX1(int x) {
            this.x[1] = x;
        }

        public void setX2(int x) {
            this.x[2] = x;
        }




        public int[] getY() {
            return y;
        }

        public int getY0(){
            return y[0];
        }

        public int getY1(){
            return y[1];
        }

        public int getY2(){
            return y[2];
        }


        public void setY(int[] y) {
            this.y = y;
        }

        public void setY0(int y) {
            this.y[0] = y;
        }

        public void setY1(int y) {
            this.y[1] = y;
        }

        public void setY2(int y) {
            this.y[2] = y;
        }



        public int[] getZ() {
            return z;
        }

        public int getZ0(){
            return z[0];
        }

        public int getZ1(){
            return z[1];
        }

        public int getZ2(){
            return z[2];
        }


        public void setZ(int[] z) {
            this.z = z;
        }

        public void setZ0(int z) {
            this.z[0] = z;
        }

        public void setZ1(int z) {
            this.z[1] = z;
        }

        public void setZ2(int z) {
            this.z[2] = z;
        }

}
