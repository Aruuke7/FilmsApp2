package kg.geektech.filmsapp.ui.films;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import kg.geektech.filmsapp.data.models.Film;
import kg.geektech.filmsapp.databinding.ItemRecBinding;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {
    private List<Film> films = new ArrayList<>();
    private final OnItemClick listener;

    public FilmsAdapter(OnItemClick listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemRecBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(films.get(position));
        holder.itemView.setOnClickListener(v -> listener.onClick(films.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecBinding binding;
        public ViewHolder(@NonNull  ItemRecBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(Film film) {
            binding.title.setText(film.getTitle());
            Log.d("TAG", "onBind: "+ film.getTitle());
            binding.desc.setText(film.getDescription());
            Log.d("TAG", "onBind: "+ film.getDescription());
            Glide.with(binding.image).load(film.getImage()).into(binding.image);
        }
    }
}
