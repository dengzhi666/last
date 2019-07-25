package com;

import com.baizhi.CmfzZhenglApplication;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {CmfzZhenglApplication.class})
@RunWith(SpringRunner.class)
public class TestGoeasy {

    @Test
    public void m1(){

        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-6c8fedfaac52425286c22b50d4ba6770");

        goEasy.publish("demo_channel", "Hello world!");

    }
}
