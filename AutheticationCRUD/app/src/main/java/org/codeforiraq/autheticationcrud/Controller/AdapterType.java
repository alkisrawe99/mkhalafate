package org.codeforiraq.autheticationcrud.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.codeforiraq.autheticationcrud.MainActivity;
import org.codeforiraq.autheticationcrud.Model.Car;
import org.codeforiraq.autheticationcrud.Model.Type;
import org.codeforiraq.autheticationcrud.R;
import org.codeforiraq.autheticationcrud.SERVER.URLs;
import org.codeforiraq.autheticationcrud.UI.EditData;
import org.codeforiraq.autheticationcrud.UI.Editcar;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codeforiraq.autheticationcrud.viewalluser;
import org.codeforiraq.autheticationcrud.viewallcar;
public class AdapterType  extends RecyclerView.Adapter<AdapterType.MyViewHolder> {

    private Context context;
    private List<Type> typeList;

    public AdapterType(Context context, List<Type> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public AdapterType.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addviolation,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterType.MyViewHolder holder, final int position) {
        final Type type = typeList.get(position);
        holder.car_name.setText(type.getName_vio());
        holder.car_num.setText(type.getPrice());



    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView car_name, car_num ;
        public ImageView delete,edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_name = itemView.findViewById(R.id.nameTextView2);

        }
    }




}
