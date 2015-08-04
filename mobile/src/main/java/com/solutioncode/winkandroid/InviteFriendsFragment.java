package com.solutioncode.winkandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InviteFriendsFragment extends Fragment {

    public static final String TAG = "InviteFriendsFragment";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.listView)
    ListView listView;

    private InviteListAdapter mAdapter;

    public InviteFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_invite_friends, container, false);
        ButterKnife.inject(this, contentView);
        setHasOptionsMenu(true);
        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(null);
        listView.setAdapter(mAdapter = new InviteListAdapter());
        listView.setOnItemClickListener(onItemClickListener);
        return contentView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                getActivity().finish();
                break;

            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView
            .OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mAdapter.checked.contains(position))
                mAdapter.checked.remove((Object) position);
            else
                mAdapter.checked.add(position);
            mAdapter.notifyDataSetChanged();
        }
    };


    class InviteListAdapter extends BaseAdapter {

        private LayoutInflater inflater = LayoutInflater.from(getActivity());
        private List<Integer> checked = new ArrayList<>();


        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            FriendViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.invite_list_item, parent, false);
                holder = new FriendViewHolder(convertView);
                convertView.setTag(holder);
            } else
                holder = (FriendViewHolder) convertView.getTag();


            if (checked.contains(position))
                holder.checkBox.setImageResource(R.drawable.accept_check_box_checked);
            else
                holder.checkBox.setImageResource(R.drawable.accept_check_box_unchecked);

            if (position < 5)
                holder.statusTxt.setVisibility(View.VISIBLE);
            else
                holder.statusTxt.setVisibility(View.INVISIBLE);
            if (position % 2 == 0) {
                holder.avatarImg.setImageResource(R.drawable.face_1);
            }
            if (position % 3 == 0) {
                holder.avatarImg.setImageResource(R.drawable.face_2);
            }
            if (position % 4 == 0) {
                holder.avatarImg.setImageResource(R.drawable.face_3);
            }
            if (position % 5 == 0) {
                holder.avatarImg.setImageResource(R.drawable.face_4);
            }

            return convertView;
        }


        class FriendViewHolder {

            @InjectView(R.id.name_txt)
            TextView nameTxt;
            @InjectView(R.id.invited_img)
            ImageView checkBox;
            @InjectView(R.id.avatar_img)
            ImageView avatarImg;
            @InjectView(R.id.status_txt)
            TextView statusTxt;

            public FriendViewHolder(View view) {
                ButterKnife.inject(this, view);
            }

        }
    }

}
