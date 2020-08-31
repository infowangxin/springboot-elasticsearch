package com.wx.util;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxin65
 * @date 2020-08-31 18:05
 */
public class BeanCopyUtil extends BeanUtils {

    public static <T, S> List<S> copyList(List<T> fromList, Class<S> toObj) {
        if (CollectionUtils.isEmpty(fromList)) {
            return null;
        }
        if (null == toObj) {
            return null;
        }
        List<S> toList = new ArrayList<>();
        fromList.forEach(f -> {
            S t = null;
            try {
                t = toObj.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            copyProperties(f, t);
            toList.add(t);
        });
        return toList;
    }

}
