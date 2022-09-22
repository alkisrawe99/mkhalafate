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
import org.codeforiraq.autheticationcrud.Model.User;
import org.codeforiraq.autheticationcrud.R;
import org.codeforiraq.autheticationcrud.SERVER.URLs;
import org.codeforiraq.autheticationcrud.UI.EditData;
import org.codeforiraq.autheticationcrud.viewallcar;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codeforiraq.autheticationcrud.viewalluser;

public class Adapter  extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public Adapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_content,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, final int position) {
        final User user = userList.get(position);

        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.status.setText(user.getStatus());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(position,user.getId());
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditData.class);
                intent.putExtra("id",user.getId());
                intent.putExtra("name",user.getName());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("status",user.getStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, email,status ;
        public ImageView delete,edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView2);
            email = itemView.findViewById(R.id.authorTextView);


            status = itemView.findViewById(R.id.priceTextView2);




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
                URLs.URL_DELETE_DATA+id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getString("message")== "user delete sucess"){
                        Toast.makeText(context,response.getString("message")
                                ,Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(context,"user delete sucess"
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

        userList.remove(position);
        viewalluser.notifyAdapter();
    }

}
