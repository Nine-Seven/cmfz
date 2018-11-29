package com.util;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;

public class AudioUtil {

    public static String getAudioPlayTime(File file) {
        MP3AudioHeader audioHeader = null;
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            audioHeader = (MP3AudioHeader) f.getAudioHeader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        double length = audioHeader.getPreciseTrackLength();
        return (int) length / 60 + ":" + (int) length % 60;
    }

}
