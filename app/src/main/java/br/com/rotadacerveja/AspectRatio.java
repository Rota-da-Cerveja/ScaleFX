package br.com.rotadacerveja;

enum AspectRatio {
    A5_3(5.0 / 3.0);

    private final double aspectRatio;

    private AspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public double value() {
        return aspectRatio;
    }
}