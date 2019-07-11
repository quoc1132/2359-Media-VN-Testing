package uit.quocnguyen.a2359mediavntesting.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uit.quocnguyen.a2359mediavntesting.R;
import uit.quocnguyen.a2359mediavntesting.databinding.NowPlayingRowItemBinding;
import uit.quocnguyen.a2359mediavntesting.models.NowPlayingItem;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.MyViewHolder> {

    private List<NowPlayingItem> nowPlayingItems;
    private LayoutInflater layoutInflater;
    private NowPlayingAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final NowPlayingRowItemBinding binding;

        public MyViewHolder(final NowPlayingRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }


    public NowPlayingAdapter(List<NowPlayingItem> nowPlayingItems, NowPlayingAdapterListener listener) {
        this.nowPlayingItems = nowPlayingItems;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        NowPlayingRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.now_playing_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setNowPlayingItem(nowPlayingItems.get(position));
        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(nowPlayingItems.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nowPlayingItems.size();
    }

    public interface NowPlayingAdapterListener {
        void onPostClicked(NowPlayingItem nowPlayingItem);
    }
}
