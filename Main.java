package HW5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    // 2085. Count Common Words With One Occurrence
    // Given two string arrays words1 and words2, return the number of strings
    // that appear exactly once in each   of the two arrays.

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words1) {
            map.putIfAbsent(word, 0);
            map.put(word, map.get(word) + 1);
        }

        for (String word : words2) {
            if (map.containsKey(word) && map.get(word) <= 1) {
                map.put(word, map.get(word) - 1);
            }
        }

        int count = 0;
        for (String i : map.keySet()) {
            if (map.get(i) == 0) count++;
        }

        return count;
    }


    // 609. Find Duplicate File in System
    // Given a list paths of directory info, including the directory path,
    // and all the files with contents in this directory,
    // return all the duplicate files in the file system in terms of their paths.
    // You may return the answer in any order.
    //A group of duplicate files consists of at least two files that have the same content

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] fileP = path.split(" ");
            String dir = fileP[0];

            for (int i = 1; i < fileP.length; i++) {
                int startInd = fileP[i].indexOf('(');
                startInd++;
                int endInd = startInd;
                while (fileP[i].charAt(endInd) != ')') endInd++;

                String content = fileP[i].substring(startInd, endInd);
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }
                map.get(content).add(dir + "/" + fileP[i].substring(0, startInd - 1));
            }
        }

        for (String file : map.keySet()) {
            if (map.get(file).size() > 1) {
                result.add(map.get(file));
            }
        }
        return result;
    }
}