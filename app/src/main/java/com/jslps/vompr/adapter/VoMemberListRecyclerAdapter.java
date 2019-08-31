package com.jslps.vompr.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jslps.vompr.R;
import com.jslps.vompr.listener.OnFragmentListItemSelectListener;
import com.jslps.vompr.model.Table13Db;
import com.jslps.vompr.model.Table8Db;
import java.util.ArrayList;

public class VoMemberListRecyclerAdapter extends RecyclerView.Adapter<VoMemberListRecyclerAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<Table8Db> productDetails;
    private int selectedPosition = -1;
    private ArrayList<Table13Db> table13DbArrayList;

    public VoMemberListRecyclerAdapter(Activity activity, ArrayList<Table8Db> pDetailsList, ArrayList<Table13Db> arrayListMeetingData) {
        this.productDetails = pDetailsList;
        this.context = activity;
        this.table13DbArrayList = arrayListMeetingData;
    }


    @Override
    public VoMemberListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.layout_row_vomember, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final VoMemberListRecyclerAdapter.ViewHolder holder, final int position) {
        final Table8Db table8Db = productDetails.get(position);
        holder.text_order_id.setText(table8Db.getVocode());
        if (table13DbArrayList != null && table13DbArrayList.size() > 0) {
            holder.btngenrateMeetings.setVisibility(View.VISIBLE);
            holder.btnreopenmeeting.setVisibility(View.GONE);
            holder.btneditemeeting.setVisibility(View.GONE);
            holder.btncloseemeeting.setVisibility(View.GONE);
            holder.btndeletemeeting.setVisibility(View.GONE);
        } else {
            if (table8Db.getFlagopen().equals("Close")) {
                holder.btngenrateMeetings.setVisibility(View.VISIBLE);
                holder.btnreopenmeeting.setVisibility(View.VISIBLE);
                holder.btneditemeeting.setVisibility(View.GONE);
                holder.btncloseemeeting.setVisibility(View.GONE);
                holder.btndeletemeeting.setVisibility(View.VISIBLE);
            } else {
                holder.btngenrateMeetings.setVisibility(View.GONE);
                holder.btnreopenmeeting.setVisibility(View.GONE);
                holder.btneditemeeting.setVisibility(View.VISIBLE);
                holder.btncloseemeeting.setVisibility(View.VISIBLE);
                holder.btndeletemeeting.setVisibility(View.GONE);
            }
        }
        if (selectedPosition != -1) {
            if (selectedPosition == position) {
                holder.buttonLayout.setVisibility(View.VISIBLE);
                holder.moreInfoBtnDown.setVisibility(View.GONE);
                holder.moreInfoBtnUp.setVisibility(View.VISIBLE);
            } else {
                holder.moreInfoBtnUp.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.VISIBLE);
                holder.buttonLayout.setVisibility(View.GONE);

            }
        }
        holder.moreInfoBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                holder.buttonLayout.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.GONE);
                holder.moreInfoBtnUp.setVisibility(View.VISIBLE);
                notifyItemRangeChanged(0, getItemCount());
            }
        });
        holder.moreInfoBtnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectedPosition = holder.getAdapterPosition();
                // notifyItemChanged(selectedPosition);
                holder.buttonLayout.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.VISIBLE);
                holder.moreInfoBtnUp.setVisibility(View.GONE);
                //notifyItemRangeChanged(0, getItemCount());
            }
        });
        if (table8Db.getStatus().equals("true")) {
            holder.text_status.setText("Completed");
            holder.mView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green));
        } else {
            holder.mView.setBackgroundColor(ContextCompat.getColor(context, R.color.pink));
            holder.text_status.setText("InComplete");
        }
        holder.text_date.setText(table8Db.getFormationdate());
        holder.text_fedrationname.setText(table8Db.getFedrationname());
        holder.text_meetingdate.setText(table8Db.getMeetingdate());
        holder.text_meetingno.setText(table8Db.getMeetingno());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemSelected(R.id.linearLayout, table8Db);
            }
        });
        holder.btngenrateMeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemSelected(R.id.btngenrateMeeting, table8Db);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    private OnFragmentListItemSelectListener mListener;
    public void setListener(OnFragmentListItemSelectListener mListener) {
        this.mListener = mListener;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView text_fedrationname, text_date, text_order_id, text_meetingno, text_status, text_meetingdate;
        private ImageView moreInfoBtnDown, moreInfoBtnUp;
        private LinearLayout buttonLayout, linearLayout;
        private Button btnreopenmeeting, btngenrateMeetings, btneditemeeting, btncloseemeeting, btndeletemeeting;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            text_order_id = itemView.findViewById(R.id.text_vocode);
            text_status = itemView.findViewById(R.id.text_status);
            text_date = itemView.findViewById(R.id.text_date);
            text_fedrationname = itemView.findViewById(R.id.text_fedrationname);
            moreInfoBtnUp = itemView.findViewById(R.id.moreInfoUp);
            moreInfoBtnDown = itemView.findViewById(R.id.moreInfoDown);
            buttonLayout = itemView.findViewById(R.id.buttonLayout);
            btnreopenmeeting = itemView.findViewById(R.id.btngreopenmeeting);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            btngenrateMeetings = itemView.findViewById(R.id.btngenrateMeeting);
            btneditemeeting = itemView.findViewById(R.id.btneditemeeting);
            btncloseemeeting = itemView.findViewById(R.id.btnclosemeeting);
            btndeletemeeting = itemView.findViewById(R.id.btndeletemeeting);
            text_meetingno = itemView.findViewById(R.id.text_meetingno);
            text_meetingdate = itemView.findViewById(R.id.text_meetingdate);
        }
    }
}

