package com.example.jda8301.spellarhyme.data;

import com.example.jda8301.spellarhyme.MyApplication;
import com.example.jda8301.spellarhyme.model.ConsonantWord;
import com.example.jda8301.spellarhyme.model.SegmentedWord;
import com.example.jda8301.spellarhyme.model.VowelWord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppPreferencesHelper {
    private final Gson mGson;

    public AppPreferencesHelper() {
        mGson = new GsonBuilder().create();
    }

    public List<List<SegmentedWord>> getSegmentedWords() {
        String filename = "Files/segmentedWords.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");
            Type listType = new TypeToken<List<List<SegmentedWord>>>() {
            }.getType();
            return mGson.fromJson(jsonLetters, listType);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getSoundFiles() {
        String filename = "Files/allPhonemes.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");
            Type listType = new TypeToken<List<String>>() {
            }.getType();
            return mGson.fromJson(jsonLetters, listType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<List<Integer>> getSimilarPhonemes() {
        String filename = "Files/similarPhonemes.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");
            Type listType = new TypeToken<List<List<Integer>>>() {
            }.getType();
            return mGson.fromJson(jsonLetters, listType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<String> getPhonemeLetters() {
        String filename = "Files/phonemeLetters.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");
            Type listType = new TypeToken<List<String>>() {
            }.getType();
            return mGson.fromJson(jsonLetters, listType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Map<String, List<VowelWord>> getVowels() {
        String filename = "Files/vowels.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(jsonLetters);
            JsonArray jsonArray = element.getAsJsonArray();

            Map<String, List<VowelWord>> map = new HashMap<>();

            Type listType = new TypeToken<List<VowelWord>>() {
            }.getType();

            for(int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                String phoneme = object.get("phoneme").getAsString();
                JsonElement jsonWords = object.get("words");
                List<VowelWord> listWords = mGson.fromJson(jsonWords, listType);
                map.put(phoneme, listWords);
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Map<String, List<ConsonantWord>> getConsonants() {
        String filename = "Files/consonants.json";
        try {
            InputStream inputStream = MyApplication.getAppContext().getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonLetters = new String(buffer, "UTF-8");


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(jsonLetters);
            JsonArray jsonArray = element.getAsJsonArray();

            Map<String, List<ConsonantWord>> map = new HashMap<>();

            Type listType = new TypeToken<List<VowelWord>>() {
            }.getType();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                String phoneme = object.get("phoneme").getAsString();
                JsonElement jsonWords = object.get("words");
                List<ConsonantWord> listWords = mGson.fromJson(jsonWords, listType);
                map.put(phoneme, listWords);
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
