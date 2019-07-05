package publi.xz.com.smartcoupon.entity.top100;

import java.util.List;

public class Root {
    private Data data;

    private List<Result> result ;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setResult(List<Result> result){
        this.result = result;
    }
    public List<Result> getResult(){
        return this.result;
    }

}