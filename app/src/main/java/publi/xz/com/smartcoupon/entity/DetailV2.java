package publi.xz.com.smartcoupon.entity;

import java.io.Serializable;

/**
 * 实现Serializable接口是为了让Intent可以传输
 */
public class DetailV2 implements Serializable {
    private String mainPic;
    private String actualPrice;
    private String originalPrice;
    private String shopType;
    private String dtitle;
    private String desc;
    private String monthSales;
    private String hotPush;
    private String couponEndTime;
    private String couponPrice;
    private String marketingMainPic;

    public String getCouponLink() {
        return CouponLink;
    }

    public void setCouponLink(String couponLink) {
        CouponLink = couponLink;
    }

    private String CouponLink;

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(String monthSales) {
        this.monthSales = monthSales;
    }

    public String getHotPush() {
        return hotPush;
    }

    public void setHotPush(String hotPush) {
        this.hotPush = hotPush;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getMarketingMainPic() {
        return marketingMainPic;
    }

    public void setMarketingMainPic(String marketingMainPic) {
        this.marketingMainPic = marketingMainPic;
    }
}
