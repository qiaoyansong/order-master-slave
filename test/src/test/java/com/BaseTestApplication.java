package com;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 3:11 下午
 * description：测试基类（module test打包需要使用surefire：test进行打包，否则会提示找不到符号）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Starter.class })
public class BaseTestApplication {
}
