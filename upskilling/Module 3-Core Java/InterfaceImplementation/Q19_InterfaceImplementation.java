// Define interface Playable
interface Playable {
    void play();
}

// Implement in Guitar class
class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Strumming the guitar: G-C-D chords!");
    }
}

// Implement in Piano class
class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing the piano: Beethoven's Moonlight Sonata!");
    }
}

public class Q19_InterfaceImplementation {
    public static void main(String[] args) {
        // Instantiate the classes
        Playable myGuitar = new Guitar();
        Playable myPiano = new Piano();

        // Call the methods
        myGuitar.play();
        myPiano.play();
    }
}
