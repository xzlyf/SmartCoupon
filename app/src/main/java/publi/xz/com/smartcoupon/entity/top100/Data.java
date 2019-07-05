package publi.xz.com.smartcoupon.entity.top100;

public class Data {
    private String api_type;

    private String update_time;

    private int total_num;

    private String update_content;

    public void setApi_type(String api_type){
        this.api_type = api_type;
    }
    public String getApi_type(){
        return this.api_type;
    }
    public void setUpdate_time(String update_time){
        this.update_time = update_time;
    }
    public String getUpdate_time(){
        return this.update_time;
    }
    public void setTotal_num(int total_num){
        this.total_num = total_num;
    }
    public int getTotal_num(){
        return this.total_num;
    }
    public void setUpdate_content(String update_content){
        this.update_content = update_content;
    }
    public String getUpdate_content(){
        return this.update_content;
    }

}