package com.tm.arifin.timbangan.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tm.arifin.timbangan.R;
import com.tm.arifin.timbangan.model.Pelanggan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arifin on 24/10/16.
 */

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.MyView> implements Filterable {

    public List<Pelanggan> pelangganList=null;
    public List<Pelanggan> pelanggFilter=null;
    private PelangganFilter filter;


    public class MyView extends RecyclerView.ViewHolder {
        TextView hp, namaPelanggan, alamatPelanggan,statusBaca;
        LinearLayout linearLayout;

        private String mItem;
        private TextView mTextView;

        public MyView(final View itemView) {
            super(itemView);
            hp = (TextView) itemView.findViewById(R.id.hp);
            namaPelanggan = (TextView) itemView.findViewById(R.id.tnama);
            alamatPelanggan = (TextView) itemView.findViewById(R.id.talamat);
            statusBaca = (TextView) itemView.findViewById(R.id.tstatus);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.clik_list);
        }

    }

    public PelangganAdapter(List<Pelanggan> pelangganList)
    {
        this.pelangganList = pelangganList;
        this.pelanggFilter = pelangganList;
        Log.e("jml data adapter", "" + pelangganList.size());
    }


    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pelanggan, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {
        Pelanggan pelanggans = pelanggFilter.get(position);

        if (!pelanggans.getKdPelangganOld().equals("")) {
            holder.hp.setText("" + pelanggans.getHp() + " / " + pelanggans.getTelp());
        }

        if (pelanggans.getNamaPelanggan() != "") {
            holder.namaPelanggan.setText(pelanggans.getNamaPelanggan());
        }

        if (pelanggans.getAlamat() != "") {
            holder.alamatPelanggan.setText(pelanggans.getAlamat());
        }

        if (pelanggans.getStatus().equals("aktif")) {
            holder.statusBaca.setBackgroundResource(R.drawable.status_success);
            holder.statusBaca.setText("Aktif");
        }else if (pelanggans.getStatus().equals("pending")) {
            holder.statusBaca.setBackgroundResource(R.drawable.status_pending);
            holder.statusBaca.setText("Pending");
        }
        else if (pelanggans.getStatus().equals("survey")) {
            holder.statusBaca.setBackgroundResource(R.drawable.status_pending);
            holder.statusBaca.setText("Survey");
        }
        else{
            holder.statusBaca.setBackgroundResource(R.drawable.status_gagal);
            holder.statusBaca.setText("Tidak Aktif");
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }

    @Override
    public int getItemCount() {
        return pelanggFilter.size();
    }



    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new PelangganFilter();
        return filter;
    }



    private class PelangganFilter extends Filter {
        private PelangganAdapter adapter;

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String data = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            if (data.length() > 0) {
                Log.e("pencarian", "-" + data.toString());
                List<Pelanggan> filteredList = new ArrayList<>(pelangganList);
                List<Pelanggan> nList = new ArrayList<>();
                int count = filteredList.size();
                for (int i = 0; i < count; i++) {
                    Pelanggan item = filteredList.get(i);
                    String name = item.getNamaPelanggan().toLowerCase();
                    String alamat = item.getAlamat().toLowerCase();
                    if (name.startsWith(data) || alamat.startsWith(data))
                        nList.add(item);
                }
                results.count = nList.size();
                results.values = nList;
            } else {
                List<Pelanggan> list = new ArrayList<>(pelangganList);
                results.count = list.size();
                results.values = list;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            pelanggFilter = (ArrayList<Pelanggan>) results.values;
            Log.e("cari","mulai");
            notifyDataSetChanged();
        }

    }
}
