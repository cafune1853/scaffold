package com.doggy.groupId.doggy.module.web.annotation;

import java.lang.annotation.*;

/**
 * 标记由子容器来加载这个bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MvcLoad {
}
