package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import hust.soict.hedspi.aims.exception.PlayerException;
public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    // Constructors
    public CompactDisc(int id, String title, String category, String director, int length, float cost, String artist) {
        super(id, title, category, director, length, cost);
        this.artist = artist;
    }

    public CompactDisc(String title, String category, float cost, String artist) {
        super(0, title, category, null, 0, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            System.out.println("Cannot add a null track.");
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added to CD '" + getTitle() + "': " + track.getTitle());
        } else {
            System.out.println("Track '" + track.getTitle() + "' already exists in CD '" + getTitle() + "'.");
        }
    }

    public void removeTrack(Track track) {
        if (track == null) {
            System.out.println("Cannot remove a null track.");
            return;
        }
        if (tracks.remove(track)) {
            System.out.println("Track removed from CD '" + getTitle() + "': " + track.getTitle());
        } else {
            System.out.println("Track '" + track.getTitle() + "' not found in CD '" + getTitle() + "'.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            if (track != null) {
                totalLength += track.getLength();
            }
        }
        return totalLength;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0 && !tracks.isEmpty()) { // Có track nhưng tổng length <=0 (do track length âm/0)
            StringBuilder trackIssues = new StringBuilder();
            for (Track track : tracks) {
                if (track.getLength() <=0) {
                    trackIssues.append("\n - Track '").append(track.getTitle()).append("' has non-positive length.");
                }
            }
            if (trackIssues.length() > 0) {
                throw new PlayerException("ERROR: CD '" + this.getTitle() + "' contains tracks with non-positive length." + trackIssues.toString());
            } else {
                throw new PlayerException("ERROR: CD '" + this.getTitle() + "' has an overall non-positive length but tracks seem valid. Check track lengths.");
            }
        }
        if (tracks.isEmpty()) {
            throw new PlayerException("ERROR: CD '" + this.getTitle() + "' has no tracks to play.");
        }
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("Artist: " + this.getArtist());
        System.out.println("Total CD Length: " + this.getLength() + "s");

        boolean allTracksOK = true;
        ArrayList<String> failedTracksMessages = new ArrayList<>();

        for (Track track : tracks) {
            try {
                if (track != null) {
                    track.play();
                } else {
                    System.err.println("Encountered a null track in CD: " + getTitle());
                    failedTracksMessages.add("A null track was encountered.");
                    allTracksOK = false;
                }
            } catch (PlayerException e) {
                System.err.println("Error playing track '" + (track != null ? track.getTitle() : "UNKNOWN_NULL_TRACK") + "' in CD '" + getTitle() + "': " + e.getMessage());
                failedTracksMessages.add("Track '" + (track != null ? track.getTitle() : "N/A") + "': " + e.getMessage());
                allTracksOK = false;
            }
        }

        if (!allTracksOK) {
            StringBuilder errorMessage = new StringBuilder("ERROR: CD '").append(this.getTitle()).append("' could not be played completely due to issues with some tracks:");
            for (String msg : failedTracksMessages) {
                errorMessage.append("\n - ").append(msg);
            }
            throw new PlayerException(errorMessage.toString());
        }
    }
}