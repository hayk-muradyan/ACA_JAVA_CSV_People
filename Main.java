import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.tools.ant.listener.BigProjectLogger.HEADER;

public class Main {

    public static void createCSVFile() throws IOException {
        FileWriter out = new FileWriter("People1.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADER)))
        {
            printer.printRecord("Name","Last Name","Date","Race","Phone Number","Email");
            printer.printRecord("Hayk","Muradyan","05/01/1997","White","094461501","mhayk.973779@gmail.com");

        }
        catch(Exception e)
        {
            e.toString();
        }

    }



    public static  void main(String[] args) throws IOException {

        Controller controller = new Controller();
        controller.scanner();
    }
}

