/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<String>();
    HashMap<String, ArrayList<String>> lettersToWord= new HashMap<String, ArrayList<String>>();
    HashSet<String> wordSet = new HashSet<String>();


    public AnagramDictionary(Reader reader) throws IOException
    {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);

            // get signature for word
            String key = sortLetters(word);

            //lookup key on table
            if(lettersToWord.containsKey(key))
            {
                //key is on table, add word to the List for that key
                ArrayList<String>listofWords = lettersToWord.get(key);
                listofWords.add(word);
            }
            else
            {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(word);
                lettersToWord.put(key,temp);
            }
            Log.d(word, "apple");
        }

    }
    // Function called (sortLetters) that takes a String and returns another
    // string with the same letters in alphabetical order.
    public String sortLetters(String inputword)
    {
        char letters[] = inputword.toCharArray();
        Arrays.sort(letters);
        String signature = new String(letters);
        return signature;
    }

    // Check work tomorrow!
    public boolean isGoodWord(String word, String base)
    {

        if(wordSet.contains(word))
        {
            if(!word.contains(base))
            {
                return true;
            }
        }
        return false;
    }

    public List<String> getAnagrams(String targetWord)
    {
        ArrayList<String> result = new ArrayList<String>();
        // Create signature for the input word
        String targetWordSignature = sortLetters(targetWord.toLowerCase());

        //For each word in the dictionary
        for(String word: wordList)
        {
            //get signature of the word
            String signatureWord = sortLetters(word);

            //compare signature (input word and dictionary word)
            if(targetWordSignature.equals(signatureWord))
            {
                result.add(word);
            }
        }
        return result;
    }



    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
