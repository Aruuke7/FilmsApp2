package kg.geektech.filmsapp.ui.film_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import kg.geektech.filmsapp.App;
import kg.geektech.filmsapp.data.models.Film;
import kg.geektech.filmsapp.databinding.FragmentDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFilm();
    }

    private void getFilm() {
        assert getArguments() != null;
        String id = getArguments().getString("id");
        App.api.getFilmById(id).enqueue(new Callback<Film>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Film film = response.body();
                    binding.title.setText("Название: " + film.getTitle());
                    binding.description.setText("Описание:  " + film.getDescription());
                    binding.director.setText("Директор: " + film.getDirector());
                    binding.originalTitle.setText("Оригинальное название: " + film.getOriginalTitle());
                    binding.releaseDate.setText("Год: " + film.getRelease_date());
                    Glide.with(binding.image).load(film.getImage()).into(binding.image);
                    Glide.with(binding.banner).load(film.getMovieBanner()).into(binding.banner);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {

            }
        });
    }
}