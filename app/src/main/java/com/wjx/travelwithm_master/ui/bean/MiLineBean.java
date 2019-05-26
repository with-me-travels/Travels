package com.wjx.travelwithm_master.ui.bean;

import java.util.List;

public class MiLineBean {

    /**
     * code : 0
     * desc :
     * result : {"count":3,"page":1,"limit":20,"routes":[{"id":93,"cityID":10,"priceInCents":190,"title":"伏见稻荷大社","intro":"8小时探访最惊艳的鸟居隧道","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510642364344_77c37f1dc47373792692f9952ee504c8.jpg","videoURL":"","sequence":887,"isPurchased":true,"isCollected":false,"city":"日本·京都","price":"1.9","date":"2017-06-07 22:46"},{"id":60,"cityID":16,"priceInCents":190,"title":"宇治","intro":"7小时品味古都的抹茶香","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510745071419_c9a5e6407b40686967f239a9393cfadf.jpg","videoURL":"","sequence":1732,"isPurchased":true,"isCollected":false,"city":"日本·京都周边","price":"1.9","date":"2017-05-17 15:39"},{"id":94,"cityID":15,"priceInCents":190,"title":"北镰仓","intro":"6小时在名寺古刹间一日出家","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510651016748_81c07c5ce074e364f1a19fbcf56b1935.jpg","videoURL":"","sequence":1895,"isPurchased":true,"isCollected":false,"city":"日本·东京周边","price":"1.9","date":"2017-06-08 00:28"}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * count : 3
         * page : 1
         * limit : 20
         * routes : [{"id":93,"cityID":10,"priceInCents":190,"title":"伏见稻荷大社","intro":"8小时探访最惊艳的鸟居隧道","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510642364344_77c37f1dc47373792692f9952ee504c8.jpg","videoURL":"","sequence":887,"isPurchased":true,"isCollected":false,"city":"日本·京都","price":"1.9","date":"2017-06-07 22:46"},{"id":60,"cityID":16,"priceInCents":190,"title":"宇治","intro":"7小时品味古都的抹茶香","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510745071419_c9a5e6407b40686967f239a9393cfadf.jpg","videoURL":"","sequence":1732,"isPurchased":true,"isCollected":false,"city":"日本·京都周边","price":"1.9","date":"2017-05-17 15:39"},{"id":94,"cityID":15,"priceInCents":190,"title":"北镰仓","intro":"6小时在名寺古刹间一日出家","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510651016748_81c07c5ce074e364f1a19fbcf56b1935.jpg","videoURL":"","sequence":1895,"isPurchased":true,"isCollected":false,"city":"日本·东京周边","price":"1.9","date":"2017-06-08 00:28"}]
         */

        private int count;
        private int page;
        private int limit;
        private List<RoutesBean> routes;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<RoutesBean> getRoutes() {
            return routes;
        }

        public void setRoutes(List<RoutesBean> routes) {
            this.routes = routes;
        }

        public static class RoutesBean {
            /**
             * id : 93
             * cityID : 10
             * priceInCents : 190
             * title : 伏见稻荷大社
             * intro : 8小时探访最惊艳的鸟居隧道
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1510642364344_77c37f1dc47373792692f9952ee504c8.jpg
             * videoURL :
             * sequence : 887
             * isPurchased : true
             * isCollected : false
             * city : 日本·京都
             * price : 1.9
             * date : 2017-06-07 22:46
             */

            private int id;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String price;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
