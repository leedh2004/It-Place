import com.example.it_place.retrofit.service.RetrofitService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

//static 싱글톤
object Retrofit {
    //BASE URL
    private const val BASE_URL_TEST = "http://ec2-35-170-243-75.compute-1.amazonaws.com:3000/"

    //IMAGE URL
    var IMAGE_URL: String = ""

    //api 통신
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_TEST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RetrofitService = retrofit.create(RetrofitService::class.java)
}