/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.edu.ashoka.shuttle;


import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Mayukh Nair
 */
 

public class SoundAlerts {
    // No JavaFX panels here, just initializing the JavaFX library for the media player to work

    
        public void playSound(String soundpath){
            Thread playSoundThread=new Thread(() -> {               
                    try {
                        InputStream is=getClass().getResourceAsStream(soundpath);
                        Player player=new Player(is);
                        player.play();
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(SoundAlerts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            playSoundThread.start();
        }
}
