package com.app.wheaterapproshka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.wheaterapproshka.Retrofit.APiInterface;
import com.app.wheaterapproshka.Retrofit.ApiClient;
import com.app.wheaterapproshka.models.OpenWeather.Main;
import com.app.wheaterapproshka.models.OpenWeather.WeatherResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActivity extends AppCompatActivity {
    private String TAG = "DetalleActivity";
    private String ciudad, lat, lng;
    private APiInterface apiInterface;
    private TextView tvTempActual, tvSensacion, tvMax, tvMin, tvTitulo;
    private RadioButton rkelvin, rcelsius, rfarenh;
    private RadioGroup grupo;
    private float tempActual,tempSensac, tempMax, temp_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        initComponents();

        Bundle bundle = getIntent().getExtras();

        ciudad = bundle.getString("Ciudad");
        lat = bundle.getString("Latitud");
        lng = bundle.getString("Longitud");
        tvTitulo.setText("Temperatura en " + ciudad);

        initRetrofit();
    }

    private void initComponents() {
        tvTitulo = findViewById(R.id.tvDetalle);
        tvTempActual = findViewById(R.id.tvTempActual);
        tvSensacion = findViewById(R.id.tvSensacionTerm);
        tvMax = findViewById(R.id.tvTemMax);
        tvMin = findViewById(R.id.tvTemMin);

        rkelvin = findViewById(R.id.rkelvin);
        rcelsius = findViewById(R.id.rcelsius);
        rfarenh = findViewById(R.id.rfaren);

        grupo =  findViewById(R.id.opciones_sistema);

    }

    private void initRetrofit() {

        apiInterface  = ApiClient.getClient().create(APiInterface.class);

        Call<WeatherResponse> call = apiInterface.getCurrentWeatherData(lat,lng,BuildConfig.OPENWEATHER_API_KEY);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d(TAG, "onResponse CODE: "+ response.code());
                Log.d(TAG, "onResponse CODE: "+ response.message());

                Gson gson =  new Gson();
                String responseStr =  gson.toJson(response.body());
                Log.d(TAG, "response: "+ responseStr);

                WeatherResponse wResponse = gson.fromJson(gson.toJson(response.body()), WeatherResponse.class);

                setvalues(wResponse);

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        boolean marcado = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rkelvin:
                if (marcado) {
                   //calcular kelvin
                    tvTempActual.setText(String.valueOf(tempActual));
                    tvSensacion.setText(String.valueOf(tempSensac));
                    tvMax.setText(String.valueOf(tempMax));
                    tvMin.setText(String.valueOf(temp_min));

                }
                break;

            case R.id.rfaren:
                if (marcado) {
                    //calcular celsius
                    tvTempActual.setText(String.valueOf(getFarenh(tempActual)));
                    tvSensacion.setText(String.valueOf(getFarenh(tempSensac)));
                    tvMax.setText(String.valueOf(getFarenh(tempMax)));
                    tvMin.setText(String.valueOf(getFarenh(temp_min)));
                }
                break;

            case R.id.rcelsius:
                if (marcado) {
                    //calcular farenh
                    tvTempActual.setText(String.valueOf(getCelsius(tempActual)));
                    tvSensacion.setText(String.valueOf(getCelsius(tempSensac)));
                    tvMax.setText(String.valueOf(getCelsius(tempMax)));
                    tvMin.setText(String.valueOf(getCelsius(temp_min)));
                }
                break;
        }
    }

    private float getFarenh(float temperatura) {
        float conversion;

        conversion = (float) ((temperatura - 273.15) * 1.8000 + 32.00);

        return conversion;
    }

    private float getCelsius(float temperature) {
        float conversion;
        conversion = (float) (temperature - 273.15);
        return conversion;
    }


    private void setvalues(WeatherResponse wResponse) {
        Main mainTemp = wResponse.main;
        tempActual = mainTemp.temp;
        tempSensac = mainTemp.feelsLike;
        tempMax = mainTemp.temp_max;
        temp_min = mainTemp.temp_min;

        tvTempActual.setText(String.valueOf(tempActual));
        tvSensacion.setText(String.valueOf(tempSensac));
        tvMax.setText(String.valueOf(tempMax));
        tvMin.setText(String.valueOf(temp_min));

    }
}