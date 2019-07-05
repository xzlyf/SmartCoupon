package publi.xz.com.smartcoupon.utils;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.entity.Popular;

public class CommonUtil {
    /**
     * 将一个集合拆分成制定长度的若干个集合
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List<?>> splitList(List<?> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<?>> result = new ArrayList<List<?>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    /**
     * 将一个集合拆分成制定长度的若干个集合
     */

    public static List<List<Popular.ResultBean>> groupList(List<Popular.ResultBean> list) {
        List<List<Popular.ResultBean>> listGroup = new ArrayList<List<Popular.ResultBean>>();
        int listSize = list.size();
        //子集合的长度
        int toIndex = 10;
        for (int i = 0; i < list.size(); i += 10) {
            if (i + 10 > listSize) {
                toIndex = listSize - i;
            }
            List<Popular.ResultBean> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

}
