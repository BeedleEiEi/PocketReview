package app.beedle.pocketreview.api;

import app.beedle.pocketreview.model.CurrencyExchange;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Beedle on 7/11/2560.
 */

public interface FixerInterface {
    @GET("latest")
    Call<CurrencyExchange> loadCurrencyExchange();

}
