package edu.gatech.buzzmovieselector.biz.api;

/**
 * The type of data that was returned by an API call
 */
public enum ApiResponseType {
    JSON,
    TEXT,
    IMAGE,
    XML,
    ERROR;

    @Override
    public String toString() {
        switch (this) {
            case JSON: return "JSON";
            case TEXT: return "Text";
            case IMAGE: return "Image";
            case XML: return "XML";
            case ERROR: return "Error";
            default:
                throw new IllegalArgumentException();
        }
    }
}
