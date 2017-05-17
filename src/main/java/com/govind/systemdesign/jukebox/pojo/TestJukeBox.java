package com.govind.systemdesign.jukebox.pojo;

/**
 * Created by 609600403 on 09/05/2017.
 */
public class TestJukeBox {
    public static void main(String[] args) {
        JukeBox jukeBox = new JukeBox();
        jukeBox.addSong(new Song("Keep"));
        jukeBox.addSong(new Song("Mind"));
        jukeBox.addSong(new Song("in"));
        jukeBox.addSong(new Song("Present"));
        jukeBox.playSong("Keep");
        jukeBox.playSong("Mind");
        jukeBox.playSong("Mind");
        jukeBox.playSong("Present");
        jukeBox.printTop();
    }
}
