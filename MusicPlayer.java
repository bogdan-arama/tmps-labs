public class MusicPlayer {
    private final AudioOutput output; // High-level module depends on abstraction

    public MusicPlayer(AudioOutput output) {
        this.output = output;
    }

    public void play(Song song) {
        output.playAudio(song);
    }
}