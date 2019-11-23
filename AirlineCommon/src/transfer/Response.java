/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author student1
 */
public class Response implements Serializable {

    private String message;
    private Object data;
    private boolean successful;

    public Response() {
    }

    public Response(String message, Object data, boolean successful) {
        this.message = message;
        this.data = data;
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    

}
