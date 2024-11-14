package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getClassLoader().getResource("sound/akwedukt.wav");
        soundURL[1] = getClass().getClassLoader().getResource("sound/coin.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sound/powerup.wav");
        soundURL[3] = getClass().getClassLoader().getResource("sound/doors_unlock.wav");
        soundURL[4] = getClass().getClassLoader().getResource("sound/fanfare.wav");
        soundURL[5] = getClass().getClassLoader().getResource("sound/man_getting_hit_1.wav");
        soundURL[6] = getClass().getClassLoader().getResource("sound/sword_swing.wav");
        soundURL[7] = getClass().getClassLoader().getResource("sound/zombie_getting_hit_1.wav");
        soundURL[8] = getClass().getClassLoader().getResource("sound/levelup.wav");

    }

    public void setFile(int i){

        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }

    public void play(){

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
