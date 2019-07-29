package com.zheng.common.util;

import static org.mockito.Matchers.anyDouble;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.diffblue.deeptestutils.Reflector;
import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import com.zheng.common.util.Paginator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
public class PaginatorTest {

	/* testedClasses: Paginator */

	@Test
	public void getTotalOutputZero() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals(0L, objectUnderTest.getTotal());
	}

	@Test
	public void setTotalInputZeroOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setTotal(0L);
		Assert.assertEquals(0L, objectUnderTest.getTotalPage());
	}

	@Test
	public void getPageOutputPositive() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals(1, objectUnderTest.getPage());
	}

	@Test
	public void setPageInputZeroOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setPage(0);
		Assert.assertEquals(0, objectUnderTest.getPage());
	}

	@Test
	public void getTotalPageOutputPositive() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals(1L, objectUnderTest.getTotalPage());
	}

	@Test
	public void setTotalPageInputZeroOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setTotalPage(0L);
		Assert.assertEquals(0L, objectUnderTest.getTotalPage());
	}

	@Test
	public void getRowsOutputPositive() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals(10, objectUnderTest.getRows());
	}

	@Test
	public void setRowsInputPositiveOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setRows(1);
		Assert.assertEquals(0L, objectUnderTest.getTotalPage());
		Assert.assertEquals(1, objectUnderTest.getRows());
	}

	@Test
	public void setRowsInputPositiveOutputVoid2() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setRows(10_001);
		Assert.assertEquals(0L, objectUnderTest.getTotalPage());
		Assert.assertEquals(10_000, objectUnderTest.getRows());
	}

	@Test
	public void getStepOutputPositive() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals(5, objectUnderTest.getStep());
	}

	@Test
	public void setStepInputZeroOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setStep(0);
		Assert.assertEquals(0, objectUnderTest.getStep());
	}

	@Test
	public void getParamOutputNotNull() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals("page", objectUnderTest.getParam());
	}

	@Test
	public void setParamInputNotNullOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setParam("3");
		Assert.assertEquals("3", objectUnderTest.getParam());
	}

	@Test
	public void getUrlOutputNotNull() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals("", objectUnderTest.getUrl());
	}


	@Test
	public void setUrlInputNotNullOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setUrl("3");
		Assert.assertEquals("3", objectUnderTest.getUrl());
	}

	@Test
	public void getQueryOutputNotNull() {
		final Paginator objectUnderTest = new Paginator();
		Assert.assertEquals("", objectUnderTest.getQuery());
	}

	@Test
	public void setQueryInputNotNullOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.setQuery("3");
		Assert.assertEquals("3", objectUnderTest.getQuery());
	}

	@Test
	public void initTotalPageOutputVoid() {
		final Paginator objectUnderTest = new Paginator();
		objectUnderTest.initTotalPage();
		Assert.assertEquals(0L, objectUnderTest.getTotalPage());
	}

	@PrepareForTest({Paginator.class, Math.class})
	@Test
	public void getHtmlOutputNotNull() throws Exception,
			InvocationTargetException {
		PowerMockito.mockStatic(Math.class);
		final Paginator objectUnderTest = new Paginator();
		Reflector.setField(objectUnderTest, "total", 0L);
		objectUnderTest.setStep(0);
		objectUnderTest.setUrl(null);
		objectUnderTest.setQuery(null);
		objectUnderTest.setParam(null);
		objectUnderTest.setPage(0);
		objectUnderTest.setTotalPage(0L);
		Reflector.setField(objectUnderTest, "rows", 2);
		PowerMockito.when(Math.ceil(anyDouble()))
				.thenReturn(-0x1.ffffffffffffep+31 /* -4.29497e+09 */);
		Assert.assertEquals("", objectUnderTest.getHtml());
	}

	@Test
	public void getHtmlOutputNotNull2() throws Exception,
			InvocationTargetException {
		PowerMockito.mockStatic(Math.class);
		final Paginator objectUnderTest = new Paginator();
		Reflector.setField(objectUnderTest, "total", 0L);
		objectUnderTest.setStep(0);
		objectUnderTest.setUrl(null);
		objectUnderTest.setQuery("foo");
		objectUnderTest.setParam("foo");
		objectUnderTest.setPage(0);
		objectUnderTest.setTotalPage(0L);
		Reflector.setField(objectUnderTest, "rows", -786_432);
		PowerMockito.when(Math.ceil(anyDouble()))
				.thenReturn(-0x1.ffffffffffffep+31 /* -4.29497e+09 */);
		Assert.assertEquals("", objectUnderTest.getHtml());
	}
}
