package com.vladislavgoncharov.overlayforcounttimebeforedead.util;

public class StopStartStopwatch {

    private static volatile StatusStopwatch work = StatusStopwatch.STOP;
    private static volatile String result = "00:00";

    public static String getWork() {
        return work.name();
    }

    public static void setWork(StatusStopwatch work) {
        StopStartStopwatch.work = work;
        if (!work.equals(StatusStopwatch.STOP)) setResult("00:00");
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        result = result.replaceAll("\"","");
        StopStartStopwatch.result = result;
    }
}
