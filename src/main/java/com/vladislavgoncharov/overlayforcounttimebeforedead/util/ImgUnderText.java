package com.vladislavgoncharov.overlayforcounttimebeforedead.util;

public class ImgUnderText {

    private static String[] addressImgUnderNumber = {"/image/img_under_text/under_nickname.png", "-66px"};
    private static String[] addressImgUnderStopwatch = {"/image/img_under_text/under_nickname.png", "76px"};
    private static String addressImgUnderSelectMap = "/image/img_under_text/under_select_map.png";

    public static String[] getAddressImgUnderNumber() {
        return addressImgUnderNumber;
    }

    public static void setAddressImgUnderNumber(String addressImgUnderNumber) {
        ImgUnderText.addressImgUnderNumber[0] = addressImgUnderNumber;
    }
    public static void setOffsetImgUnderNickname(String offsetImgUnderNumber) {
        ImgUnderText.addressImgUnderNumber[1] = offsetImgUnderNumber;
    }

    public static String[] getAddressImgUnderStopwatch() {
        return addressImgUnderStopwatch;
    }

    public static void setAddressImgUnderStopwatch(String addressImgUnderStopwatch) {
        ImgUnderText.addressImgUnderStopwatch[0] = addressImgUnderStopwatch;
    }
    public static void setOffsetImgUnderStopwatch(String offsetImgUnderNumber) {
        ImgUnderText.addressImgUnderStopwatch[1] = offsetImgUnderNumber;
    }

    public static String getAddressImgUnderSelectMap() {
        return addressImgUnderSelectMap;
    }

    public static void setAddressImgUnderSelectMap(String addressImgUnderSelectMap) {
        ImgUnderText.addressImgUnderSelectMap = addressImgUnderSelectMap;
    }
}
