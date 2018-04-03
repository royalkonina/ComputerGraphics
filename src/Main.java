import geometric_primitives.Model;
import geometric_primitives.PointDouble;
import objects.TgaImage;
import utils.Drawer;
import utils.ObjectReader;
import utils.PictureIO;

import java.io.IOException;

public class Main {

    public static final String FILENAME = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head.obj";
    private static final String TEXTURE_FILENAME = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head_diffuse.tga";


    public static void main(String[] args) throws IOException {

        ObjectReader objReader = new ObjectReader(FILENAME);

        TgaImage texture = PictureIO.readImage(TEXTURE_FILENAME, "texture");

        PointDouble camera = new PointDouble(0, 0, 1);
        Model model = objReader.readModel();
        //Drawer.draw(model);
        Drawer.drawWithBarycentric(model, camera);
        Drawer.drawWithTexture(model, camera, texture);
    }
}
