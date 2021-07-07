package com.cloudcc.frame;

import com.cloudcc.frame.trigger.Tigger1;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FrameApplicationTests {

    @Test
    void contextLoads() {
    String tset = "2021-06-29 16:30:15.0";
        String[] split = tset.split("\\.");
        System.out.printf(split[0]);
    }

}
