package br.com.rotadacerveja.scalefx;

public enum AspectRatio {
    A5_3(5.0 / 3.0);

    private final double aspectRatio;

    private AspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    double getValue() {
      return aspectRatio;
    }
}