package com.onlineretail.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public final class StringUtil {

	private StringUtil(){}


	public static long generateReference(long seed){
		Random random = new Random(seed);
		long val = random.nextLong();
		if(val<0){
			return val * -1;
		}
		return val;
	}

	public static Properties getPropertiesFromClasspath(String propFileName) throws IOException {
		// loading xmlProfileGen.properties from the classpath
		Properties props = new Properties();
		InputStream inputStream = StringUtil.class.getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		props.load(inputStream);

		return props;
	}

	//Change in the Method
	public static Set<String> readStringFromFile(String fileName) throws IOException
	{
		byte[] byteArray = new byte[200];
		InputStream inputStream =  getStream(byteArray, fileName);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String value = bufferedReader.readLine();
		Set<String> hashset = new HashSet<>();
		while(value != null)
		{
			hashset.add(value);
			value = bufferedReader.readLine();
		}
		return hashset;
	}

	private static InputStream getStream(byte[] byteArray,String fileName) throws IOException
	{
		InputStream inputStream = (InputStream) StringUtil.class.getClassLoader()
				.getResourceAsStream(fileName);

		return inputStream;

	}
	
}
