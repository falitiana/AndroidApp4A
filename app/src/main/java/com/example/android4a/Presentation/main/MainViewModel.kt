package com.example.android4a.Presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.android4a.Domain.usecase.CreateUserUseCase
import com.example.android4a.Domain.entity.User
import com.example.android4a.Domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.example.android4a.injection.Singletons.gson
import com.google.gson.reflect.TypeToken;
import org.koin.core.KoinApplication.Companion.init

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

class MainViewModel(
   private val createUserUseCase: CreateUserUseCase,
   private val getUserUseCase: GetUserUseCase,

   private SharedPreferences sharedPreferences; {
    val loginLiveData: Any
}
private Gson gson;
   private MainActivity view;
   private Character Character;

): ViewModel(){
    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO){
            val user:User? = getUserUseCase.invoke(emailUser)
            val loginStatus = if(user != null){
                    LoginSucces(user.email)
                }else{
                    LoginError
                }
            withContext(Dispatchers.Main){
                loginLiveData.value=loginStatus
            }

            )}
    }

    init {
        List<Character> characterList = getDataFromCache ();

        if (characterList != null) {
            view.ShowList (characterList);
        } else {
            makeApiCall ();
        }
    }

    private void makeApiCall () {
        Call<ApplicationResponse> call = Singletons.getApplicationApi ().getApplicationResponse();
        call.enqueue (new Callback<ApplicationResponse> () {

            @Override
            public void onResponse(Call<ApplicationResponse> call, Response<RestApplicationResponse> response) {

                if (response.isSuccessful () && response.body () != null) {
                    List<Character> characterList = response.body ().getResults ();
                    saveList (characterList);
                    view.ShowList (characterList);
                } else {
                    view.ShowError ();
                }
            }

            @Override
            public void onFailure(Call<RestApplicationResponse> call, Throwable t) {
                view.ShowError ();
            }
        });
    }

    private void saveList(List<Character> characterList) {
        String jsonString = gson.toJson (characterList);

        sharedPreferences
            .edit ()
            .putString ("jsonCharacterList", jsonString)
            .apply ();
    }

    private List<Character> getDataFromCache () {

        String  jsonCharacter = sharedPreferences.getString(Constants.KEY_CHARACTER_LIST, null);

        if (jsonCharacter == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Character>> () {}.getType ();
            return gson.fromJson (jsonCharacter, listType);
        }
    }

    public void onItemClick(Character item) {
        view.navigateToDetails(Character);
    }
}

}
