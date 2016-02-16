package com.skoh.sample.common.converter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ParametersConverter {	
	/**
	 * 
	 * @param map
	 * @param tempMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map convertObject(Map map) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		for(Iterator<Entry> iterator = map.entrySet().iterator() ; iterator.hasNext();) {
			
			Entry entry = iterator.next();
			
			String key = entry.getKey().toString();
			Object value = entry.getValue();
			
			if (value == null) {
				tempMap.put(key, null);
        	}
        	else if (value.getClass().isArray()) {
        		if(Array.getLength(value) == 1) {
        			tempMap.put(key, ((String)Array.get(value, 0)));
        		}
        		else {
        			List list = new ArrayList();
        			for(Object obj : Arrays.asList(value)) {
        				list.add(obj.toString());
        			}
        			tempMap.put(key, list);
        		}
        	}
        	else {
    			tempMap.put(key, value);
        	}
		}
		return tempMap;
	}
}