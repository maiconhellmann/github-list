package br.com.supero.hithublist.ui.repository.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.supero.hithublist.data.model.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;
import br.com.supero.hithublist.R;

public class RepositorysAdapter extends RecyclerView.Adapter<RepositorysAdapter.RepositoryViewHolder> {

    private List<Repository> mRepositoryList;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(Repository repository);
    }

    @Inject
    public RepositorysAdapter() {
        mRepositoryList = new ArrayList<>();
    }

    public void setRepositories(List<Repository> repositorys) {
        mRepositoryList = repositorys;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepositoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RepositoryViewHolder holder, int position) {
        final Repository repository = mRepositoryList.get(position);
        holder.nameTextView.setText(repository.getFullName());
        holder.emailTextView.setText(repository.getOwner().getLogin());


        if(onItemClickListener != null) holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(repository);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_type) View hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_owner) TextView emailTextView;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
