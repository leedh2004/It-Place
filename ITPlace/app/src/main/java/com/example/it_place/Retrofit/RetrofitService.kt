import com.example.it_place.Model.Place
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RetrofitService {

    /**
     * 1.이미지 파일 업로드
     */
//    @Multipart
//    @POST("upload")
//    fun uploadImage(@Part imgFile: MultipartBody.Part): Call<ImageRes>

    /**
     * 3. 방 생성
     */
    @POST("room")
    fun createRoom()

    /**
     * 4.모든 방 목록 조회
     */
    @GET("room/all")
    fun placeList(): Call<List<Place>>
}