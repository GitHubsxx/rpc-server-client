package com.sxx.util;

import com.sxx.dto.ServerDto;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class FileUtil {
    public static ServerDto readProperties(){
        InputStream is = null;
        ServerDto dto = new ServerDto();
        Properties properties = new Properties();
        try {
            is = FileUtil.class.getClassLoader().getResourceAsStream("config/server.properties");
            properties.load(is);
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            dto.setHost(host);
            dto.setPort(Integer.parseInt(port));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dto;
    }
    public static ServerDto readXMLByDom4j(){
        SAXReader reader = new SAXReader();
        InputStream is = null;
        ServerDto dto = null;
        try {
            is = FileUtil.class.getClassLoader().getResourceAsStream("serviceXml/service.xml");
            Document read = reader.read(is);
            Element interfaces = read.getRootElement();
            Iterator iterator = interfaces.elementIterator();
            while (iterator.hasNext()) {
                Element interface_ = (Element) iterator.next();
                List<Attribute> attributes = interface_.attributes();
                dto = new ServerDto();
                for (Attribute att : attributes) {
                    String name = att.getName();
                    if("fullClassName".equals(name)){
                        String value = att.getValue();
                        dto.setInterfaceFullName(value);
                    }
                }
                Iterator iterator1 = interface_.elementIterator();
                while (iterator1.hasNext()) {
                    Element impls = (Element) iterator1.next();
                    String stringValue = impls.getStringValue();
                    dto.setImplFullName(stringValue);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            if(is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dto;
    }
}
