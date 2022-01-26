package com.jan.dec201.bh;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// buildPath로 hadoop관련 jar파일 import하기
// In
//		key : ?
//		value : 2020,1,1,100,명륜3가.성대입구,70.000000,96.000000
// Out
//		key : 100
//		value :166(70.000000 + 96.000000)
public class BHMapper extends Mapper<Object, Text, Text, LongWritable>{
	
	private static final Text YOIL = new Text();
	private static final LongWritable SUM = new LongWritable();
	
	@Override
	protected void map(Object key, 
						Text value, 
						Mapper<Object, Text, Text, LongWritable>.Context context)
						throws IOException, InterruptedException {
		try {
			String line = value.toString(); 
			String[] line2 = line.split(","); 
			
			String y = line2[0];
			int m = Integer.parseInt(line2[1]);
			int d = Integer.parseInt(line2[2]);
			String date = String.format("%s%02d%02d", y, m, d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date2;
			date2 = sdf.parse(date);
			sdf = new SimpleDateFormat("E");
			YOIL.set(sdf.format(date2));
			
			String s1 = line2[5].replace(".000000", ""); 
			Long l1 = Long.parseLong(s1); 
			String s2 = line2[6].replace(".000000", "");
			Long l2 = Long.parseLong(s2);
			SUM.set(l1 + l2); 
			
			context.write(YOIL, SUM);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
