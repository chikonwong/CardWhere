package com.cs.cardwhere;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.cardwhere.Controller.CallBack;
import com.cs.cardwhere.Controller.CardController;
import com.cs.cardwhere.Models.Card;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class CardListFragment extends Fragment {
    private View view;

    private ArrayList<Card> cards = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card_list, container, false);

        // get current user id
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        final String userId = sharedPreferences.getString("USER_ID", "");

        CardController cardController = new CardController();
        // Init Data in Recycler View
        cardController.getCards(userId, new CallBack() {
            @Override
            public void onSuccess(ArrayList<Card> CardsList) {
                cards = CardsList;
                // Init Recycler View
                initRecyclerView();
            }
            @Override
            public void onFail(String msg) {
                Log.d(TAG, "get result fail" + msg);
            }
        });
        return view;
    }

    private void initRecyclerView() {
        RecyclerView cardRecyclerView;
        cardRecyclerView = view.findViewById(R.id.card_recycler_view);
        CardAdapter cardAdapter;
        cardAdapter = new CardAdapter(getActivity(), cards);
        cardRecyclerView.setAdapter(cardAdapter);

        // recycler view setting
        cardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        // OnClick Listener
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Card data) {
                Toast.makeText(getActivity(),"This is " + data.getCompany(),Toast.LENGTH_SHORT).show();
            }


        });
    }

}
