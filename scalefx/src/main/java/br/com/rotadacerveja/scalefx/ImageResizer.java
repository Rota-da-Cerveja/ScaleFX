package br.com.rotadacerveja.scalefx;

public class ImageResizer {
    private final int originalWidth;
    private final int originalHeight;
    private final int newWidth;
    private final int newHeight;

    public ImageResizer(int originalWidth, int originalHeight, AspectRatio aspectRatio) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        double currentAspectRatio = (double) originalWidth / originalHeight;
        switch (Double.compare(currentAspectRatio, aspectRatio.getValue())) {
            case 1:
                this.newWidth = originalWidth;
                this.newHeight = (int) (originalWidth / aspectRatio.getValue());
                break;
            case -1:
                this.newWidth = (int) (originalHeight * aspectRatio.getValue());
                this.newHeight = originalHeight;
                break;
            default:
                this.newWidth = originalWidth;
                this.newHeight = originalHeight;
                break;
        }
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }
}
