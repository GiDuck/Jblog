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
	
		
            List<StatusEnum> memberCode = EnumTransferer.getEnumCode(MemberStatusCode.class);
            List<StatusEnum> blogCode = EnumTransferer.getEnumCode(BlogStatusCode.class);

            sce.getServletContext().setAttribute("memberStatusCode",BlogHelper.parseToJson(memberCode));
            sce.getServletContext().setAttribute("blogStatusCode", BlogHelper.parseToJson(blogCode));

		
	}

	
	
	
}
