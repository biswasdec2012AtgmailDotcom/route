
package com.biswas.route.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RouteService
{
    @GET("5808f00d10000005074c6340")
    Observable<RouteResponse> getRoutes();
}
