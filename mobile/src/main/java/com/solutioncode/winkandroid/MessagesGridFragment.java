package com.solutioncode.winkandroid;


import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesGridFragment extends Fragment {


    public static final String TAG = "MessagesGridFragment";

    @InjectView(R.id.invite_img)
    ImageView inviteBtn;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.gridView)
    GridView gridView;
    ContectsGridAdapter mAdapter;

    public MessagesGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View contentView = inflater.inflate(R.layout.fragment_messages_grid, container, false);
        ButterKnife.inject(this, contentView);
        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(null);
        gridView.setAdapter(mAdapter = new ContectsGridAdapter());
        gridView.setOnItemClickListener(onItemClickListener);
        inviteBtn.setOnClickListener(onClickListener);

        return contentView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.invite_img:
                    startActivity(new Intent(getActivity(), InviteFriendsActivity.class));
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };

    class ContectsGridAdapter extends BaseAdapter {

        private LayoutInflater inflater = LayoutInflater.from(getActivity());
        private List<Integer> checked = new ArrayList<>();

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ContactViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.contact_grid_item, parent, false);
                holder = new ContactViewHolder(convertView);
                convertView.setTag(holder);
            } else
                holder = (ContactViewHolder) convertView.getTag();

            if(position % 2 == 0){
                holder.messageCountTxt.setVisibility(View.GONE);
                holder.statusBtn.setBackgroundResource(R.drawable.contact_circle_white);
                holder.statusBtn.setImageResource(R.drawable.face_4);
                holder.avatarImg.setBorderWidth(0);
            }
            if(position % 3 == 0){
                holder.messageCountTxt.setVisibility(View.GONE);
                holder.statusBtn.setBackgroundResource(R.drawable.contact_circle_white);
                holder.statusBtn.setImageResource(R.drawable.face_1);
                holder.avatarImg.setBorderWidth(0);
            }
            if(position % 4 == 0){
                holder.messageCountTxt.setVisibility(View.VISIBLE);
                holder.statusBtn.setBackgroundResource(R.drawable.contact_circle_primary);
                holder.statusBtn.setImageResource(0);
                holder.avatarImg.setBorderWidth(4);
                holder.messageCountTxt.setText(String.valueOf((position / 2) + 1));
            }


            return convertView;
        }


        class ContactViewHolder {

            @InjectView(R.id.name_txt)
            TextView nameTxt;
            @InjectView(R.id.status_btn)
            CircleImageView statusBtn;
            @InjectView(R.id.avatar_img)
            CircleImageView avatarImg;
            @InjectView(R.id.message_count_txt)
            TextView messageCountTxt;

            public ContactViewHolder(View view) {
                ButterKnife.inject(this, view);
            }

        }
    }

}
