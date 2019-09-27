package lijewski.data.network

import io.reactivex.Single
import lijewski.data.response.RemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteEndpoint {
    @GET("search")
    fun getSongsList(@Query("term") artistName: String): Single<RemoteResponse>
}
