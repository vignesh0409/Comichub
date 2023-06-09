package ajiet.ise.dept.comichub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>implements Filterable {
    private Context mtx;
    private List<Product> productList;
    private  List<Product> listfull;


    public ProductAdapter(Context mtx, List<Product> productList) {
        this.mtx = mtx;
        this.productList = productList;
        listfull=new ArrayList<>(productList);
    }

    @NonNull
    @Override



    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.layout_products,null);
        return  new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") final int position) {

     final    Product product = productList.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.imageView.setImageDrawable(mtx.getResources().getDrawable(product.getImage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Comics.class);
                i.putExtra("title",productList.get(position).getTitle());
                i.putExtra("product",productList.get(position).getTitle());
                i.putExtra("link",productList.get(position).getLink());
                mtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return FilterUser;
    }

    private Filter FilterUser=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String searchtext= charSequence.toString().toLowerCase();
            List<Product>templist=new ArrayList<>();
            if(searchtext.length()==0||searchtext.isEmpty())
            {
                templist.addAll(listfull);
            }
            else {
                for (Product item:listfull){
                    if (item.getTitle().toLowerCase().contains(searchtext))
                    {
                        templist.add(item);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=templist;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((Collection<? extends Product>) filterResults.values);
            notifyDataSetChanged();

        }
    };
}


class  ProductViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    ImageView imageView;
    TextView textViewTitle;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardview);
        imageView = itemView.findViewById(R.id.imageview);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);

    }
}
