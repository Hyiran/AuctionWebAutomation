package com.framework.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class BeanFactory {
	private static Map<String, Object> map = null;
	private static Logger logger = Logger.getLogger(BeanFactory.class);

	static {
		load();
	}

	public static Object getBean(String beanName) {
		return map.get(beanName);
	}

	public static void load() {
		map = new HashMap<String, Object>();
		try {
			Document doc = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(Thread.currentThread().getContextClassLoader()
							.getResource("config/beanfactory.xml").getPath());
			NodeList daos = doc.getElementsByTagName("bean");
			Element e = null;
			int length = daos.getLength();
			for (int i = 0; i < length; i++) {
				e = (Element) daos.item(i);
				map.put(e.getAttribute("name"),
						Class.forName(e.getAttribute("class")).newInstance());
			}
			e = null;
			daos = null;
			doc = null;
		} catch (Exception e) {
			logger.error("执行BeanFactory.load()方法发生异常，异常信息：", e);
		}
	}
}
