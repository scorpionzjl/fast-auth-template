package com.chachae.core.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/11/13 15:43
 */
@Component
public class BeanCopyUtil {
  private static Map<String, BeanCopier> map = Maps.newHashMap();
  /**
   * 对象复制
   *
   * @param obj 被复制对象，为空会抛出异常
   * @param clazz 复制类型
   * @return T
   */
  public static <T> T copyObject(Object obj, Class<T> clazz) {
    T obj2;
    try {
      obj2 = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      return null;
    }
    String name = getClassName(obj.getClass(), clazz);
    BeanCopier beanCopier;
    if (map.containsKey(name)) {
      beanCopier = map.get(name);
    } else {
      beanCopier = BeanCopier.create(obj.getClass(), clazz, false);
      map.put(name, beanCopier);
    }
    beanCopier.copy(obj, obj2, null);
    return obj2;
  }

  /**
   * 复制队列
   *
   * @param list 被复制队列
   * @param <T> 复制类型
   * @return T
   */
  public static <T> List<T> copyList(List<?> list, Class<T> clazz) {
    if (CollectionUtil.isEmpty(list)) {
      return null;
    }
    List<T> resultList = Lists.newLinkedList();
    for (Object obj1 : list) {
      T t = copyObject(obj1, clazz);
      resultList.add(t);
    }
    return resultList;
  }

  /**
   * 获取类命
   *
   * @param class1 obj1
   * @param class2 obj2
   * @return 类名
   */
  private static String getClassName(Class<?> class1, Class<?> class2) {
    return class1.getName() + class2.getName();
  }
}
