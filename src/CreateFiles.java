import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFiles {
    File orderFile;
    File productsFile;


    public CreateFiles(File orderFile, File productsFile) {
        this.orderFile = orderFile;
        this.productsFile = productsFile;
    }

    public void work() throws IOException {
        orderFile.createNewFile();
        productsFile.createNewFile();
    }
}
