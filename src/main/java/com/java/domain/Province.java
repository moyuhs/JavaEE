package com.java.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 省份对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    private int id;
    private String name;
}
