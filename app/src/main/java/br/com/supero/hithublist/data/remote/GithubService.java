package br.com.supero.hithublist.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.supero.hithublist.data.model.Pull;
import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.data.remote.parse.RepositoryParser;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/search/repositories?q=language:Java&sort=stars")
    Observable<List<Repository>> getRepositories();

    @GET("/repos/{owner}/{repository}/pulls")
    Observable<List<Pull>> getPulls(@Path("owner") String owner,@Path("repository") String repository);

    class Creator {
        public static GithubService newGithubService() {
            Type listType = new TypeToken<List<Repository>>(){}.getType();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .registerTypeAdapter(listType, new RepositoryParser())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GithubService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }
}
