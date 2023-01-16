package com.fc.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author yangfucheng
 * @date 2022/10/24 14:48
 */
public class TreeUtils {

    /**
     * @param list             源数据
     * @param setChildListFn   设置递归的方法
     * @param idFn             获取id的方法
     * @param pidFn            获取父id的方法
     * @param getRootCondition 获取根节点的方法
     * @return 将List 转换成 Tree
     */
    public static <M, T> List<M> listToTree(List<M> list,
                                            Function<M, T> idFn,
                                            Function<M, T> pidFn,
                                            BiConsumer<M, List<M>> setChildListFn,
                                            Predicate<M> getRootCondition) {
        if (CollUtil.isEmpty(list)) return null;
        Map<T, List<M>> listMap = list.stream().collect(Collectors.groupingBy(pidFn));
        list.forEach(model -> setChildListFn.accept(model, listMap.get(idFn.apply(model))));
        return list.stream().filter(getRootCondition).collect(Collectors.toList());
    }

    public static <M> List<M> treeToList(List<M> source,
                                         Function<M, List<M>> getChildListFn,
                                         BiConsumer<M, List<M>> setChildListFn,
                                         Predicate<M> getRootCondition) {
        List<M> target = new ArrayList<>();
        if (CollUtil.isNotEmpty(source)) {
            treeToList(source, target, getChildListFn);
            target.forEach(model -> setChildListFn.accept(model, null));
            target.addAll(target.stream().filter(getRootCondition).collect(Collectors.toList()));
        }
        return target;
    }

    private static <F> void treeToList(List<F> source,
                                       List<F> target,
                                       Function<F, List<F>> getChildListFn) {
        if (CollUtil.isNotEmpty(source)) {
            target.addAll(source);
            source.forEach(model -> {
                List<F> childList = getChildListFn.apply(model);
                if (CollUtil.isNotEmpty(childList)) {
                    treeToList(childList, target, getChildListFn);
                }
            });
        }
    }
}

