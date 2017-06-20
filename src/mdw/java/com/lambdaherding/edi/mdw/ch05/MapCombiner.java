package com.lambdaherding.edi.mdw.ch05;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapCombiner {
Map<String, Integer> builder = new HashMap<String, Integer>();
	
	public MapCombiner merge(MapCombiner other) {
    for(Entry<String, Integer> entry : other.toMap().entrySet() ){
    	if (builder.containsKey( entry.getKey() )){
    		builder.put( entry.getKey(), entry.getValue() + builder.get( entry.getValue() ));
    	} else {
    		builder.put( entry.getKey(), entry.getValue());
    	}
    };
    return this;
}
	public MapCombiner add(String element) {
    if ( builder.containsKey( element )){
    	builder.put( element, builder.get( element ) + 1);
    } else {
    	builder.put( element,  1);
    }
    return this;
}

	public Map<String, Integer> toMap() {
		return builder;
	}

}
