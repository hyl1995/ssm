package com.hyl.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class XmlUtils {
    public static Document readXml(String resource) throws DocumentException {
        return DocumentHelper.parseText(resource);
    }

    public static Node getNode(Document document, String nodeName) {
        return document.selectSingleNode(nodeName);
    }

    public static void main(String[] args) throws DocumentException {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Packet version=\"1.0\" type=\"RESPONSE\">\n" +
                "\t<Head>\n" +
                "\t\t<CRequestType>340002</CRequestType>\n" +
                "\t\t<CBusiChnl>maifen</CBusiChnl>\n" +
                "\t\t<ResultCode>0000</ResultCode>\n" +
                "\t\t<ResultMessage>退保成功</ResultMessage>\n" +
                "\t</Head>\n" +
                "\t<Body>\n" +
                "\t\t<EdrResList>\n" +
                "\t\t\t<EdrRes>\n" +
                "\t\t\t\t<PlyNo>保单号</PlyNo>\n" +
                "\t\t\t\t<ReturnTm>2017-02-28 16:17:46</ReturnTm>\n" +
                "\t\t\t\t<ResultCode>0000</ResultCode>\n" +
                "\t\t\t\t<ResultMessage>成功</ResultMessage>\n" +
                "\t\t\t</EdrRes>\n" +
                "\t\t</EdrResList>\n" +
                "\t</Body>\n" +
                "</Packet>  ";
        Document document =readXml(str);
        Node node = getNode(document, "/Packet/Head/ResultCode");
        System.out.println(node);
    }
}
