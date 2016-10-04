package ali.jpostnetworking.test.jpostnetworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by janisharali on 03/10/16.
 */

public class RepoListAdapter extends BaseAdapter {

    private List<GitRepo> gitRepoList;
    private Context context;

    public RepoListAdapter(Context context, List<GitRepo> gitRepoList) {
        this.context  = context;
        this.gitRepoList = gitRepoList;
    }

    @Override
    public int getCount() {
        return gitRepoList.size();
    }

    @Override
    public Object getItem(int position) {
        return gitRepoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.repo_list_item,parent,false);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.repoIdTxt = (TextView)convertView.findViewById(R.id.repoIdTxt);
        holder.repoNameTxt = (TextView)convertView.findViewById(R.id.repoNameTxt);
        holder.repoUrlTxt = (TextView)convertView.findViewById(R.id.repoUrlTxt);
        holder.repoSizeTxt = (TextView)convertView.findViewById(R.id.repoSizeTxt);

        GitRepo repo = gitRepoList.get(position);
        holder.repoIdTxt.setText(String.valueOf(repo.getId()));
        holder.repoNameTxt.setText(repo.getName());
        holder.repoUrlTxt.setText(repo.getUrl());
        holder.repoSizeTxt.setText(String.valueOf(repo.getSize()));

        return convertView;
    }

    private class ViewHolder{
        TextView repoIdTxt;
        TextView repoNameTxt;
        TextView repoUrlTxt;
        TextView repoSizeTxt;
    }

    public void setGitRepoList(List<GitRepo> gitRepoList) {
        this.gitRepoList = gitRepoList;
        notifyDataSetChanged();
    }
}
