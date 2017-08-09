package com.doggy.groupId.doggy.module.provider.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author doggy
 *         Created on 2017-07-15.
 */
@Data
public class Test {
    @Column(name = "id")
    private long idc;
    @Column(name = "name")
    private String name;
}
