<%-- <%@page import="project3.MemberRegistDAO"%>
<%@page import="project3.MemberRegistDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

MemberRegistDTO dto = new MemberRegistDTO();
dto.setId(id);
dto.setPass(pass);


MemberRegistDAO dao = new MemberRegistDAO(application);
int iResult = dao.id
%> --%>