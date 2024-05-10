package br.com.rotadacerveja;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class App {
    private static final String OUTPUT_FORMAT = "png";

    public static void main(String[] args) throws Exception {
        try {
            File inputFile = new File(args[0]);

            String fileName = inputFile.getName().split("\\.")[0];

            BufferedImage image = ImageIO.read(inputFile);

            System.out.printf("\n========================================\nFile: %s\nCurrent aspect ratio: %.2f\n", inputFile.getName(),
                    image.getWidth() / (double) image.getHeight());

            Resize resize = new Resize(image.getWidth(), image.getHeight(), AspectRatio.A5_3);

            System.err.printf("New Aspect ratio: %.2f\n", (double)resize.getNewWidth()/resize.getNewHeight());

            BufferedImage novaImagem = getResizingImage(image, resize);

            File output = new File(String.format("output/%s_gray_5:3.%s", fileName, OUTPUT_FORMAT));

            ImageIO.write(novaImagem, OUTPUT_FORMAT, output);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedImage getResizingImage(BufferedImage image, Resize resize) {
        BufferedImage newImage = new BufferedImage(resize.getNewWidth(), resize.getNewHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = newImage.createGraphics();

        g.drawImage(convertToGrayInvert(image), (resize.getNewWidth() - resize.getWidth()) / 2,
                (resize.getNewHeight() - resize.getHeight()) / 2, resize.getWidth(),
                resize.getHeight(), null);
        return newImage;
    }

    private static BufferedImage convertToGrayInvert(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color currentPixelColor = new Color(image.getRGB(x, y));
                int grayScale = 255 - (int) (currentPixelColor.getRed() * 0.299 + currentPixelColor.getGreen() * 0.587
                        + currentPixelColor.getBlue() * 0.114);
                int alpha = grayScale;
                Color newPixelColor = new Color(255, 255, 255, alpha);
                newImage.setRGB(x, y, newPixelColor.getRGB());
            }
        }

        return newImage;
    }
}

/**
 * InnerApp
 */
class Resize {
    private int width;
    private int height;
    private int newWidth;
    private int newHeight;

    public Resize(int width, int height, AspectRatio aspectRatio) {
        this.width = width;
        this.height = height;
        double currentAspectRatio = (double) width / height;
        switch (Double.compare(currentAspectRatio, aspectRatio.value())) {
            case 1:
                this.newWidth = width;
                this.newHeight = (int) (width / aspectRatio.value());
                break;
            case -1:
                this.newWidth = (int) (height * aspectRatio.value());
                this.newHeight = height;
                break;
            default:
                this.newWidth = width;
                this.newHeight = height;
                break;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

}
