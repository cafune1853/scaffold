package com.doggy.groupId.doggy.module.provider.service;

import com.doggy.groupId.doggy.module.provider.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author doggy
 *         Created on 2017-07-12.
 */
@Slf4j
@Service("testService")
public class TestServiceImpl implements com.doggy.groupId.doggy.module.api.service.TestService {
    @Autowired
    private Environment environment;
    @Autowired
    private TestDao testDao;
    @Autowired
    private com.doggy.groupId.doggy.module.api.service.TestService testService;

    @Override
    public void test(){
        log.info("test method");
    }
}
