package com.will.redpacket;

import com.will.redpacket.service.UserRedPacketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.LinkedBlockingQueue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedpacketApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
    public void testGrabRedPacket() throws Exception{
        mockMvc.perform(post("/userRedPacket/grabRedPacket").param("redPacketId","1")).andExpect(status().isOk());
    }

    @Test
    public void testGrabRedPacket2() throws Exception{
        mockMvc.perform(post("/userRedPacket/simpleTest").param("redPacketId","1")).andExpect(status().isOk());
    }

}
