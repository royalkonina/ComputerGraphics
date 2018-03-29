import geometric_primitives.Model;
import utils.Drawer;
import utils.ObjectReader;

import java.io.IOException;

public class Main {

    public static final String FILENAME = "/home/egor/IdeaProjects/Com_grap_lab_1/src/input_output_files/african_head.obj";

    public static void main(String[] args) throws IOException {

        ObjectReader reader = new ObjectReader(FILENAME);
        Model model = reader.readModel();
        Drawer.draw(model);
    }
}
