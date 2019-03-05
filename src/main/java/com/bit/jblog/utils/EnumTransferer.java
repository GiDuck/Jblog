package com.bit.jblog.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumTransferer {
	
	public static List<StatusEnum> getEnumCode(Class< ? extends SimpleStatusEnum > e){
		
		return Arrays.stream(e.getEnumConstants())
				.map(StatusEnum::new)
				.collect(Collectors.toList());
		
	}

}
