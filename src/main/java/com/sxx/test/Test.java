package com.sxx.test;

import com.sxx.dto.ServerDto;
import com.sxx.util.FileUtil;

public class Test {
    @org.junit.Test
    public void readXmlTest(){
        ServerDto dto = FileUtil.readXMLByDom4j();
        dto.getInterfaceFullName();
        System.out.print("interface:"+dto.getInterfaceFullName()+","+"impl:"+dto.getImplFullName());
    }
}
