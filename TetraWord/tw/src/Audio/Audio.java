/**
 * 
 */
package Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class Audio 
 *
 */
public class Audio extends Thread {
	
	AudioInputStream audioInputStream = null;
    SourceDataLine line;
    String link; //chemin vers le fichier
    boolean loop, end;
    
    /**
	 * Constructor for objects of class Audio
	 */
    public Audio(String link){
    	this.link = link;
    	this.loop = true;
    	this.end = false;
    }
    
    
    /**
	 * Getters and setters 
	 */
    public boolean isEnd() {
		return end;
	}


	public void setEnd(boolean end) {
		this.end = end;
	}



	/**
	 * Function run use when we call start()
	 */
    public void run(){
    	
    	while(this.loop) {
    		
	        File fichier = new File(this.link);
	      
	        try {
	        AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
	        } catch (UnsupportedAudioFileException e1) {
	            e1.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	         
	        try {
	            audioInputStream = AudioSystem.getAudioInputStream(fichier);
	        } catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	         AudioFormat audioFormat = audioInputStream.getFormat();
	         DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
	          
	         try {
	             line = (SourceDataLine) AudioSystem.getLine(info);
	                        
	        } catch (LineUnavailableException e) {
	               e.printStackTrace();
	               return;
	        }
	          
	        try {
	                line.open(audioFormat);
	        } catch (LineUnavailableException e) {
	                    e.printStackTrace();
	                    return;
	        }
	        
	    	line.start();
	        try {
	            byte bytes[] = new byte[1024];
	            int bytesRead=0;
	            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
	                 line.write(bytes, 0, bytesRead);
	                }
	        } catch (IOException io) {
	            io.printStackTrace();
	            return;
	        }
            
            if(this.end) 
            	this.loop = true;
            else
            	this.loop = false;
            
    	}

    }
    
    
    public void stopSound() {
    	line.stop();
    }
    

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
