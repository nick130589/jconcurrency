package com.sergrosh.concurrency.forkjoin;

import java.util.Collection;

/**
 * Created by sroshchupkin on 20/11/15.
 */
public interface Node {
    Collection<Node> getChildren();

    long getValue();
}
