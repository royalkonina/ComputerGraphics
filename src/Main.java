import geometric_primitives.Model;
import utils.ObjectReader;

import java.io.IOException;

public class Main {

    public static final String FILENAME = "input_output_files/african_head.obj";

    public static void main(String[] args) throws IOException {

        ObjectReader reader = new ObjectReader(FILENAME);
        Model model = reader.readModel();
    }
}
