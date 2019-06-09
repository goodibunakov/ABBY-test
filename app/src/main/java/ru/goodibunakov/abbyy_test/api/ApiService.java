package ru.goodibunakov.abbyy_test.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.goodibunakov.abbyy_test.model.TranslateModel;

public interface ApiService {

    @POST(Urls.GET_AUTH)
    Call<ResponseBody> getAuthorization(@Header("Authorization") String keyApi);

    @GET(Urls.GET_TRANSLATE)
    Call<List<TranslateModel>> getTranslation(@Header("Authorization") String authBearer,
                                              @Query("text") String text,
                                              @Query("srcLang") int srcLang,
                                              @Query("dstLang") int dstLang);
}
