<%@ attribute name="title" required="true" %>
<%@ taglib prefix="imports" tagdir="/WEB-INF/tags/template/imports" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>${title}</title>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <imports:bootstrapLinks/>
    <imports:headerTemplate/>
</head>
<body>

<jsp:doBody/>

</body>
</html>
