public class PlaylistPrinter {
    public void print(Playlist playlist) {
        System.out.println("=== PLAYLIST: " + playlist.getName() + " ===");
        for (Song s : playlist.getSongs()) {
            System.out.printf("- %s by %s (%d sec)%n", s.getTitle(), s.getArtist(), s.getDurationSeconds());
        }
        System.out.println("Total Duration: " + playlist.getTotalDurationSeconds() + " seconds");
        System.out.println("=====================================\n");
    }
}