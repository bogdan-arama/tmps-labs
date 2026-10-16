public class SpeakerOutput implements AudioOutput {
    @Override
    public void playAudio(Song song) {
        System.out.println("[SPEAKER] Playing aloud: " + song.getTitle() + " by " + song.getArtist());
    }
}