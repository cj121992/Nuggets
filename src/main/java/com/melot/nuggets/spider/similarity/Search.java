package com.melot.nuggets.spider.similarity;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by 618 on 2018/1/12.
 *
 * @author lingfengsan
 */
@SuppressWarnings("rawtypes")
public interface Search extends Callable {
    Long search() throws IOException;
}