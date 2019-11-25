package com.company;

public class Main {

    public static void main(String[] args) {

        Ffmpeg ffmpeg = Ffmpeg.getInstance();
        ffmpeg.addHardSubtitles("Silicon.Valley.S01E08.1080p.BluRay.x264-ROVERS.mkv","Silicon.Valley.S01E08.Optimal.Tip-to-Tip.Efficiency.720p.WEB-DL.DD5.1.H.264-DiCKJOKE.简体&英文.ass","Silicon.Valley.S01E08.1080p.BluRay.x264_ROVERS.mkv");
        ffmpeg.shutdown();

    }
}
