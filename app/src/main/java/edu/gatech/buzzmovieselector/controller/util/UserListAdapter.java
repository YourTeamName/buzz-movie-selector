package edu.gatech.buzzmovieselector.controller.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import edu.gatech.buzzmovieselector.R;
import edu.gatech.buzzmovieselector.controller.activity.UserStatusActivity;
import edu.gatech.buzzmovieselector.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User List Adapter allows User POJOs to be displayed in ListViews
 */
public class UserListAdapter extends BaseAdapter implements ListAdapter {

    private List<User> users = new ArrayList<>();
    private Context context;

    private static LayoutInflater inflater = null;

    public UserListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int pos) {
        return users.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.user_list_item, null);
        }

        TextView listItemText = (TextView) view.findViewById(R.id
                .usernameLabel);
        final User user = users.get(position);
        listItemText.setText(user.getUsername() + " (" + user.getUserStatus()
                + ")");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = new Intent(context, UserStatusActivity.class);
                starter.putExtra(UserStatusActivity.CURRENT_USER, user);
                context.startActivity(starter);
            }
        });

        return view;
    }

}
