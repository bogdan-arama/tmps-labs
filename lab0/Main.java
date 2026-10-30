public class Main {
    public static void main(String[] args) {
        // 1. Single Responsibility (SRP)
        Playlist playlist = new Playlist("Favorites");
        playlist.addSong(new Song("Careless Whisper", "George Michael", 300));
        playlist.addSong(new Song("Take Five", "Dave Brubeck", 324));

        // Playlist only manages songs; PlaylistPrinter handles the display
        PlaylistPrinter printer = new PlaylistPrinter();
        printer.print(playlist);

        // 2. Open/Closed (OCP) & 3. Dependency Inversion (DIP)
        // MusicPlayer doesn't care whether we use Speakers or Headphones.
        // It accepts any class that implements AudioOutput.
        AudioOutput headphones = new HeadphoneOutput();
        MusicPlayer player = new MusicPlayer(headphones);

        Song firstSong = playlist.getSongs().get(0);
        player.play(firstSong);

        // Switch to speakers without modifying MusicPlayer
        AudioOutput speakers = new SpeakerOutput();
        MusicPlayer partyPlayer = new MusicPlayer(speakers);
        partyPlayer.play(firstSong);
    }
}