package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class is responsible for handling sound 
 * effects in the game. It provides static methods 
 * to play different sounds corresponding to various 
 * game events.
 */
public class SoundEffects {
	
	/**
     * Plays the sound effect for a win event.
     */
	public static void playWinSound() {
		File file = new File("soundEffectFiles/winSound.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	/**
     * Plays the sound effect for a loss event.
     */
	public static void playLoseSound() {
		File file = new File("soundEffectFiles/louderBuzzer.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	/**
     * Plays the sound effect for a guess event.
     */
	public static void playGuessSound() {
		File file = new File("soundEffectFiles/negativeBeeps.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	/**
     * Plays the sound effect for a yellow tile event.
     */
	public static void playYellowTile() {
		File file = new File("soundEffectFiles/cartoonJump.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	/**
     * Plays the sound effect for a green tile event.
     */
	public static void playGreenTile() {
		File file = new File("soundEffectFiles/ping.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	/**
     * Plays the sound effect for a grey tile event.
     */
	public static void playGreyTile() {
		File file = new File("soundEffectFiles/wrongAnswer.mp3");
		URI uri = file.toURI();
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
}
