package iprint.uajy.com.iprint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("user_name")
    @Expose
    private String name;

    @SerializedName("user_email")
    @Expose
    private String email;

    @SerializedName("user_address")
    @Expose
    private String address;

    @SerializedName("user_phone")
    @Expose
    private String phone;

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}