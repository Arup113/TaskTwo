package com.arupkumar.tasktwo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.arupkumar.tasktwo.Config.AppConfig;
import com.arupkumar.tasktwo.Model.User;
import com.arupkumar.tasktwo.R;
import com.arupkumar.tasktwo.Utils.ShowLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.MyViewHolder> implements Filterable {

    private List<User> tempUserList;
    private List<User> userList;
    private Context mContext;

    public UsersListAdapter(Context mContext, List<User> medicineDetailList) {
        //super();
        this.mContext = mContext;
        this.tempUserList = medicineDetailList;
        this.userList = new ArrayList<>(tempUserList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence filterValue) {
                final FilterResults results = new FilterResults();
                if (filterValue.toString().trim().length() == 0) {
                    results.count = tempUserList.size();
                    results.values = tempUserList;
                } else {
                    List<User> recyclerViewBaseItems = new ArrayList<>();
                    for (int i = 0; i < tempUserList.size(); i++) {
                        User boardingPointItem = (User) tempUserList.get(i);
                        if (boardingPointItem.getFirstName().trim().toLowerCase().contains(filterValue.toString().trim().toLowerCase()) ||
                                boardingPointItem.getLastName().trim().toLowerCase().contains(filterValue.toString().trim().toLowerCase()) ||
                                boardingPointItem.getPhones().getMobile().trim().toLowerCase().contains(filterValue.toString().trim().toLowerCase()) ||
                                boardingPointItem.getGender().trim().toLowerCase().contains(filterValue.toString().trim().toLowerCase())) {
                            recyclerViewBaseItems.add(boardingPointItem);
                        }
                    }
                    results.count = recyclerViewBaseItems.size();
                    results.values = recyclerViewBaseItems;
                }
                ShowLog.e("VALUES", results.values.toString());
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public User getItem(int position) {
        return userList.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_users, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (holder instanceof RecyclerView.ViewHolder) {
            try {
                final User user = userList.get(position);
                String imgUrl;
                if (user.getGender().equalsIgnoreCase("male")) {
                    imgUrl = AppConfig.USER_MEN_URL + user.getPhoto() + ".jpg";
                } else if (user.getGender().equalsIgnoreCase("female")) {
                    imgUrl = AppConfig.USER_WOMEN_URL + user.getPhoto() + ".jpg";
                } else {
                    imgUrl = "";
                }

                Glide.with(mContext).load(imgUrl).asBitmap().placeholder(R.drawable.loading_img)
                        .error(R.drawable.loading_img_not_found).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(new BitmapImageViewTarget(holder.list_row_users_iv_profile_image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.list_row_users_iv_profile_image.setImageDrawable(circularBitmapDrawable);
                    }
                });

                holder.list_row_users_tv_first_name.setText(user.getFirstName());
                holder.list_row_users_tv_last_name.setText(user.getLastName());
                holder.list_row_users_tv_mobile_no.setText(user.getPhones().getMobile());


            } catch (IndexOutOfBoundsException ex) {
                ShowLog.e("Error", ex.getMessage());
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView list_row_users_iv_profile_image;
        private TextView list_row_users_tv_first_name;
        private TextView list_row_users_tv_last_name;
        private TextView list_row_users_tv_mobile_no;

        public MyViewHolder(View view) {
            super(view);
            list_row_users_iv_profile_image = view.findViewById(R.id.list_row_users_iv_profile_image);
            list_row_users_tv_first_name = view.findViewById(R.id.list_row_users_tv_first_name);
            list_row_users_tv_last_name = view.findViewById(R.id.list_row_users_tv_last_name);
            list_row_users_tv_mobile_no = view.findViewById(R.id.list_row_users_tv_mobile_no);
        }
    }
}
