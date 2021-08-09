//package com.chumlung.backend.config;
//
//import java.time.Duration;
//import java.time.LocalDate;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//@ConfigurationProperties(prefix = "server.servlet.session.cookie")
//@Configuration("cookieProperties")
//public class Cookieconfiguration {
//
//    private String comment;
//    private String domain;
//    private boolean httpOnly;
//    private Duration maxAge;
//    private String name;
//    private String path;
//    private boolean secure=true;
//    LocalDate futureDate = LocalDate.now().plusMonths(1);
//    
//	public String getComment() {
//		return comment;
//	}
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//	public String getDomain() {
//		return domain;
//	}
//	public void setDomain(String domain) {
//		this.domain = domain;
//	}
//	public boolean isHttpOnly() {
//		return httpOnly;
//	}
//	public void setHttpOnly(boolean httpOnly) {
//		this.httpOnly = httpOnly;
//	}
//	public Duration getMaxAge() {
//		return maxAge;
//	}
//	public void setMaxAge(Duration maxAge) {
//		this.maxAge = maxAge;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPath() {
//		return path;
//	}
//	public void setPath(String path) {
//		this.path = path;
//	}
//	public boolean isSecure() {
//		return secure;
//	}
//	public void setSecure(boolean secure) {
//		this.secure = secure;
//	}
//    
//    
//
//}