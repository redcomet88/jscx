package jssvc.base;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description: 测试的基类
 * @Author: redcomet
 * @Date: 2018-10-02-13:16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:springmvc-*.xml"})
@Ignore
public class TestBasic {
}
