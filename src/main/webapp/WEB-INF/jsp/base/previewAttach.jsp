<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预览</title>
</head>
<body>
    <div  align="center" style="margin:0 auto ;">
        <embed  align="center" width="1024px" height="800px;" src="<%=request.getContextPath()%>${path}" wmode="opaque"></embed>
    </div>
</body>
</html>