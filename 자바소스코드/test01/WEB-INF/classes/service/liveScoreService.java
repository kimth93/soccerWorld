package service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class liveScoreService {
	//WebDriver
    private WebDriver driver;
    
    //Properties
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:/chromedriver.exe";
    
    //크롤링 할 URL
    private String base_url;
    
    public ArrayList<String> getScore(String date){
    	 //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
      //크롬 옵션
      	ChromeOptions options = new ChromeOptions();
      	options.addArguments("--headless");
      	options.addArguments("window-size=1920x1080");
      	options.addArguments("--disable-gpu");
        
        //Driver SetUp
        driver = new ChromeDriver(options);
        //String date = "/?date=20200525";
        
        base_url = "https://www.fotmob.com"+date;	//url
        
        ArrayList<String> full_score = new ArrayList<>();
        
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);
            //WebElement element = driver.findElement(By.xpath(".//section[@class='ebxv2uw0']/section"));
            WebElement element = driver.findElement(By.className("ebxv2uw0"));
            WebElement element2 = element.findElement(By.className("e1edruoy0"));
            WebElement element3 = element2.findElement(By.className("e1edruoy2"));
            
            List<WebElement> element4 = element3.findElements(By.className("e7pc1841"));
            
            List<WebElement> score = element3.findElements(By.className("ew7iiy60"));	//리그별 스코어를 리스트에 저장
            //List<WebElement> score = element3.findElements(By.className("e7pc1841"));
            List<WebElement> league = element3.findElements(By.className("e7pc1840"));	//리그
            
            //List<WebElement> test = element4.findElements(By.tagName("a"));
            
            int gamenum = 0;
            
            
            //System.out.println(element4.size());
            
            for(int i=0; i<element4.size(); i++) {
            	//System.out.println("i번째 = "+element4.get(i).findElements(By.tagName("a")).size());
            	for(int j=0; j<element4.get(i).findElements(By.tagName("a")).size(); j++) {
            		if(j==0) {
            			full_score.add("리그명");
            			full_score.add(element4.get(i).findElements(By.tagName("a")).get(j).getText());	//첫번째는 무조건 리그명이므로
            		}else {
            			full_score.add(element4.get(i).findElements(By.tagName("a")).get(j).getText());
            		}
            		
            	}
            }
            full_score.remove(0);	//첫번째 리그명 제거
            
            for(int i=0; i<full_score.size(); i++) {	//리스트 최종 수정
            	String tmp = full_score.get(i);
            	tmp = tmp.replaceAll("\n","  ");	//리스트 안에 개행문자 수정
            	full_score.set(i, tmp);
            }
            //System.out.println(full_score);
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        
        } finally {
        	driver.close();
            driver.quit();
        }
        return full_score;
    }
}
