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
    
    //ũ�Ѹ� �� URL
    private String base_url;
    
    public ArrayList<String> getScore(String date){
    	 //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
      //ũ�� �ɼ�
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
            //get page (= ���������� url�� �ּ�â�� ���� �� request �� �Ͱ� ����)
            driver.get(base_url);
            //WebElement element = driver.findElement(By.xpath(".//section[@class='ebxv2uw0']/section"));
            WebElement element = driver.findElement(By.className("ebxv2uw0"));
            WebElement element2 = element.findElement(By.className("e1edruoy0"));
            WebElement element3 = element2.findElement(By.className("e1edruoy2"));
            
            List<WebElement> element4 = element3.findElements(By.className("e7pc1841"));
            
            List<WebElement> score = element3.findElements(By.className("ew7iiy60"));	//���׺� ���ھ ����Ʈ�� ����
            //List<WebElement> score = element3.findElements(By.className("e7pc1841"));
            List<WebElement> league = element3.findElements(By.className("e7pc1840"));	//����
            
            //List<WebElement> test = element4.findElements(By.tagName("a"));
            
            int gamenum = 0;
            
            
            //System.out.println(element4.size());
            
            for(int i=0; i<element4.size(); i++) {
            	//System.out.println("i��° = "+element4.get(i).findElements(By.tagName("a")).size());
            	for(int j=0; j<element4.get(i).findElements(By.tagName("a")).size(); j++) {
            		if(j==0) {
            			full_score.add("���׸�");
            			full_score.add(element4.get(i).findElements(By.tagName("a")).get(j).getText());	//ù��°�� ������ ���׸��̹Ƿ�
            		}else {
            			full_score.add(element4.get(i).findElements(By.tagName("a")).get(j).getText());
            		}
            		
            	}
            }
            full_score.remove(0);	//ù��° ���׸� ����
            
            for(int i=0; i<full_score.size(); i++) {	//����Ʈ ���� ����
            	String tmp = full_score.get(i);
            	tmp = tmp.replaceAll("\n","  ");	//����Ʈ �ȿ� ���๮�� ����
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
