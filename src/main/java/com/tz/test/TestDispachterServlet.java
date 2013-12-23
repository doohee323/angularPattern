package com.tz.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : TZ
 * 프로그램 :
 * 설    명 : 서블릿 테스트를 위해 사용되는 테스트 서블릿
 * 작 성 자 : TZ
 * 작성일자 : 2013-02-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2013-02-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class TestDispachterServlet extends DispatcherServlet	{
	/**
	 *
	 */
	private static final long serialVersionUID = -868007902565242363L;

	private String location;

	/**
	 * <pre>
	 * 지정된 위치의 설정 파일을 읽어 정의된 빈들을 객체화한다.
	 * </pre>
	 *
	 * @param location 설정파일의 위치
	 */
	public TestDispachterServlet(String location) {
		this.location = location;

		try {
			this.init(new MockServletConfig());
		} catch (ServletException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.FrameworkServlet#createWebApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	protected WebApplicationContext createWebApplicationContext(
			ApplicationContext parent) {
		AbstractRefreshableWebApplicationContext ctx = new AbstractRefreshableWebApplicationContext() {

			@Override
			protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
					throws BeansException, IOException {
				XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
				reader.loadBeanDefinitions(location);
			}
		};

		ctx.setServletContext(getServletContext());
		ctx.setServletConfig(getServletConfig());
		ctx.refresh();

		return ctx;
	}
}