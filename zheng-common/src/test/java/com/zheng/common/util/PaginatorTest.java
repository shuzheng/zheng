package com.zheng.common.util;

import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

@RunWith(PowerMockRunner.class)
public class PaginatorTest {

  @PrepareForTest({Paginator.class, HttpServletRequest.class})
  @Test
  public void testConstructor() throws Exception {

    // Arrange
    final HttpServletRequest request = PowerMockito
        .mock(HttpServletRequest.class);
    final Method getRequestURIMethod =
        DTUMemberMatcher.method(
            HttpServletRequest.class, "getRequestURI");
    PowerMockito.doReturn("BAZ").when(request, getRequestURIMethod)
        .withNoArguments();
    final Method getQueryStringMethod =
        DTUMemberMatcher.method(
            HttpServletRequest.class, "getQueryString");
    PowerMockito.doReturn("1a 2b 3c").when(
        request, getQueryStringMethod)
        .withNoArguments();

    // Act, creating object to test constructor
    final Paginator objectUnderTest =
        new Paginator(0L, 0, 1, request);

    // Assert side effects
    Assert.assertEquals(5, objectUnderTest.getStep());
    Assert.assertEquals("BAZ", objectUnderTest.getUrl());
    Assert.assertEquals("1a 2b 3c", objectUnderTest.getQuery());
    Assert.assertEquals("page", objectUnderTest.getParam());
    Assert.assertEquals(1, objectUnderTest.getPage());
    Assert.assertEquals(1, objectUnderTest.getRows());
  }

  @Test
  public void testSetTotal() {
    final Paginator objectUnderTest = new Paginator();

    objectUnderTest.setTotal(14L);

    Assert.assertEquals(2L, objectUnderTest.getTotalPage());
  }

  @Test
  public void testSetRowsInput1() {
    final Paginator objectUnderTest = new Paginator();
    final int rows = 1;

    objectUnderTest.setRows(rows);

    Assert.assertEquals(0L, objectUnderTest.getTotalPage());
    Assert.assertEquals(1, objectUnderTest.getRows());
  }

  @Test
  public void testSetRowsInput10001() {

    final Paginator objectUnderTest = new Paginator();

    objectUnderTest.setRows(10_001);

    Assert.assertEquals(0L, objectUnderTest.getTotalPage());
    Assert.assertEquals(10_000, objectUnderTest.getRows());
  }

  @Test
  public void testInitTotalPageDefault() {
    final Paginator objectUnderTest = new Paginator();

    objectUnderTest.initTotalPage();

    Assert.assertEquals(0L, objectUnderTest.getTotalPage());
    Assert.assertEquals(1, objectUnderTest.getPage());
  }

  @Test
  public void testInitTotalPageInputTotal100Rows6() {
    final Paginator objectUnderTest = new Paginator();
    objectUnderTest.setTotal(100);
    objectUnderTest.setRows(6);

    objectUnderTest.initTotalPage();

    Assert.assertEquals(17, objectUnderTest.getTotalPage());
  }

  @Test
  public void testInitTotalPageCheckPage4() {
    final Paginator objectUnderTest = new Paginator();
    objectUnderTest.setPage(14);
    objectUnderTest.setTotal(40);
    objectUnderTest.setRows(10);

    objectUnderTest.initTotalPage();

    Assert.assertEquals(4, objectUnderTest.getTotalPage());
    Assert.assertEquals(4, objectUnderTest.getPage());
  }

}
