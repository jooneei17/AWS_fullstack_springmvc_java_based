package co.jmymble.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CGVParser {
	public static void main(String[] args) throws MalformedURLException, IOException {
		String 슬램덩크 = "http://www.cgv.co.kr/movies/detail-view/default.aspx?midx=86720";
		String 매트릭스 = "http://www.cgv.co.kr/movies/detail-view/?midx=85541";
		Document doc = Jsoup.parse(new URL(매트릭스), 2000);
		
		//정보
		String info = doc.selectFirst(".sect-story-movie").text();
		//영문 제목
		String engtitle = doc.selectFirst(".sect-base-movie .title p").text();
		
		
		
//		System.out.println(doc.selectFirst(".sect-base-movie .title p").text());
//		System.out.println(doc.selectFirst(".sect-story-movie").text());
		
		Element ele = doc.selectFirst("#ctl00_PlaceHolderContent_Section_Still_Cut");
		Elements els = doc.select(".sect-base-movie .spec dt");

		for(Element e : els){
			System.out.println(e.getElementsContainingText("감독"));
			Elements es = e.getElementsContainingText("감독").next().select("a");
			for(Element e2 : es){
				String director = e2.text(); //감독
				String directorHref = e2.attr("href"); //감독 링크
				String pidx = directorHref.substring(directorHref.lastIndexOf("=")+1); //pidx
			}
			System.out.println("===========================");
			Elements es2 = e.getElementsContainingText("배우").next().select("a");
			for(Element e2 : es2){
				String actor = e2.text(); //배우
				String actorHref = e2.attr("href"); //배우 링크
				String pidx = actorHref.substring(actorHref.lastIndexOf("=")+1); //pidx
			}
			Elements es3 = e.getElementsContainingText("장르");
			for(Element e2 : es3){
				String genre = e2.text(); //장르
			}
			Elements es4 = e.getElementsContainingText("기본").next();
			for(Element e2 : es4){
				String runningTime = e2.text().split(", ")[1]; //상영 시간
				String nation = e2.text().split(", ")[2]; // 제작 국가
			}
			
//			String str = e.text();
//			if(str.trim().length() != 0){
//				System.out.println(str);
//			}
		}
		
		Elements lis = ele.select("img");
		for(Element e : lis){
			System.out.println(e.attr("data-src"));
//			String href = e.selectFirst("a").attr("href");
//			String pidx = href.substring(href.lastIndexOf("=")+1);
//			String date = e.selectFirst(".txt-info").text().replaceAll("개봉", "").trim();
//			String imgSrc = e.selectFirst(".thumb-image img").attr("src");
//			String imgAlt = e.selectFirst(".thumb-image img").attr("alt");
//			String age = e.selectFirst("i.cgvIcon").text();
//			String title = e.selectFirst(".box-contents strong.title").text();
	
			
//			System.out.println(e);
//			System.out.println(e.selectFirst(".box-contents strong.title").text());
//			doc = Jsoup.parse(new URL("http://www.cgv.co.kr/movies/" + e.selectFirst("a").attr("href")), 2000);
//			System.out.println(doc);
//			break;
		}
		
//		Elements els =  doc.select(".sect-movie-chart");
//		System.out.println(els.size());
//		
//		for (int i = 0; i < els.size(); i++) {
//			Element e = els.get(i);
//			System.out.println(e);
//		}
//		
//		URL url = new URL("https://www.cgv.co.kr/");
//		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
//		String str = "";
//		while((str = br.readLine()) != null){
//			System.out.println(str);
//		}
	}
}
