package com.company;

public class Main {

    public static void main(String[] args) {
        String input = "test.mov";
        String output = "test";
        Ffmpeg.getInstance().encode(input, output + "_LIBXVID.mkv", Ffmpeg.Vcodec.LIBXVID.getVcode());
        Ffmpeg.getInstance().encode(input, output + "_COPY.mkv", Ffmpeg.Vcodec.COPY.getVcode());
        Ffmpeg.getInstance().encode(input, output + "_MPEG4.mkv", Ffmpeg.Vcodec.MPEG4.getVcode());
        Ffmpeg.getInstance().encode(input, output + "_H264.mkv", Ffmpeg.Vcodec.H264.getVcode());
        Ffmpeg.getInstance().encode(input, output + "_H265.mkv", Ffmpeg.Vcodec.H265.getVcode());
        Ffmpeg.getInstance().shutdown();
    }
}
