package br.com.rotadacerveja.scalefx;

import java.awt.image.BufferedImage;

public interface ScaleFX {

  BufferedImage execute();

  public static ScaleFXBuilder builder(){
    return new ScaleFXBuilder();
  }

  class ScaleFXBuilder {
    private BufferedImage image; 
    private AspectRatio aspectRatio;

    protected ScaleFXBuilder(){}

    public ScaleFXBuilder Image(BufferedImage image){
      this.image = image;
      return this;
    }

    public ScaleFXBuilder AspectRatio(AspectRatio aspectRatio){
      this.aspectRatio = aspectRatio;
      return this;
    }

    public ScaleFX build(){
      return new ScaleFXImpl(image, aspectRatio);
    }

  }

}