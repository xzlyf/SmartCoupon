package publi.xz.com.smartcoupon.entity;

import java.util.ArrayList;
import java.util.List;

public class PPBrand {


    private long time;
    private int code;
    private String msg;
    private List<DataBean> data = new ArrayList<>();

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    //追加数据
    public void add(PPBrand ppBrand){
        this.data.addAll(ppBrand.getData());
    }

    public static class DataBean {

        private int brandId;
        private String brandName;
        private String brandLogo;
        private String brandEnglish;
        private String name;
        private String sellerId;
        private int brandScore;
        private String location;
        private String establishTime;
        private String belongTo;
        private String position;
        private String consumer;
        private String label;

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandLogo() {
            return brandLogo;
        }

        public void setBrandLogo(String brandLogo) {
            this.brandLogo = brandLogo;
        }

        public String getBrandEnglish() {
            return brandEnglish;
        }

        public void setBrandEnglish(String brandEnglish) {
            this.brandEnglish = brandEnglish;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public int getBrandScore() {
            return brandScore;
        }

        public void setBrandScore(int brandScore) {
            this.brandScore = brandScore;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getEstablishTime() {
            return establishTime;
        }

        public void setEstablishTime(String establishTime) {
            this.establishTime = establishTime;
        }

        public String getBelongTo() {
            return belongTo;
        }

        public void setBelongTo(String belongTo) {
            this.belongTo = belongTo;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getConsumer() {
            return consumer;
        }

        public void setConsumer(String consumer) {
            this.consumer = consumer;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}