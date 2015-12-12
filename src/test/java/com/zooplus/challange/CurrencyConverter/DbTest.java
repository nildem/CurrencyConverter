package com.zooplus.challange.CurrencyConverter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zooplus.challange.ProjectLoader;
import com.zooplus.challange.cc.CurrencyConverterAdaptor;
import com.zooplus.challange.cc.CurrencyConverterFactory;
import com.zooplus.challange.dao.UserDAO;
import com.zooplus.challange.pojo.User;
import com.zooplus.challange.util.CryptoUtil;

public class DbTest {
    private static ProjectLoader projectLoader = null;
    UserDAO userDAO = new UserDAO();
    User testUser = null;

//    @Before
//    public void setup() throws Exception {
//        projectLoader = new ProjectLoader();
//        projectLoader.init("C:\\data\\config\\zooplus\\app.properties");
//    }
//
//    @Test
//    public void userManipulation() {
//        User s = new User();
//        s.setName("Nildem");
//        s.setSurname("Ucok");
//        s.setEmail("nildemdemir@yahoo.com");
//        s.setPassHash(CryptoUtil.getBase64ofFIMDigest("123456"));
//        s.setAddressLine1("Line 1 blabla");
//        s.setAddressLine2("Line 2 tatata");
//        s.setCity("Istanbul");
//        s.setCountry("TR");
//        s.setPostalCode("ABC123");
//        s = userDAO.addUser(s);
//        User currentUser = userDAO.findUserById(s.getId());
//        Assert.assertEquals("Nildem", currentUser.getName());
//        Assert.assertEquals("Ucok", currentUser.getSurname());
//        Assert.assertEquals("nildemdemir@yahoo.com", currentUser.getEmail());
//        Assert.assertEquals(CryptoUtil.getBase64ofFIMDigest("123456"), currentUser.getPassHash());
//        Assert.assertEquals("Line 1 blabla", currentUser.getAddressLine1());
//        Assert.assertEquals("Line 2 tatata", currentUser.getAddressLine2());
//        Assert.assertEquals("Istanbul", currentUser.getCity());
//        Assert.assertEquals("TR", currentUser.getCountry());
//        Assert.assertEquals("ABC123", currentUser.getPostalCode());
//    }

//    @Test
//    public void OpenExchangeRatesAdapter() {
//        CurrencyConverterAdaptor ccAdaptor = CurrencyConverterFactory.getAdaptor();
//        Assert.assertNotEquals(null, ccAdaptor.getRates());
//        Assert.assertEquals(true, ccAdaptor.getRates().size() > 0);
//    }
}
