package br.com.rotadacerveja.scalefx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class ScaleFXImpl implements ScaleFX {

  private final BufferedImage image;
  private final ImageResizer resizer;

  public ScaleFXImpl(BufferedImage image, AspectRatio aspectRatio) {
    this.image = image;
    this.resizer = new ImageResizer(image.getWidth(), image.getHeight(), aspectRatio);
  }

  @Override
  public BufferedImage execute(){
    return getResizingImage();
  }

  private BufferedImage getResizingImage() {
    BufferedImage newImage = new BufferedImage(resizer.getNewWidth(), resizer.getNewHeight(),
            BufferedImage.TYPE_INT_ARGB);

    Graphics2D g = newImage.createGraphics();

    g.drawImage(convertToGrayInvert(), (resizer.getNewWidth() - resizer.getOriginalWidth()) / 2,
            (resizer.getNewHeight() - resizer.getOriginalHeight()) / 2, resizer.getOriginalWidth(),
            resizer.getOriginalHeight(), null);
    return newImage;
}

  private BufferedImage convertToGrayInvert() {
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
