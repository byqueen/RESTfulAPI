package com.exam.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.restful.GuestVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RestController {
	RestController() {
		System.out.println("===> RestController()");
	}
	
	@GetMapping("/list")
	public String rest(Model model, GuestVo vo, HttpServletRequest request) throws Exception {
		System.out.println("===> list mapping");
		
		String START = "";
		if (vo.getStart() == 0) {
			START = "1";
		} else {
			START = request.getParameter("start");
		}
		int pageSize = 10;
		String END = String.valueOf(Integer.parseInt(START) + pageSize - 1);
		model.addAttribute("start", START);
		model.addAttribute("end", END);
		System.out.println("===> (client) start & end : "+START+" & "+END);
				
		String CONDITION = vo.getSearchCondition();
		String KEYWORD = vo.getSearchKeyword();
		if (CONDITION == null || KEYWORD == null) {
			CONDITION = "";
			KEYWORD = "";
		} else {
			CONDITION = vo.getSearchCondition();
			KEYWORD = vo.getSearchKeyword();
		}
		System.out.println("===> (client) condition & keyword : "+CONDITION+" & "+KEYWORD);

		String URL = "http://127.0.0.1:8892/restful/list";
        String RESULTTYPE = "json";

		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" + URLEncoder.encode(START, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("end","UTF-8") + "=" + URLEncoder.encode(END, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("searchCondition","UTF-8") + "=" + URLEncoder.encode(CONDITION, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("searchKeyword","UTF-8") + "=" + URLEncoder.encode(KEYWORD, "UTF-8"));
         
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
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
        System.out.println(sb);
        
        try {
        	JSONParser jsonParser = new JSONParser();
            
            // JSON데이터를 넣어 JSON Object 로 만듦
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            
            JSONArray InfoArray = (JSONArray) jsonObject.get("li");
            List<GuestVo> li = new ArrayList<GuestVo>();
            for(int i=0; i< InfoArray.size(); i++){
            	JSONObject object = (JSONObject) InfoArray.get(i);
            	GuestVo m = new GuestVo();
            	m.setIdx(String.valueOf(object.get("idx")));
            	m.setName(String.valueOf(object.get("name")));
            	m.setAge(String.valueOf(object.get("age")));
            	m.setMemo(String.valueOf(object.get("memo")));
            	m.setRegdate(String.valueOf(object.get("regdate")));
            	m.setSearchCondition(String.valueOf(object.get("searchCondition")));
            	m.setSearchKeyword(String.valueOf(object.get("searchKeyword")));
            	li.add(m);
            }
            model.addAttribute("li", li);
            
            int totalRecord = Integer.parseInt(String.valueOf(jsonObject.get("totalRecord")));
            model.addAttribute("totalRecord", totalRecord);
            
    		int currentPage = Integer.parseInt(START) / pageSize + 1;
    		int totalPage = (int)(Math.ceil((double)totalRecord / pageSize));
    		int listSize = 10;
    		int listStartPage = (currentPage-1) / listSize * listSize + 1;
    		int listEndPage =  listStartPage + listSize -1;
    		
    		
    		model.addAttribute("searchCondition", vo.getSearchCondition());
    		model.addAttribute("searchKeyword", vo.getSearchKeyword());
    		model.addAttribute("pageSize", pageSize);
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("totalRecord", totalRecord);
    		model.addAttribute("totalPage", totalPage);
    		model.addAttribute("listSize", listSize);
    		model.addAttribute("listStartpage", listStartPage);
    		model.addAttribute("listEndpage", listEndPage);
    		
    		int prePage = Integer.parseInt(START) - (pageSize*listSize);
    		int nextPage = Integer.parseInt(START) + (pageSize*listSize);
    		int lastPage = (totalPage-1) * pageSize + 1;
    		model.addAttribute("prePage", prePage);
    		model.addAttribute("nextPage", nextPage);
    		model.addAttribute("lastPage", lastPage);
            return "/list";
   
        } catch (Exception e) {
        	e.printStackTrace();
        	return "{}";

        }
	}
}
