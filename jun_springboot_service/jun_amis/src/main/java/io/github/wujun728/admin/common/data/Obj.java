package io.github.wujun728.admin.common.data;

import lombok.Data;

@Data
public class Obj<T> {
    T value;

    public Obj(T value) {
        this.value = value;
    }
}
