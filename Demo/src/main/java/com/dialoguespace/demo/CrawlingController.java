package com.dialoguespace.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingController {
	
	private WebDriver driver;
	private WebElement element;
	private String url;
	
	// 1. 드라이버 설치 경로
		public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
		public static String WEB_DRIVER_PATH = "D:/library/selenium/chromedriver-win64/chromedriver.exe";
		
		public CrawlingController (){
			// WebDriver 경로 설정
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
					
			// 2. WebDriver 옵션 설정
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--remote-allow-origins=*");
	        
			driver = new ChromeDriver(options);
			
			url = "https://www.melon.com/chart/index.htm";
		}
		
		public void activateBot() {
			try {
				driver.get(url);
				Thread.sleep(2000); // 3. 페이지 로딩 대기 시간
				
				//System.out.println(driver.getPageSource());
				System.out.println(driver.findElements(By.cssSelector(".lst50 > td:nth-child(6) > div > div > div.ellipsis.rank01 > span > a")));
				
				
				/*
				
				// 4. 로그인 버튼 클릭
				element = driver.findElement(By.className("MyView-module__link_login___HpHMW"));
				element.click();
				
				Thread.sleep(1000);
				
				// ID 입력
				element = driver.findElement(By.id("id"));
				element.sendKeys("test");
				
				// 비밀번호 입력
				element = driver.findElement(By.id("pw"));
				element.sendKeys("12345678");
				
				// 전송
				element = driver.findElement(By.className("btn_login"));
				element.submit();
				*/
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				//driver.close(); // 5. 브라우저 종료
			}
		}
		
		public static void main(String[] args) {
			CrawlingController bot1 = new CrawlingController();
			bot1.activateBot();
		}
}
