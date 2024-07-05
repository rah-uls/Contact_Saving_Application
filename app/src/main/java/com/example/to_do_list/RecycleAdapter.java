package com.example.to_do_list;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RecycleAdapter extends RecyclerView.Adapter <RecycleAdapter.ViewHolder> {
    Context context;
    ArrayList<Structure_List> arrayList;
    public RecycleAdapter(Context context,ArrayList<Structure_List> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.to_do_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).image);
        holder.name.setText(arrayList.get(position).name);
        holder.number.setText(arrayList.get(position).number);
        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.update_lay);
                EditText editText1=dialog.findViewById(R.id.ediname);
                EditText editText2=dialog.findViewById(R.id.edinumber);
                Button button=dialog.findViewById(R.id.btn);
                TextView textView=dialog.findViewById(R.id.ttview);
                textView.setText("UPDATE CONTACT");
                button.setText("Update");
                editText1.setText(arrayList.get(position).name);
                editText2.setText(arrayList.get(position).number);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="";
                        String number="";
                        if (editText1.getText().toString().equals("")){
//                            Toast.makeText(context, "Please Fill The Name- ", Toast.LENGTH_SHORT).show();
                            editText1.setError("please fill");
                            editText1.requestFocus();
                            return;
                        }
                        else{
                            name=editText1.getText().toString();

                        }
                        if (editText2.getText().toString().equals("")){
//                            Toast.makeText(context, "Please Fill The Number- ", Toast.LENGTH_SHORT).show();
                            editText2.setError("please fill");
                            editText2.requestFocus();
                            return;
                        }
                        else {
                            number=editText2.getText().toString();

                        }
                        arrayList.set(position,new Structure_List(name,number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(context)
                        .setTitle("DELETE CONTACT")
                        .setMessage("ARE YOU SURE WANT TO DELETE THIS CONTACT ?")
                        .setIcon(R.drawable.delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alert.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,number;
        LinearLayout llrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            llrow=itemView.findViewById(R.id.llrow);
        }
    }
}
