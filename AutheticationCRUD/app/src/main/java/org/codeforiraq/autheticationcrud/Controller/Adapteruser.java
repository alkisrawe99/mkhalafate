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
import org.codeforiraq.autheticationcrud.Model.Violation;
import org.codeforiraq.autheticationcrud.R;
import org.codeforiraq.autheticationcrud.SERVER.URLs;
import org.codeforiraq.autheticationcrud.UI.EditData;
import org.codeforiraq.autheticationcrud.UI.Editcar;
import org.codeforiraq.autheticationcrud.viewSearch;
import org.codeforiraq.autheticationcrud.viewallvio;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codeforiraq.autheticationcrud.viewalluser;
import org.codeforiraq.autheticationcrud.viewallcar;
import org.codeforiraq.autheticationcrud.driverviolation;
public class Adapteruser  extends RecyclerView.Adapter<Adapteruser.MyViewHolder> {

    private Context context;
    private List<Violation> vioList;

    public Adapteruser(Context context, List<Violation> vioList) {
        this.context = context;
        this.vioList = vioList;
    }

    @NonNull
    @Override
    public Adapteruser.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_content,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapteruser.MyViewHolder holder, final int position) {
        final Violation vio = vioList.get(position);
        holder.name_vio.setText(vio.getName_vio());
        holder.name_of_driver.setText(vio.getName_driver());
        holder.location.setText(vio.getLocation());
        holder.car_num.setText(vio.getNum_car());
        holder.car_name.setText(vio.getNam_car());
        holder.price.setText(vio.getPrice());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(position,vio.getId());
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Editcar.class);
                intent.putExtra("id",vio.getId());
                intent.putExtra("name_of_driver",vio.getName_driver());
                intent.putExtra("car_name", vio.getNam_car());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vioList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView car_name, name_of_driver,name_vio,location ,car_num,price;
        public ImageView delete,edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_vio = itemView.findViewById(R.id.authorTextView);
            location = itemView.findViewById(R.id.nameTextView2);
            price = itemView.findViewById(R.id.priceTextView2);
            car_name = itemView.findViewById(R.id.carnameTextView2);
            car_num = itemView.findViewById(R.id.carnumTextView2);
            name_of_driver = itemView.findViewById(R.id.driveTextView2);

            delete = itemView.findViewById(R.id.imageViewDelete);
            edit = itemView.findViewById(R.id.imageViewEdit);





        }
    }



    /*private  String formatDate(String dataStr){
       try{
           SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date date = fmt.parse(dataStr);
           SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
           return fmtOut.format(date);
       }catch (ParseException e){
           Log.d("error", e.getMessage());
       }
       return "";
    }
*/
    public void deleteData(int position , int id){


        final String token = SessionManager.getInstance(context).getToken().getToken();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET,
                URLs.delete_vio+id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getString("message")== "violation delete sucess"){
                        Toast.makeText(context,response.getString("message")
                                ,Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(context,"violation delete sucess"
                                ,Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("Accept","application/json");
                params.put("Authorization","Bearer  "+ "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMWY4YzE4ODkwM2UzOTJjMGFkNDBjZTg0NzY4ZTk3MzczYTE2MTFmMTcxNzI3OWMxYzZiY2JkNTVkNTNiMTBkZjc5YTFlZTBkNmNjZWZmNTciLCJpYXQiOjE2NDA5MjQzMDMsIm5iZiI6MTY0MDkyNDMwMywiZXhwIjoxNjcyNDYwMzAzLCJzdWIiOiIzNCIsInNjb3BlcyI6W119.Us8ybzq9v-7VBpfdHtItb1v9cRVEjAzEmMUKXEi_1t_KaGldsvjjIiqiz0XWZJSa7lsmPoRvUe_gON1U6CW2oxnD7Wllwup79LbMcmi_L68fD8AMc1NlhQIbJafYu1ajDWmDUxg2ZKfixOBufkTE9mciDwuDCJVzhPVZ-pfKuw-CnnOVxImrmnsI2EEorNwxhJ_9B2qpGBYZCrA3WzG8hNiy-OMFHrkKlHqfZFUfXSMxbll-wg2aLS2DlyrcMimJKAbABkJt3UY-dfw15e9SCrmbUR6ojaAF6K9Yaf8lGv2wnlXVLHHWs2i7AU8JwAW1bJ-ehHyfbaNphvhZ3vil0Uqm65ME_7inJbnNiZjlDzkhXX15U3ds3s7RMCGSWMqMcqwwlESBodgpC5Fgq3tBZEGyeYDEYD6EblZe4BgvyA0Eh9qZwSdirfGwrQgCyFKmFGSydatlQKMwfV83mRd1UKJNyTjAOfi2hzzOnaTVKSPbJG8hIz-sxYO40domW9mDY-cxVqmUczOhQ8QnVAtuy0KQ80-RAm9eKwuYB8_7POvqSoacX-BnkzLIMUw0ITGIIUR87nFzmCEJaBttiWuNbm0bdMEjLoXoRBPTJRnuomemWWeZWibcuzarDMtayJopsEQ0XgK8F3o3FYsuluZ2-hEj1t7Frg4ddW1m4fryQHY");
                return params;
            }
        }

                ;

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

        vioList.remove(position);
        viewallvio.notifyAdapter();
    }

}
