package com.MohrShaji.server;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

public class Response implements HttpServletResponse {

    private PrintWriter outputWriter;
    private HashMap<String, String> headers;
    private String body = "";
    private Locale locale;
    private int statusCode = 501;
    private boolean committed = false;

    public Response(OutputStream o){
        outputWriter = new PrintWriter(o);
        headers = new HashMap<>();
    }

    public PrintWriter getOutputWriter() {
        return outputWriter;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    @Override
    public String getHeader(String s) {
        return headers.get(s);
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return headers.keySet();
    }

    @Override
    public boolean containsHeader(String s) {

        return headers.containsKey(s);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return headers.keySet();
    }

    @Override
    public void setHeader(String s, String s1) {
        if(headers.containsKey(s)){
            headers.put(s,s1);
        }else{
            throw new InvalidParameterException("Key not found");
        }
    }

    @Override
    public void addHeader(String s, String s1) {
        headers.put(s,s1);
    }

    @Override
    public PrintWriter getWriter() {
        return outputWriter;
    }

    @Override
    public String getContentType() {
        return headers.get("Content-Type");
    }

    @Override
    public void setStatus(int i) {
        statusCode = i;
    }

    @Override
    @Deprecated
    public void setStatus(int i, String s) {

    }

    @Override
    public int getStatus() {
        return statusCode;
    }

    @Override
    public void setLocale(Locale locale) {
        if(!isCommitted()){
            // if(!isCharacterEncodingSet)
            this.locale = locale;
        }
    }

    @Override
    public Locale getLocale() {
        return Optional.ofNullable(locale).orElse(Locale.getDefault());
    }

    @Override
    public boolean isCommitted() {
        return !committed;
    }

    @Override
    public void setContentLength(int i) {
        headers.put("Content-Length", Integer.toString(i) );
    }

    @Override
    public void setContentLengthLong(long l) {
        headers.put("Content-Length",Long.toString(l));
    }

    @Override
    public void setContentType(String s) {
        headers.put("Content-Type",s);
    }


    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {

        return 0;
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override

    public String encodeUrl(String s) {
        return null;
    }

    @Override

    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public void reset() {

    }
}
