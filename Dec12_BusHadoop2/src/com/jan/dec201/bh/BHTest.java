package com.jan.dec201.bh;

import java.text.SimpleDateFormat;
import java.util.Date;

//2020,1,1,100,명륜3가.성대입구,70.000000,96.000000
public class BHTest {
	public static void main(String[] args) {
		try {
			String line = "2020,1,1,100,명륜3가.성대입구,70,96";
			String[] line2 = line.split(",");
			String y = line2[0];
//			System.out.println(y);
//			String m = line2[1];
//			String d = line2[2];
//			System.out.println(m + "." + d);
			// 월, 일 은 두자리로 정리
			int m = Integer.parseInt(line2[1]);
			int d = Integer.parseInt(line2[2]);
			String date = String.format("%s%02d%02d", y, m, d);
//			System.out.println(date);
			// 날짜로
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// String -> Date 형변환 : .parse
			// Date -> String 형변환 : .format
			Date date2 = sdf.parse(date);
			// 날짜 -> 요일
			sdf = new SimpleDateFormat("E");
			String yoil = sdf.format(date2);
			System.out.println(yoil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
