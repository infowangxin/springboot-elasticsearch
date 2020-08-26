package com.wx.util;

import org.apache.commons.collections4.IteratorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Iterator util
 *
 * @author wangxin65
 * @date 2020-08-26 14:29
 */
public class IteratorUtil {

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        if (null != iterable) {
            list = IteratorUtils.toList(iterable.iterator());
        }
        return list;
    }
}
