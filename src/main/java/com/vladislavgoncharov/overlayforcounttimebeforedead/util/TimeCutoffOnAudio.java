package com.vladislavgoncharov.overlayforcounttimebeforedead.util;

public class TimeCutoffOnAudio {

    private static int timeCutoff = 6000;

    public static int getTimeCutoff() {
        return timeCutoff;
    }

    public static void setTimeCutoff(int timeCutoff) {
        TimeCutoffOnAudio.timeCutoff = timeCutoff;
    }
}
