package com.java.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private Integer sage;
    private String department;
    private String sclass;
    private String sphone;

    public void sleep() {
        System.out.println( "sleep..." );
    }
}
