package com.jslps.vompr.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jslps.vompr.R;
import com.jslps.vompr.listener.OnFragmentListItemSelectListener;
import com.jslps.vompr.model.Table9Db;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class MemberListRecyclerAdapter extends RecyclerView.Adapter<MemberListRecyclerAdapter.ViewHolder> {
    private ArrayList<Table9Db> mFilteredList;
    Activity context;
    private ArrayList<Table9Db> productDetails;
    int selectedPosition = -1;
    String[] status = {"Active", "Inactive"};
    String[] voPost = {"Member", "Chairman", "Vice Chairman", "Treasurer", "Secretary", "Vice Secretary"};
    private String groupOfficialpostname, statusData;

    public MemberListRecyclerAdapter(Activity activity, ArrayList<Table9Db> pDetailsList) {
        this.productDetails = pDetailsList;
        this.context = activity;
        this.mFilteredList = pDetailsList;
    }


    @Override
    public MemberListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.layout_row_member, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final MemberListRecyclerAdapter.ViewHolder holder, final int position) {
        final Table9Db table9Db = productDetails.get(position);
        getdate();
       /* if (selectedPosition != -1) {
            if (selectedPosition == position) {
                holder.layoutEdit.setVisibility(View.VISIBLE);
            } else {
                holder.layoutEdit.setVisibility(View.GONE);
            }
        }*/
        if (selectedPosition != -1) {
            if (selectedPosition == position) {
                holder.layoutEdit.setVisibility(View.VISIBLE);
                holder.moreInfoBtnDown.setVisibility(View.GONE);
                holder.moreInfoBtnUp.setVisibility(View.VISIBLE);
            } else {
                holder.moreInfoBtnUp.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.VISIBLE);
                holder.layoutEdit.setVisibility(View.GONE);

            }
        }
        holder.memberName.setText(table9Db.getGropmembername());
        holder.memberId.setText(table9Db.getMemberid());
        holder.groupName.setText(table9Db.getGroupname());
        holder.groupMemberName.setText(table9Db.getGropmembername());
        holder.fatherHusbandName.setText(table9Db.getFatherhasbandname());
        holder.phoneNumber.setText(table9Db.getPhoneno());
        holder.dateOfJoining.setText(entryDate);
        holder.layoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mListener.onListItemSelected(R.id.moreInfoUp, table9Db);
                selectedPosition = holder.getAdapterPosition();
                holder.moreInfoBtnDown.setVisibility(View.GONE);
                holder.moreInfoBtnUp.setVisibility(View.VISIBLE);
                notifyItemChanged(selectedPosition);
                holder.layoutEdit.setVisibility(View.VISIBLE);
                notifyItemRangeChanged(0, getItemCount());
            }
        });
        holder.moreInfoBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                holder.moreInfoBtnDown.setVisibility(View.GONE);
                holder.moreInfoBtnUp.setVisibility(View.VISIBLE);
                notifyItemChanged(selectedPosition);
                holder.layoutEdit.setVisibility(View.VISIBLE);
                notifyItemRangeChanged(0, getItemCount());
            }
        });
        holder.moreInfoBtnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectedPosition = holder.getAdapterPosition();
                // notifyItemChanged(selectedPosition);
                holder.layoutEdit.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.VISIBLE);
                holder.moreInfoBtnUp.setVisibility(View.GONE);
                //notifyItemRangeChanged(0, getItemCount());
            }
        });
        holder.dateOfJoining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance(Locale.ENGLISH);
                Integer ALyear = c.get(Calendar.YEAR);
                Integer ALmonthOfYear = c.get(Calendar.MONTH);
                Integer ALdayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String dateSelected = year + "-" + (getProperFormat(dayOfMonth) + "-" + getProperFormat(monthOfYear + 1));
                        holder.dateOfJoining.setText(dateSelected);
                    }
                }, ALyear, ALmonthOfYear, ALdayOfMonth);


                dpd.getDatePicker().setMinDate(System.currentTimeMillis() /*+ 24 * 60 * 60 * 1000*/);
                c.add(Calendar.YEAR, 5);
                dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
                dpd.show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, status);
        holder.spinnerMemberStatus.setAdapter(adapter);
        ArrayAdapter<String> adapterMember = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, voPost);
        holder.groupVoPost.setAdapter(adapterMember);
        holder.updateMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Table9Db book = Table9Db.findById(Table9Db.class, 1);
                book.setPhoneno("78787255");
                book.save();*/
               /* String id = UUID.randomUUID().toString();
                Table9Db book = Table9Db.findById(Table9Db.class, 1);
                System.out.println("fgdfhgfdghdgahfjhawkihafwerb"+new Gson().toJson(book));
                Table9Db table9Db1 = new Table9Db(table9Db.getVoCode(), statusData,holder.phoneNumber.getText().toString(), groupOfficialpostname,
                        table9Db.getVoCode(), table9Db.getMemberid(), holder.dateOfJoining.getText().toString(), holder.fatherHusbandName.getText().toString(),
                        table9Db.getGropmembername(), table9Db.getGroupname(),id,"false");
                table9Db1.save();
                //book.setPhoneno("123456789");  // modify the values
                table9Db1.save(); // updates the previous entry with new values.*/
                ArrayList<Table9Db> arrayListVillage1 = (ArrayList<Table9Db>) Select.from(Table9Db.class)
                        .where(Condition.prop("uuid").eq(table9Db.getUuid())).list();
                System.out.println("fgdfhgfdghdgahfjhawkihafwerb" + new Gson().toJson(arrayListVillage1));

                table9Db.setPhoneno(holder.phoneNumber.getText().toString());
                table9Db.setFatherhasbandname(holder.fatherHusbandName.getText().toString());
                table9Db.setGropofofficerpostname(groupOfficialpostname);
                table9Db.setStatus(statusData);
                table9Db.setDateofjoin(holder.dateOfJoining.getText().toString());
                table9Db.save();

                // Toast.makeText(context, "Data Update Successfully", Toast.LENGTH_SHORT).show();
                holder.layoutEdit.setVisibility(View.GONE);
                holder.moreInfoBtnDown.setVisibility(View.VISIBLE);
                holder.moreInfoBtnUp.setVisibility(View.GONE);

            }
        });
        holder.spinnerMemberStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                    statusData = "1";
                else statusData = "0";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.groupVoPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) groupOfficialpostname = "6";
                else if (i == 1) statusData = "1";
                else if (i == 2) statusData = "2";
                else if (i == 3) statusData = "3";
                else if (i == 4) statusData = "4";
                else if (i == 5) statusData = "5";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public int getCount() {
        return productDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    OnFragmentListItemSelectListener mListener;

    public void setListener(OnFragmentListItemSelectListener mListener) {
        this.mListener = mListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView memberName, groupName, groupMemberName, memberId, dateOfJoining;
        private EditText fatherHusbandName, phoneNumber;
        private Button updateMember;
        private LinearLayout layoutClick, layoutEdit;
        Spinner spinnerMemberStatus, groupVoPost;
        private ImageView moreInfoBtnDown, moreInfoBtnUp;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            layoutClick = itemView.findViewById(R.id.layoutClick);
            memberName = itemView.findViewById(R.id.memberName);
            layoutEdit = itemView.findViewById(R.id.layoutEdit);
            groupName = itemView.findViewById(R.id.groupName);
            groupMemberName = itemView.findViewById(R.id.groupmemberName);
            fatherHusbandName = itemView.findViewById(R.id.fatherhusbandName);
            phoneNumber = itemView.findViewById(R.id.phonenumber);
            spinnerMemberStatus = itemView.findViewById(R.id.memberStatusSpiner);
            groupVoPost = itemView.findViewById(R.id.groupvopost);
            memberId = itemView.findViewById(R.id.membercode);
            dateOfJoining = itemView.findViewById(R.id.dateofjoining);
            moreInfoBtnUp = itemView.findViewById(R.id.moreInfoUp);
            moreInfoBtnDown = itemView.findViewById(R.id.moreInfoDown);
            updateMember = itemView.findViewById(R.id.updateMember);
        }
    }

    String entryDate, currentDate;

    private void getdate() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateString = formatter.format(currentDate);
        entryDate = formattedDateString;
        //    tv_date.setText(entryDate);
    }

    private String getProperFormat(int hhORmm) {
        String temp = hhORmm + "";
        if (temp.length() == 1) {
            temp = "0" + temp;
        } else {

        }
        return temp;
    }

    public Filter getCustomContactFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = productDetails;
                } else {
                    ArrayList<Table9Db> filteredList = new ArrayList<>();
                    for (Table9Db contact : productDetails) {
                        if (contact.toString().toLowerCase().contains(charString) || contact.getGropmembername().toLowerCase().contains(charString) || contact.getMemberid().contains(charString)) {
                            filteredList.add(contact);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                filterResults.count = mFilteredList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Table9Db>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

