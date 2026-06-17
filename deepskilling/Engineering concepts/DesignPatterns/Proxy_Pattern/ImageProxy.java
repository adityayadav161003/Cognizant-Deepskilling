interface Image { void display(); }
class RealImage implements Image {
    public RealImage() { System.out.println("Loading image from disk..."); }
    public void display() { System.out.println("Displaying Real Image."); }
}

class ProxyImage implements Image {
    private RealImage realImage;
    public void display() {
        if (realImage == null) realImage = new RealImage();
        realImage.display();
    }
}
