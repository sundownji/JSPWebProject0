<%@page import="project3.MemberRegistDAO"%>
<%@page import="project3.MemberRegistDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

MemberRegistDAO dao = new MemberRegistDAO(application);

%>