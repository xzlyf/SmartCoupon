package publi.xz.com.smartcoupon.entity;

import java.util.List;

public class Baoyou9_9 {

    private long time;
    private int code;
    private String msg;
    private DataBean data;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private int totalNum;
        private String pageId;
        private List<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private int id;
            private String goodsId;
            private String title;
            private String dtitle;
            private double originalPrice;
            private double actualPrice;
            private int shopType;
            private int goldSellers;
            private int monthSales;
            private int twoHoursSales;
            private int dailySales;
            private int commissionType;
            private String desc;
            private int couponReceiveNum;
            private String couponLink;
            private String couponEndTime;
            private String couponStartTime;
            private int couponPrice;
            private String couponConditions;
            private int activityType;
            private String createTime;
            private String mainPic;
            private String marketingMainPic;
            private String sellerId;
            private int cid;
            private double discounts;
            private double commissionRate;
            private int couponTotalNum;
            private int haitao;
            private String activityStartTime;
            private String activityEndTime;
            private String shopName;
            private int shopLevel;
            private double descScore;
            private int brand;
            private String brandId;
            private String brandName;
            private int hotPush;
            private String teamName;
            private String itemLink;
            private int tchaoshi;
            private String detailPics;
            private double dsrScore;
            private double dsrPercent;
            private double shipScore;
            private double shipPercent;
            private double serviceScore;
            private double servicePercent;
            private int tbcid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDtitle() {
                return dtitle;
            }

            public void setDtitle(String dtitle) {
                this.dtitle = dtitle;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public double getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(double actualPrice) {
                this.actualPrice = actualPrice;
            }

            public int getShopType() {
                return shopType;
            }

            public void setShopType(int shopType) {
                this.shopType = shopType;
            }

            public int getGoldSellers() {
                return goldSellers;
            }

            public void setGoldSellers(int goldSellers) {
                this.goldSellers = goldSellers;
            }

            public int getMonthSales() {
                return monthSales;
            }

            public void setMonthSales(int monthSales) {
                this.monthSales = monthSales;
            }

            public int getTwoHoursSales() {
                return twoHoursSales;
            }

            public void setTwoHoursSales(int twoHoursSales) {
                this.twoHoursSales = twoHoursSales;
            }

            public int getDailySales() {
                return dailySales;
            }

            public void setDailySales(int dailySales) {
                this.dailySales = dailySales;
            }

            public int getCommissionType() {
                return commissionType;
            }

            public void setCommissionType(int commissionType) {
                this.commissionType = commissionType;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getCouponReceiveNum() {
                return couponReceiveNum;
            }

            public void setCouponReceiveNum(int couponReceiveNum) {
                this.couponReceiveNum = couponReceiveNum;
            }

            public String getCouponLink() {
                return couponLink;
            }

            public void setCouponLink(String couponLink) {
                this.couponLink = couponLink;
            }

            public String getCouponEndTime() {
                return couponEndTime;
            }

            public void setCouponEndTime(String couponEndTime) {
                this.couponEndTime = couponEndTime;
            }

            public String getCouponStartTime() {
                return couponStartTime;
            }

            public void setCouponStartTime(String couponStartTime) {
                this.couponStartTime = couponStartTime;
            }

            public int getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public String getCouponConditions() {
                return couponConditions;
            }

            public void setCouponConditions(String couponConditions) {
                this.couponConditions = couponConditions;
            }

            public int getActivityType() {
                return activityType;
            }

            public void setActivityType(int activityType) {
                this.activityType = activityType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getMainPic() {
                return mainPic;
            }

            public void setMainPic(String mainPic) {
                this.mainPic = mainPic;
            }

            public String getMarketingMainPic() {
                return marketingMainPic;
            }

            public void setMarketingMainPic(String marketingMainPic) {
                this.marketingMainPic = marketingMainPic;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public double getDiscounts() {
                return discounts;
            }

            public void setDiscounts(double discounts) {
                this.discounts = discounts;
            }

            public double getCommissionRate() {
                return commissionRate;
            }

            public void setCommissionRate(double commissionRate) {
                this.commissionRate = commissionRate;
            }

            public int getCouponTotalNum() {
                return couponTotalNum;
            }

            public void setCouponTotalNum(int couponTotalNum) {
                this.couponTotalNum = couponTotalNum;
            }

            public int getHaitao() {
                return haitao;
            }

            public void setHaitao(int haitao) {
                this.haitao = haitao;
            }

            public String getActivityStartTime() {
                return activityStartTime;
            }

            public void setActivityStartTime(String activityStartTime) {
                this.activityStartTime = activityStartTime;
            }

            public String getActivityEndTime() {
                return activityEndTime;
            }

            public void setActivityEndTime(String activityEndTime) {
                this.activityEndTime = activityEndTime;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getShopLevel() {
                return shopLevel;
            }

            public void setShopLevel(int shopLevel) {
                this.shopLevel = shopLevel;
            }

            public double getDescScore() {
                return descScore;
            }

            public void setDescScore(double descScore) {
                this.descScore = descScore;
            }

            public int getBrand() {
                return brand;
            }

            public void setBrand(int brand) {
                this.brand = brand;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public int getHotPush() {
                return hotPush;
            }

            public void setHotPush(int hotPush) {
                this.hotPush = hotPush;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public String getItemLink() {
                return itemLink;
            }

            public void setItemLink(String itemLink) {
                this.itemLink = itemLink;
            }

            public int getTchaoshi() {
                return tchaoshi;
            }

            public void setTchaoshi(int tchaoshi) {
                this.tchaoshi = tchaoshi;
            }

            public String getDetailPics() {
                return detailPics;
            }

            public void setDetailPics(String detailPics) {
                this.detailPics = detailPics;
            }

            public double getDsrScore() {
                return dsrScore;
            }

            public void setDsrScore(double dsrScore) {
                this.dsrScore = dsrScore;
            }

            public double getDsrPercent() {
                return dsrPercent;
            }

            public void setDsrPercent(double dsrPercent) {
                this.dsrPercent = dsrPercent;
            }

            public double getShipScore() {
                return shipScore;
            }

            public void setShipScore(double shipScore) {
                this.shipScore = shipScore;
            }

            public double getShipPercent() {
                return shipPercent;
            }

            public void setShipPercent(double shipPercent) {
                this.shipPercent = shipPercent;
            }

            public double getServiceScore() {
                return serviceScore;
            }

            public void setServiceScore(double serviceScore) {
                this.serviceScore = serviceScore;
            }

            public double getServicePercent() {
                return servicePercent;
            }

            public void setServicePercent(double servicePercent) {
                this.servicePercent = servicePercent;
            }

            public int getTbcid() {
                return tbcid;
            }

            public void setTbcid(int tbcid) {
                this.tbcid = tbcid;
            }
        }
    }
}
