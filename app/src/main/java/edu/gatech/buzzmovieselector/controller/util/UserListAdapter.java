package edu.gatech.buzzmovieselector.controller.util;

import android.annotation.SuppressLint;
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

    private static LayoutInflater inflater = null;
    private List<User> users = new ArrayList<>();
    private Context context;

    /**
     * Constructor for user list adaptor
     * @param users the users to display
     * @param context the context of the activity
     */
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

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        View newView;
        if (view == null) {
            newView = inflater.inflate(R.layout.user_list_item, null);
        } else {
            newView = view;
        }
        final TextView listItemText = (TextView) newView.findViewById(R.id
            .usernameLabel);
        final User user = users.get(position);
        listItemText.setText(user.getUsername() + " (" + user.getUserStatus()
            + ")");

        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent starter = new Intent(context, UserStatusActivity
                    .class);
                starter.putExtra(UserStatusActivity.CURRENT_USER, user);
                context.startActivity(starter);
            }
        });

        return newView;
    }

}
