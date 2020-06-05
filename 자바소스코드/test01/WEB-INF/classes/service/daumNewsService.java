package service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class daumNewsService {
	
	public ArrayList<String> news_title(String nation) throws IOException{	//기사 타이틀 리스트
		String address = "https://sports.media.daum.net/sports/";
		
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		Elements titles = doc.select("div.cont_thumb a.link_tit");
		
		ArrayList<String> list_title = new ArrayList<>();	//타이틀 저장한 리스트
		for(Element title : titles){	//타이틀 리스트에 저장
			String t = title.text();
			list_title.add(t);
        }
		
		//System.out.println("타이틀"+list_title);
		
		return list_title;
		
	}
	
	public ArrayList<String> news_link(String nation) throws IOException{	//링크 리스트
		String address = "https://sports.media.daum.net/sports/";
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		ArrayList<String> list_link = new ArrayList<>();	//타이틀 저장한 리스트
		Elements links = doc.select("p.desc_detail a");	//메인뉴스링크 가져오기
		for(Element link : links){	//기사링크 리스트에 저장
	           String t = link.attr("href");
	           if(!t.contains("#none")) {
	               list_link.add(t);
	           }
	        }
		
		//System.out.println("링크"+list_link);
		return list_link; 
		
	}
	
	public ArrayList<String> news_img(String nation) throws IOException{	//이미지 리스트
		String address = "https://sports.media.daum.net/sports/";
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		Elements imgs = doc.select("div.detail_news img");
		ArrayList<String> list_img = new ArrayList<>();	
		
		for(Element img : imgs) {	//기사 이미지 리스트에 저장
			String t= img.attr("src");
			list_img.add(t);
			
		}
		//System.out.println("이미지링크"+list_img);
		
		return list_img;
	}


}
