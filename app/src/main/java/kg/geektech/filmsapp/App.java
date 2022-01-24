package kg.geektech.filmsapp;

import android.app.Application;

import kg.geektech.filmsapp.data.remote.FilmApi;
import kg.geektech.filmsapp.data.remote.RetrofitClient;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmApi api;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideFilmApi();
    }
}
