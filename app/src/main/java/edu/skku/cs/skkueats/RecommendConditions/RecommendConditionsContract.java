package edu.skku.cs.skkueats.RecommendConditions;

import java.util.ArrayList;

public interface RecommendConditionsContract {
    interface contactView {
        void showRecommend(RecommendConditionItem menuRecommends);
        void initView();
    }

    interface contactModel {
        void fetchRecommends();
        void pushRecommendsToViewer(ArrayList<RecommendConditionItem> reviewArrays);
    }
}
