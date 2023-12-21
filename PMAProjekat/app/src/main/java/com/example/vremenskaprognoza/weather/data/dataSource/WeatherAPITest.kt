import com.example.vremenskaprognoza.weather.data.dataSource.WeatherApi
import com.example.vremenskaprognoza.weather.domain.model.CurrentWeather
import com.example.vremenskaprognoza.weather.domain.model.DailyWeather
import com.example.vremenskaprognoza.weather.domain.model.HourlyWeather
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class WeatherApiTest {

    private lateinit var weatherApi: WeatherApi

    @Before
    fun setup() {
        weatherApi = mock(WeatherApi::class.java)
    }

    @Test
    fun testGetCurrentWeather() {
        runBlocking {
            // Mocking the response
            val mockResponse = mock(CurrentWeather::class.java)
            `when`(weatherApi.getCurrentWeather(60.0, 60.0, "Celsius", "m/s", "mm"))
                .thenReturn(mockResponse)

            // Performing the API call
            val currentWeather = weatherApi.getCurrentWeather(60.0, 60.0, "Celsius", "m/s", "mm")

            // Asserting the response
            assertNotNull(currentWeather)
        }
    }

    @Test
    fun testGetHourlyWeather() {
        runBlocking {
            // Mocking the response
            val mockResponse = mock(HourlyWeather::class.java)
            `when`(weatherApi.getHourlyWeather(60.0, 60.0, "Celsius", "m/s", "mm"))
                .thenReturn(mockResponse)

            // Performing the API call
            val hourlyWeather = weatherApi.getHourlyWeather(60.0, 60.0, "Celsius", "m/s", "mm")

            // Asserting the response
            assertNotNull(hourlyWeather)
        }
    }

    @Test
    fun testGetDailyWeather() {
        runBlocking {
            // Mocking the response
            val mockResponse = mock(DailyWeather::class.java)
            `when`(weatherApi.getDailyWeather(60.0, 60.0, "Celsius", "m/s", "mm"))
                .thenReturn(mockResponse)

            // Performing the API call
            val dailyWeather = weatherApi.getDailyWeather(60.0, 60.0, "Celsius", "m/s", "mm")

            // Asserting the response
            assertNotNull(dailyWeather)
        }
    }
}
