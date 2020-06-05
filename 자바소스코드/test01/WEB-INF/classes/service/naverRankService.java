package service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class naverRankService {
	
	public ArrayList<String> kleague(String choice) throws IOException{
		String address = "https://sports.news.naver.com/wfootball/record/index.nhn?";
		String Kleague = "category=";
		String year = "&year=2020";
		
		address = address + Kleague + choice + year;
		Document doc = Jsoup.connect(address).get();

		ArrayList<String> list = new ArrayList<>();
		int count  = 0;
		String text = doc.select("tbody#regularGroup_table").text();
		String temp = "";

		int num = 0;
		for(int i=0; i<text.length(); i++) {
			if(text.charAt(i) == ' ') {
				count++;
			}
			if(count == 12) {
				temp = text.substring(num, i);
				list.add(temp);
				count = 0;
				num = i+1;
			}
		}
		list.add(text.substring(num));
		
		ArrayList<String> result = new ArrayList<String>();
		String[] array = null;
		for(int i=0; i<list.size(); i++ ) {
			String tmp = list.get(i);
			tmp = tmp.replace(' ', ',');
			list.set(i,tmp);
			String str = list.get(i);
			array = str.split(",");
			for(int j=0; j<array.length; j++) {
				result.add(array[j]);
			}
		}
		
		return result;
	}
	
	public ArrayList<String> wLeague(String choice){
		//WebDriver
	    WebDriver driver;
	    
	    //Properties
	    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	    final String WEB_DRIVER_PATH = "C:/chromedriver.exe";
	    
	    //크롤링 할 URL
	    String base_url;
	    
	  //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
      //크롬 옵션
      	ChromeOptions options = new ChromeOptions();
      	options.addArguments("--headless");
      	options.addArguments("window-size=1920x1080");
      	options.addArguments("--disable-gpu");
        
        //Driver SetUp
        driver = new ChromeDriver(options);
        
        String category = "category="+choice;
        base_url = "https://sports.news.naver.com/wfootball/record/index.nhn?"+category+"&year=2019";
        ArrayList<String> list_result = new ArrayList<>();
        
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);
            WebElement element = driver.findElement(By.id("wfootballTeamRecordBody"));
            WebElement element2 = element.findElement(By.tagName("table"));
            WebElement element3 = element2.findElement(By.tagName("tbody"));
            //WebElement element4 = element3.findElement(By.tagName("tr"));
            
           // System.out.println(element4.getText());
            
            List<WebElement> div = element3.findElements(By.xpath(".//div[@class='inner']/span"));

            List<WebElement> num = element3.findElements(By.tagName("strong"));	//순위
            
            //System.out.println(num.size());
           
            
            int count = 0;
            for(int i=0; i<num.size(); i++) {
            	//System.out.print(num.get(i).getText()+ " ");
            	list_result.add(num.get(i).getText());
            	for(int j=i*9; j<div.size(); j++) {
            		count = (i*9)+9;
            		if(count != j) {
            			//System.out.print(div.get(j).getText()+" ");
            			list_result.add(div.get(j).getText());
            			count--;
            		}else {
            			break;
            		}
            	}
//            	System.out.println();
            }
            
            //System.out.println(list_result);
    
        } catch (Exception e) {
            
            e.printStackTrace();
        
        } finally {
        	driver.close();
            driver.quit();
        }
        
        return list_result;
		
	}

}
