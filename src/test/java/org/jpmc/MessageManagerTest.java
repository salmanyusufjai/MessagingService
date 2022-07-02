package org.jpmc;

import org.jpmc.service.MessageManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageManagerTest {
    MessageManager messageManager;

    @Before
    public void initialise() {
        messageManager = MessageManager.getMessageManagerInstance();
    }

    @Test()
    public void aStartServiceTest() {
        boolean isServiceStart = messageManager.startService();
        Assert.assertTrue(isServiceStart);
    }
    @Test(expected = IllegalStateException.class)
    public void bStartServiceIfAlreadyStartTest() {
        messageManager.startService();
    }

    @Test
    public void cSendMessageToManager() {
        boolean messageAdded = messageManager.addMessage("ORANGE,5,P,5");
        Assert.assertTrue(messageAdded);
    }

    @Test()
    public void dStopServiceTest() {
        boolean isServiceStop = messageManager.stopService();
        Assert.assertTrue(isServiceStop);
    }


    @Test(expected = IllegalStateException.class)
    public void eSendMessageToManagerWhenMessageServiceIsStop() {
       messageManager.addMessage("ORANGE,5,P,5");
    }


    @Test(expected = IllegalStateException.class)
    public void fStopServiceIfAlreadyStopTest() {
      messageManager.stopService();
    }


}
