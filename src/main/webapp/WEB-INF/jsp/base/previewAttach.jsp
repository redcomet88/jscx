<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PDF预览</title>
</head>
<body>
    <div style="width:100%;height:100%">
        <iframe width="100%" height="800px;" src="<%=request.getContextPath()%>${path}"></iframe>
    </div>
</body>
</html>