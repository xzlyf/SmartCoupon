package publi.xz.com.smartcoupon.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Baoyou9_9  {

    public long time;
    public String code;
    public String msg;
    public DataBean data ;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public static class DataBean  {

        public String totalNum;
        public String pageId;
        public List<ListBean> list = new ArrayList<>();

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
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

            public String id;
            public String goodsId;
            public String title;
            public String dtitle;
            public double originalPrice;
            public double actualPrice;
            public String shopType;
            public String goldSellers;
            public String monthSales;
            public String twoHoursSales;
            public String dailySales;
            public String commissionType;
            public String desc;
            public String couponReceiveNum;
            public String couponLink;
            public String couponEndTime;
            public String couponStartTime;
            public String couponPrice;
            public String couponConditions;
            public String activityType;
            public String createTime;
            public String mainPic;
            public String marketingMainPic;
            public String sellerId;
            public String cid;
            public double discounts;
            public double commissionRate;
            public String couponTotalNum;
            public String haitao;
            public String activityStartTime;
            public String activityEndTime;
            public String shopName;
            public String shopLevel;
            public double descScore;
            public String brand;
            public String brandId;
            public String brandName;
            public String hotPush;
            public String teamName;
            public String itemLink;
            public String tchaoshi;
            public String detailPics;
            public double dsrScore;
            public double dsrPercent;
            public double shipScore;
            public double shipPercent;
            public double serviceScore;
            public double servicePercent;
            public String tbcid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getShopType() {
                return shopType;
            }

            public void setShopType(String shopType) {
                this.shopType = shopType;
            }

            public String getGoldSellers() {
                return goldSellers;
            }

            public void setGoldSellers(String goldSellers) {
                this.goldSellers = goldSellers;
            }

            public String getMonthSales() {
                return monthSales;
            }

            public void setMonthSales(String monthSales) {
                this.monthSales = monthSales;
            }

            public String getTwoHoursSales() {
                return twoHoursSales;
            }

            public void setTwoHoursSales(String twoHoursSales) {
                this.twoHoursSales = twoHoursSales;
            }

            public String getDailySales() {
                return dailySales;
            }

            public void setDailySales(String dailySales) {
                this.dailySales = dailySales;
            }

            public String getCommissionType() {
                return commissionType;
            }

            public void setCommissionType(String commissionType) {
                this.commissionType = commissionType;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCouponReceiveNum() {
                return couponReceiveNum;
            }

            public void setCouponReceiveNum(String couponReceiveNum) {
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

            public String getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(String couponPrice) {
                this.couponPrice = couponPrice;
            }

            public String getCouponConditions() {
                return couponConditions;
            }

            public void setCouponConditions(String couponConditions) {
                this.couponConditions = couponConditions;
            }

            public String getActivityType() {
                return activityType;
            }

            public void setActivityType(String activityType) {
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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
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

            public String getCouponTotalNum() {
                return couponTotalNum;
            }

            public void setCouponTotalNum(String couponTotalNum) {
                this.couponTotalNum = couponTotalNum;
            }

            public String getHaitao() {
                return haitao;
            }

            public void setHaitao(String haitao) {
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

            public String getShopLevel() {
                return shopLevel;
            }

            public void setShopLevel(String shopLevel) {
                this.shopLevel = shopLevel;
            }

            public double getDescScore() {
                return descScore;
            }

            public void setDescScore(double descScore) {
                this.descScore = descScore;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
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

            public String getHotPush() {
                return hotPush;
            }

            public void setHotPush(String hotPush) {
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

            public String getTchaoshi() {
                return tchaoshi;
            }

            public void setTchaoshi(String tchaoshi) {
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

            public String getTbcid() {
                return tbcid;
            }

            public void setTbcid(String tbcid) {
                this.tbcid = tbcid;
            }
        }
    }
}
