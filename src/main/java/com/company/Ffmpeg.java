package com.company;

/**
 * @author chxbca
 */
public class Ffmpeg {

    private Ffmpeg() {
    }

    public static Ffmpeg getInstance() {
        return INSTANCE;
    }

    private static final Ffmpeg INSTANCE = new Ffmpeg();

    private static final String VIDEO_ADD_SOFT_SUBTITLES = "ffmpeg -i %s -i %s -c copy %s";

    private static final String VIDEO_ADD_HARD_SUBTITLES = "ffmpeg -i %s -vf subtitles=%s %s";

    private static final String VIDEO_ENCODE = "ffmpeg -i %s -vcodec %s %s";

    private CommandRunner commandRunner = new CommandRunner();

    public void encode(String input, String output, String vcodec) {
        commandRunner.run(String.format(VIDEO_ENCODE, input, vcodec, output));
    }

    public void addSoftSubtitles(String input, String subtitles, String output) {
        commandRunner.run(String.format(VIDEO_ADD_SOFT_SUBTITLES, input, subtitles, output));
    }

    public void addHardSubtitles(String input, String subtitles, String output) {
        commandRunner.run(String.format(VIDEO_ADD_HARD_SUBTITLES, input, subtitles, output));
    }

    public void shutdown() {
        commandRunner.shutdown();
    }

    enum Vcodec {

        /**
         * h264
         */
        H264("h264"),

        /**
         * mpeg4
         */
        MPEG4("mpeg4"),

        /**
         * libxvid
         */
        LIBXVID("libxvid"),

        /**
         * libx265
         */
        H265("libx265"),

        /**
         * copy
         */
        COPY("copy");

        private String vcode;

        Vcodec(String vcode) {
            this.vcode = vcode;
        }

        public String getVcode() {
            return vcode;
        }
    }
}
