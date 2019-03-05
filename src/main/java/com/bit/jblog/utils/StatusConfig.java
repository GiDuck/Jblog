package com.bit.jblog.utils;


import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.utils.status.MemberStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StatusConfig implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		
		try {
            ObjectMapper mapper = new ObjectMapper();
            List<StatusEnum> memberCode = EnumTransferer.getEnumCode(MemberStatusCode.class);
            List<StatusEnum> blogCode = EnumTransferer.getEnumCode(BlogStatusCode.class);

            sce.getServletContext().setAttribute("memberStatusCode", mapper.writeValueAsString(memberCode));
            sce.getServletContext().setAttribute("blogStatusCode", mapper.writeValueAsString(blogCode));

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
