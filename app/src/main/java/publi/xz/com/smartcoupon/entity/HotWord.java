package publi.xz.com.smartcoupon.entity;

import java.util.List;

public class HotWord {


    private int er_code;
    private String er_msg;
    private List<DataBean> data;

    public int getEr_code() {
        return er_code;
    }

    public void setEr_code(int er_code) {
        this.er_code = er_code;
    }

    public String getEr_msg() {
        return er_msg;
    }

    public void setEr_msg(String er_msg) {
        this.er_msg = er_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String rank;
        private String word;
        private String total;
        private String change;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getChange() {
            return change;
        }

        public void setChange(String change) {
            this.change = change;
        }
    }
}
