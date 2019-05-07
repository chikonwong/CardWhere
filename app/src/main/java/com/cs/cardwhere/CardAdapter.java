package com.cs.cardwhere;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.cardwhere.Controller.CardController;
import com.cs.cardwhere.Bean.CardBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private List<CardBean> cardList;
    private Context context;

    CardAdapter(Context context, List<CardBean> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  = View.inflate(context, R.layout.list_card_item,null);
        return new ViewHolder(itemView );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardBean card = cardList.get(position);

        holder.textCompany.setText(card.getCompany());
        holder.textName.setText(card.getName());
        holder.textTel.setText(card.getTel());
        holder.textAddress.setText(card.getAddress());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardController cardController = new CardController(context);
                cardController.deleteCard(card.getCardId());
            }
        });

        String imageUrl = card.getImageUri().replace("http", "https");
        Picasso.get()
                .load(imageUrl)
                .resize(350, 200)
                .centerCrop()
                .into(holder.imageCard);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        ImageView imageCard;
        TextView textCompany;
        TextView textName;
        TextView textTel;
        TextView textAddress;
        Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_recycler_view);
            imageCard = itemView.findViewById(R.id.imgCard);
            textCompany = itemView.findViewById(R.id.textCompany);
            textName = itemView.findViewById(R.id.textName);
            textTel = itemView.findViewById(R.id.textTel);
            textAddress = itemView.findViewById(R.id.textAddress);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            // set itemView onClick Listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, cardList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, CardBean data);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
