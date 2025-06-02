package com.example.backend.common.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListData<T> {
    private final List<T> list;
    private long total;
}
