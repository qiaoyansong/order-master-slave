package com.common;

import com.BaseTestApplication;
import com.utils.LogFactory;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 3:10 下午
 * description：
 */
public class TestLog extends BaseTestApplication {

    private static Logger serviceLog = LogFactory.SERVICE_LOG;


    @Test
    public void testGetLog() {
        serviceLog.info("这是一个测试2");
    }

    @Test
    public void testWarnAndError() {
        serviceLog.warn("这是一个测试");
        serviceLog.error("这是一个测试");
    }
}
