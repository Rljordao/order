package br.com.ambev.order.domain.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorResponse {

    private int statusCode;
    private String statusMessage;
    private String timestamp;
    private String message;
    private String details;
    private String traceId;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public ErrorResponse(String message, String details, int statusCode, String statusMessage, String traceId) {
        this.timestamp = formatTimestamp();
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.traceId = traceId;
    }

    public ErrorResponse(String message, String details, int statusCode, String statusMessage) {
        this.timestamp = formatTimestamp();
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public ErrorResponse(String message, int statusCode, String statusMessage) {
        this.timestamp = formatTimestamp();
        this.message = message;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }




    private String formatTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(new Date());
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
