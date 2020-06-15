package com.transcript;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class Utils {

	public static String readJSON(String data)
	{
		String str = "";
		try {
			//String data = readFileAsString("C:\\Users\\lakshyaveer.singh\\Documents\\workspace-spring-tool-suite-4-4.6.2.RELEASE\\Transcrip\\src\\main\\resources\\static\\downloaded_from_facebook.json");
			
			JsonParser springParser = JsonParserFactory.getJsonParser();
			Map<String, Object> entityMap = springParser.parseMap(data);
			//System.out.println(map);
			for(Map.Entry<String, Object> map: entityMap.entrySet())
			{
				if(map.getKey().equals("messages"))
				{
					List<Map<String, Long>> s =  (List)map.getValue();
					//System.out.println(s);
					for(Map<String, Long> l: s)
					{
						//String str = "";
						for(Map.Entry<String, Long> m: l.entrySet())
						{
							if(m.getKey().equals("sender_name"))
							{
								str = str.concat(String.valueOf(m.getValue())+"");
							}
							if(m.getKey().equals("timestamp_ms"))
							{
								SimpleDateFormat ojSdf = new SimpleDateFormat("dd/MM/YYYY, HH:mm:ss");
								String a = ojSdf.format(m.getValue());
								//System.out.println(a);
								str = str.concat("("+String.valueOf(a)+""+"): ");
								
							}
							if(m.getKey().equals("content"))
							{
								str = str.concat(String.valueOf(m.getValue())+" ");
								str = str.concat("\n");
							}
							/* return str; */
						}
						System.out.println(str);
					}
					//System.out.println(s);
					
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String readFileAsString(String fileName)throws Exception 
	  { 
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	  }
	
}
