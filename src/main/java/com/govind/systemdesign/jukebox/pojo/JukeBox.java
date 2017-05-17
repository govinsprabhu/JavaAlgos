package com.govind.systemdesign.jukebox.pojo;

import java.util.*;

/**
 * Created by 609600403 on 09/05/2017.
 */
public class JukeBox {
    Set<SongWithCount> songs = new HashSet<>();
    Map<String, SongWithCount> map = new HashMap<>();
    PriorityQueue<SongWithCount> priorityQueue = new PriorityQueue<>(new Comparator<SongWithCount>() {
        @Override
        public int compare(SongWithCount o1, SongWithCount o2) {
            return o2.count - o1.count;
        }
    });
    public void addSong(Song song) {
        SongWithCount songWithCount = new JukeBox.SongWithCount(song);
        songs.add(songWithCount);
        priorityQueue.add(songWithCount);
        map.put(song.title, songWithCount);
    }

    public boolean deleteSong(String title) {
        if(map.containsKey(title)){
            map.remove(title);
            return true;
        }
        return false;
    }

    public void printTop(){
        for (SongWithCount song : priorityQueue){
            System.out.println(song.song.title+" ");
        }
    }
    public SongWithCount playSong(String title) {
        if (map.containsKey(title)) {
            SongWithCount songWithCount = map.get(title);
            songWithCount.incrementCount();
            //priorityQueue.add(songWithCount);
            return songWithCount;
        }
        return  null;
    }


    class SongWithCount {
        int count;
        Song song;

        public SongWithCount(Song song) {
            this.song = song;
        }

        public void incrementCount() {
            count++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SongWithCount that = (SongWithCount) o;

            return song.equals(that.song);
        }

        @Override
        public int hashCode() {
            return song.hashCode();
        }
    }

}
