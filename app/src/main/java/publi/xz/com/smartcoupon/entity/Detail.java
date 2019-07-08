package publi.xz.com.smartcoupon.entity;

public class Detail {


    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {

        private String api_type;
        private String update_time;
        private int total_num;
        private String update_content;

        public String getApi_type() {
            return api_type;
        }

        public void setApi_type(String api_type) {
            this.api_type = api_type;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public String getUpdate_content() {
            return update_content;
        }

        public void setUpdate_content(String update_content) {
            this.update_content = update_content;
        }
    }

    public static class ResultBean {

        private String ID;
        private String GoodsID;
        private String Title;
        private String D_title;
        private String Pic;
        private String Cid;
        private String Org_Price;
        private double Price;
        private String IsTmall;
        private String Sales_num;
        private String Dsr;
        private String SellerID;
        private String Commission;
        private String Commission_jihua;
        private String Commission_queqiao;
        private String Jihua_link;
        private String Que_siteid;
        private String Jihua_shenhe;
        private String Introduce;
        private String Quan_id;
        private String Quan_price;
        private String Quan_time;
        private String Quan_surplus;
        private String Quan_receive;
        private String Quan_condition;
        private Object Quan_m_link;
        private String Yongjin_type;
        private String Quan_link;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getGoodsID() {
            return GoodsID;
        }

        public void setGoodsID(String GoodsID) {
            this.GoodsID = GoodsID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getD_title() {
            return D_title;
        }

        public void setD_title(String D_title) {
            this.D_title = D_title;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getCid() {
            return Cid;
        }

        public void setCid(String Cid) {
            this.Cid = Cid;
        }

        public String getOrg_Price() {
            return Org_Price;
        }

        public void setOrg_Price(String Org_Price) {
            this.Org_Price = Org_Price;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getIsTmall() {
            return IsTmall;
        }

        public void setIsTmall(String IsTmall) {
            this.IsTmall = IsTmall;
        }

        public String getSales_num() {
            return Sales_num;
        }

        public void setSales_num(String Sales_num) {
            this.Sales_num = Sales_num;
        }

        public String getDsr() {
            return Dsr;
        }

        public void setDsr(String Dsr) {
            this.Dsr = Dsr;
        }

        public String getSellerID() {
            return SellerID;
        }

        public void setSellerID(String SellerID) {
            this.SellerID = SellerID;
        }

        public String getCommission() {
            return Commission;
        }

        public void setCommission(String Commission) {
            this.Commission = Commission;
        }

        public String getCommission_jihua() {
            return Commission_jihua;
        }

        public void setCommission_jihua(String Commission_jihua) {
            this.Commission_jihua = Commission_jihua;
        }

        public String getCommission_queqiao() {
            return Commission_queqiao;
        }

        public void setCommission_queqiao(String Commission_queqiao) {
            this.Commission_queqiao = Commission_queqiao;
        }

        public String getJihua_link() {
            return Jihua_link;
        }

        public void setJihua_link(String Jihua_link) {
            this.Jihua_link = Jihua_link;
        }

        public String getQue_siteid() {
            return Que_siteid;
        }

        public void setQue_siteid(String Que_siteid) {
            this.Que_siteid = Que_siteid;
        }

        public String getJihua_shenhe() {
            return Jihua_shenhe;
        }

        public void setJihua_shenhe(String Jihua_shenhe) {
            this.Jihua_shenhe = Jihua_shenhe;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        public String getQuan_id() {
            return Quan_id;
        }

        public void setQuan_id(String Quan_id) {
            this.Quan_id = Quan_id;
        }

        public String getQuan_price() {
            return Quan_price;
        }

        public void setQuan_price(String Quan_price) {
            this.Quan_price = Quan_price;
        }

        public String getQuan_time() {
            return Quan_time;
        }

        public void setQuan_time(String Quan_time) {
            this.Quan_time = Quan_time;
        }

        public String getQuan_surplus() {
            return Quan_surplus;
        }

        public void setQuan_surplus(String Quan_surplus) {
            this.Quan_surplus = Quan_surplus;
        }

        public String getQuan_receive() {
            return Quan_receive;
        }

        public void setQuan_receive(String Quan_receive) {
            this.Quan_receive = Quan_receive;
        }

        public String getQuan_condition() {
            return Quan_condition;
        }

        public void setQuan_condition(String Quan_condition) {
            this.Quan_condition = Quan_condition;
        }

        public Object getQuan_m_link() {
            return Quan_m_link;
        }

        public void setQuan_m_link(Object Quan_m_link) {
            this.Quan_m_link = Quan_m_link;
        }

        public String getYongjin_type() {
            return Yongjin_type;
        }

        public void setYongjin_type(String Yongjin_type) {
            this.Yongjin_type = Yongjin_type;
        }

        public String getQuan_link() {
            return Quan_link;
        }

        public void setQuan_link(String Quan_link) {
            this.Quan_link = Quan_link;
        }
    }
}
