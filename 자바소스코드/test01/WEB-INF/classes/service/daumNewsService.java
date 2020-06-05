package service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class daumNewsService {
	
	public ArrayList<String> news_title(String nation) throws IOException{	//��� Ÿ��Ʋ ����Ʈ
		String address = "https://sports.media.daum.net/sports/";
		
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		Elements titles = doc.select("div.cont_thumb a.link_tit");
		
		ArrayList<String> list_title = new ArrayList<>();	//Ÿ��Ʋ ������ ����Ʈ
		for(Element title : titles){	//Ÿ��Ʋ ����Ʈ�� ����
			String t = title.text();
			list_title.add(t);
        }
		
		//System.out.println("Ÿ��Ʋ"+list_title);
		
		return list_title;
		
	}
	
	public ArrayList<String> news_link(String nation) throws IOException{	//��ũ ����Ʈ
		String address = "https://sports.media.daum.net/sports/";
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		ArrayList<String> list_link = new ArrayList<>();	//Ÿ��Ʋ ������ ����Ʈ
		Elements links = doc.select("p.desc_detail a");	//���δ�����ũ ��������
		for(Element link : links){	//��縵ũ ����Ʈ�� ����
	           String t = link.attr("href");
	           if(!t.contains("#none")) {
	               list_link.add(t);
	           }
	        }
		
		//System.out.println("��ũ"+list_link);
		return list_link; 
		
	}
	
	public ArrayList<String> news_img(String nation) throws IOException{	//�̹��� ����Ʈ
		String address = "https://sports.media.daum.net/sports/";
		address = address + nation;
		Document doc = Jsoup.connect(address).get();
		Elements imgs = doc.select("div.detail_news img");
		ArrayList<String> list_img = new ArrayList<>();	
		
		for(Element img : imgs) {	//��� �̹��� ����Ʈ�� ����
			String t= img.attr("src");
			list_img.add(t);
			
		}
		//System.out.println("�̹�����ũ"+list_img);
		
		return list_img;
	}


}
