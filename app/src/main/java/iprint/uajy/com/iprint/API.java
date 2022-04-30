package iprint.uajy.com.iprint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @POST("user_signup.php")
    @FormUrlEncoded
    Call<String> signup (@Field("name") String name,
                         @Field("email") String email,
                         @Field("password") String password,
                         @Field("address") String address,
                         @Field("phone") String phone);

    @POST("user_login.php")
    @FormUrlEncoded
    Call<LoginResponse> loginRequest (@Field("email") String email,
                                      @Field("password") String password);

    @POST("user_edit.php")
    @FormUrlEncoded
    Call<String> edit (@Field("email") String email,
                       @Field("address") String address,
                       @Field("phone") String phone,
                       @Field("password") String password);
}