package epicCrawl;

import  sun.audio.*;
import  java.io.*;

public class SoundManager{

    public void playSound(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Sounds/SwordSwing.wav");

        AudioStream as = null;

        try{
            as = new AudioStream(is);
        }
        catch(IOException e){
            System.err.println("Error loading wav file: " + e);
        }

        AudioPlayer.player.start(as);
    }
}