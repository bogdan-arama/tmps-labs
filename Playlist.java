import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private final String name;
    private final List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public String getName() {
        return name;
    }

    public int getTotalDurationSeconds() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDurationSeconds();
        }
        return total;
    }
}