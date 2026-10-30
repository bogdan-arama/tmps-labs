public class HeadphoneOutput implements AudioOutput {
    @Override
    public void playAudio(Song song) {
        System.out.println("[HEADPHONES] Playing privately: " + song.getTitle() + " by " + song.getArtist());
    }
}