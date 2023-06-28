package com.asan.couirertracking.utility;
@FunctionalInterface
public interface SplitLocationFunction<T,U, R> {
    T apply(U stringValue, R splitChar);
}
