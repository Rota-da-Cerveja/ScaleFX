package br.com.rotadacerveja;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import br.com.rotadacerveja.scalefx.AspectRatio;
import br.com.rotadacerveja.scalefx.ScaleFX;

public class CLI {
    private static final String OUTPUT_FORMAT = "png";

    public static void main(String[] args) throws Exception {
        try {
            File inputFile = new File(args[0]);

            String fileName = inputFile.getName().split("\\.")[0];

            BufferedImage image = ImageIO.read(inputFile);

            ScaleFX editor = ScaleFX.builder().Image(image).AspectRatio(AspectRatio.A5_3).build();

            BufferedImage newImage = editor.execute();

            File output = new File(String.format("output/%s_gray_5:3.%s", fileName, OUTPUT_FORMAT));

            ImageIO.write(newImage, OUTPUT_FORMAT, output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
