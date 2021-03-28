package com.spring.food.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.spring.food.dao.FoodAdditivesDAO;
import com.spring.food.dto.FoodAdditivesDTO;

@Service
public class FoodAdditivesServiceImpl implements FoodAdditivesService{
	private static final Logger logger = LoggerFactory.getLogger(FoodAdditivesServiceImpl.class);
	
	@Autowired
	private FoodAdditivesDAO fadao;
	
//	List<Map<String, String>>
	
	@Transactional
	@Override
	public void FoodAdditives() throws Exception {
//    	식품첨가물 파싱
//    	String servicekey="befee95fb28648608ab0";
//		개수: 6819개
		
        for(int x=1; x<7000; x=x+1000) {
        	String start=Integer.toString(x);
        	String end=Integer.toString(x+999);
        	
        	StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/befee95fb28648608ab0/I0950/xml/"+start+"/"+end); /*URL*/
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
	        
	        InputSource is=new InputSource(new StringReader(sb.toString()));
	    	Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    	
	    	
	    	NodeList nlist =  doc.getElementsByTagName("row");
	    	
			for(int i=0; i<nlist.getLength(); i++) {
	    		NodeList falist=nlist.item(i).getChildNodes();
	    		
	    		Map<String, String> famap=new HashMap<String, String>();
	    		FoodAdditivesDTO fadto= new FoodAdditivesDTO();
	    		for(int j=0; j<falist.getLength(); j++) {
	    			Node node=falist.item(j);
	    			String nname=node.getNodeName();
	    			
	    			if(nname.equals("PRDLST_CD")) {
	    				if(fadao.foodAddSelectOne(node.getTextContent())!=null) {
	    					break;
	    				}
	    				
	    				fadto.setPcode(node.getTextContent());
	    				System.out.println("pcode: "+node.getTextContent());
	    				
	    			}else if(nname.equals("PC_KOR_NM")){
	    				fadto.setPname(node.getTextContent());
	    				fadao.foodAddInsert(fadto);
	    			}
	    			
	    		}
	    	}
		}
	}

	@Override
	public List<FoodAdditivesDTO> foodAddSelectList() throws Exception {
		return fadao.foodAddSelectList();
	}

}
