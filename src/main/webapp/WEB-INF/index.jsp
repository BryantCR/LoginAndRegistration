<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Languages</title>
		<link rel="stylesheet" href="./css/index.css"/>
	</head>
	<body>
		<header>
			<nav>
				<nav>
					Languages
				</nav>
			</nav>
		</header>
		<main>
			<h1>
				All LANGUAGES
			</h1>
			<div class="containerTable" >
                <table class="tdText1">
                    <thead>
                        <tr>
                            <th class="thText1" >
                                Name
                            </th>
                            <th class="thText1" >
                                Creator
                            </th>
                            <th class="thText1" >
                                Version
                            </th>
                            <th class="thText1" >
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${languageList}" var="languageList">
                            <tr>
                                <td class="tdText1" ><a class="title" href="/showlanguage/${ languageList.id }"><c:out value="${languageList.languageName}"/></a></td>
                                <td class="tdText1" ><c:out value="${languageList.creatorName}"/></td>
                                <td class="tdText1" ><c:out value="${languageList.languageVersion}"/></td>
                                <td class="tdText1" > 
                                	<form action="/delete/${languageList.id}" method='POST'>
                                		<input type="hidden" name="_method" value="DELETE">
                						<input class="btn btn-danger" type="submit" value="DELETE">
                                	</form>	
                                	<a href="/languages/edit/${languageList.id}"> Edit </a> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
			</div>
			<form class="formIndex" action="/addlanguages" method = 'POST'>
				<div class="conContainerForm">
	                <div class="containerFormData">
	                    <label class="formLabel" for="languageName">
	                        Name
	                    </label>
	                    <input class="formInput" type="text" name="languageName" id="languageName" placeholder="Name of the language here" autocomplete=None>
	                </div>
	                <div class="containerFormData">
	                    <label class="formLabel" for="creatorName">
	                        Creator 
	                    </label>
	                    <input class="formInput" type="text" name="creatorName" id="creatorName" placeholder="Name of the creator here" autocomplete=None>
	                </div>
	                <div class="containerFormData">
	                    <label class="formLabel" for="languageVersion">
	                        Version 
	                    </label>
	                    <input class="formInput" type="text" name="languageVersion" id="languageVersion" placeholder="Version of the language here" autocomplete=None>
	                </div>
	                <input class="submitButton" type="submit" value="Submit">
            	</div>
			</form>
		</main>	
	</body>
</html>
