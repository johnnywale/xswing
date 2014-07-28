package com.jovx.xswing.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBXMLHandler {

	public static void marshal(Object o, Writer writer) throws Throwable {
		JAXBContext context = JAXBContext.newInstance(o.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(o, writer);
		writer.close();

	}

	public static <T> void marshal(List<T> t, Class<T> clazz)
			throws IOException, JAXBException {
		ListPersisHelper<T> selectedFile = new ListPersisHelper<T>(clazz);
		selectedFile.setContent(t);

		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(selectedFile.getClazz()
				.getSimpleName() + ".xml"));
		JAXBContext context = JAXBContext.newInstance(selectedFile.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(selectedFile, writer);
		writer.close();
	}

	public static <T> ListPersisHelper<T> unmarshal(Class<T> clazz)
			throws JAXBException {
		File f = new File(clazz.getSimpleName() + ".xml");
		if (f.exists()) {
			JAXBContext context = JAXBContext
					.newInstance(ListPersisHelper.class);
			Unmarshaller um = context.createUnmarshaller();
			ListPersisHelper listPersisHelper = (ListPersisHelper) um
					.unmarshal(new File(clazz.getSimpleName() + ".xml"));
			return listPersisHelper;
		} else {
			return new ListPersisHelper<T>();
		}

	}

	public static <T> T unmarshal(InputStream inputStream, Class<T> clazz)
			throws Throwable {

		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller um = context.createUnmarshaller();
		T result = (T) um.unmarshal(inputStream);
		return result;
	}

}