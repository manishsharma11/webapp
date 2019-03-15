package com.main.sts.util;

import javax.servlet.http.HttpServletRequest;

public class ApplicationName {

    public static String getApplicationName(HttpServletRequest request) {
        //<%=request.getContextPath()%>
        return ((HttpServletRequest) request).getContextPath().replace("/", "");
    }
}
