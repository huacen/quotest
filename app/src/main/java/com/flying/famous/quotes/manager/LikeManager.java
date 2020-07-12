package com.flying.famous.quotes.manager;

import android.util.Log;

import com.flying.famous.quotes.MyApp;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.db.entity.Like;
import com.flying.famous.quotes.db.entity.Quotes;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LikeManager {
    private static final String TAG = LikeManager.class.getSimpleName();
    private Map<Long, Like> likeMap = new ConcurrentHashMap<>();

    private static class Inner {
        private static final LikeManager INSTANCE = new LikeManager();
    }

    public static LikeManager getInstance() {
        return Inner.INSTANCE;
    }

    private LikeManager() {
    }

    public void init() {
        List<Like> likes = DBManager.INSTANCE().getLikeDao().loadAll();
        Log.i(TAG, "likes.size = " + likes);
        if (likes != null && likes.size() > 0) {
            for (Like like : likes) {
                likeMap.put(like.getQid(), like);
            }
        }
        Log.i(TAG, "likeMap.size = " + likeMap.size());
    }

    public Collection<Long> getLikesQuotesIds() {
        return likeMap.keySet();
    }

    public void likeOrNot(Quotes quotes) {
        if (likeMap.containsKey(quotes.getId())) {
            DBManager.INSTANCE().getLikeDao().delete(likeMap.get(quotes.getId()));
            likeMap.remove(quotes.getId());
        } else {
            Like like = new Like();
            like.setQid(quotes.getId());
            like.setUuid(MyApp.uuid);
            long id = DBManager.INSTANCE().getLikeDao().insert(like);
            if (id > 0) {
                like.setId(id);
                likeMap.put(quotes.getId(), like);
            }
        }
    }

    public boolean isLike(Quotes quotes) {
        return likeMap.containsKey(quotes.getId());
    }
}
