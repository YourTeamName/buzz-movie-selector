package edu.gatech.buzzmovieselector.service.api;

/**
 * The type of data that was returned by an API call
 */
public enum ApiResponseType {
    JSON,
    TEXT,
    IMAGE,
    XML;

    @Override
    public String toString() {
        switch (this) {
            case JSON: return "JSON";
            case TEXT: return "Text";
            case IMAGE: return "Image";
            case XML: return "XML";
            default:
                throw new IllegalArgumentException();
        }
    }
}
