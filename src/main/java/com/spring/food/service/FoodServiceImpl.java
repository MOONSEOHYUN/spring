package com.spring.food.service;

import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Service
public class FoodServiceImpl implements FoodService{
	private static final Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);

	@Override
	public List<Map<String, String>> food(String key, String value) throws Exception {
//    	식품 파싱
//    	String servicekey="befee95fb28648608ab0";
        StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/befee95fb28648608ab0/C002/xml/1/1000"); /*URL*/
        if(!value.equals("")) {
        	urlBuilder.append("/" + URLEncoder.encode(key,"UTF-8") + "=" + URLEncoder.encode(value,"UTF-8")); 
        }
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        InputSource is=new InputSource(new StringReader(sb.toString()));
    	Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
    	
    	List<Map<String, String>> foodlist=new ArrayList<>();
    	
    	NodeList nlist =  doc.getElementsByTagName("row");
    	logger.info(nlist.toString());
		
		for(int i=0; i<nlist.getLength(); i++) {
    		NodeList flist=nlist.item(i).getChildNodes();
    		System.out.println("flist: "+flist);
    		
    		Map<String, String> fmap=new HashMap<String, String>();
    		for(int j=0; j<flist.getLength(); j++) {
    			Node node=flist.item(j);
    			String nname=node.getNodeName();
    			if(nname.equals("PRDLST_REPORT_NO")){
    				logger.info("PRDLST_REPORT_NO(품목제조번호): "+node.getTextContent());
    				fmap.put("PRDLST_REPORT_NO",node.getTextContent());
    			}else if(nname.equals("PRDLST_DCNM")) {
    				logger.info("PRDLST_DCNM(유형): "+node.getTextContent());
    				fmap.put("PRDLST_DCNM",node.getTextContent());
    			}else if(nname.equals("PRDLST_NM")){
    				logger.info("PRDLST_NM(제품명): "+node.getTextContent());
    				fmap.put("PRDLST_NM",node.getTextContent());
    			}else if(nname.equals("RAWMTRL_NM")){
    				logger.info("RAWMTRL_NM(원재료): "+node.getTextContent());
    				fmap.put("RAWMTRL_NM",node.getTextContent());
    			}else if(nname.equals("BSSH_NM")) {
    				logger.info("BSSH_NM(업소명): "+node.getTextContent());
    				fmap.put("BSSH_NM",node.getTextContent());
    			}
    		
    		}
    		foodlist.add(fmap);
    	}
    	
    	logger.info(foodlist.toString());
		
		return foodlist;
	}

	

}
