package com.cs.cardwhere;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CardFragment extends Fragment {

    View view;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);

        mViewPager = view.findViewById(R.id.pager);
        mTabLayout = view.findViewById(R.id.tabs);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setViewPager(){
        // Create Fragment
        CardListFragment cardListFragment = new CardListFragment();
        CardsMapsFragment cardsMapsFragment = new CardsMapsFragment();

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(cardListFragment);
        fragmentList.add(cardsMapsFragment);

        CardPagerFragmentAdapter myFragmentAdapter = new CardPagerFragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(myFragmentAdapter);
    }




}
