<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    I know how to connect IntelliJ Java with SSMS SQL!<br><br>
    <table>
        <c:forEach items="${MYTEXT}" var="item" >
            <tr>
                <td>${item}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>