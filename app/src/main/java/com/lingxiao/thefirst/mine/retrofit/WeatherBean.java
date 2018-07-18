package com.lingxiao.thefirst.mine.retrofit;

public class WeatherBean {
    /**
     * weatherinfo : {"city":"北京","cityid":"101010100","temp":"27.9","WD":"南风","WS":"小于3级","SD":"28%","AP":"1002hPa","njd":"暂无实况","WSE":"<3","time":"17:55","sm":"2.1","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB"}
     */
    public WeatherinfoBean weatherinfo;

    public static class WeatherinfoBean {
        /**
         * city : 北京
         * cityid : 101010100
         * temp : 27.9
         * WD : 南风
         * WS : 小于3级
         * SD : 28%
         * AP : 1002hPa
         * njd : 暂无实况
         * WSE : <3
         * time : 17:55
         * sm : 2.1
         * isRadar : 1
         * Radar : JC_RADAR_AZ9010_JB
         */
        public String city;
        public String cityid;
        public String temp;
        public String WD;
        public String WS;
        public String SD;
        public String AP;
        public String njd;
        public String WSE;
        public String time;
        public String sm;
        public String isRadar;
        public String Radar;
    }
}
