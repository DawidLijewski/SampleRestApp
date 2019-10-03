package lijewski.data.network

import io.reactivex.Single
import lijewski.data.response.RemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteEndpoint {
    @GET("search")
    fun getSearchResults(@Query("term") term: String,
                         @Query("country") country: String,
                         @Query("media") media: String): Single<RemoteResponse>
}
