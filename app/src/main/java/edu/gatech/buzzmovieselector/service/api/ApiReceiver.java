package edu.gatech.buzzmovieselector.service.api;

import org.json.JSONObject;

/**
 * Holds the response of an ApiCommand
 */
public class ApiReceiver {
    private ApiResponseType responseType;
    private Object responseData;

    /**
     * Gets the ApiCommand's response type
     * @return the type of data that was returned
     */
    public ApiResponseType getResponseType() {
        return responseType;
    }

    /**
     * Returns an object that accurately represents the corresponding responseType
     * @return An server's response
     */
    public Object getResponse() {
        if (responseType == ApiResponseType.JSON) {
            return (JSONObject) responseData;
        } else if (responseType == ApiResponseType.TEXT) {
            return (String) responseData;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
