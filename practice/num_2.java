package practice;

import java.util.*;

public class num_2 {
    Map<String, List<String>> strToListMap = new HashMap<>();

    public List<List<String>> groupAnagrams(String[] strs) {
        for (String str : strs) {
            char[] charList = str.toCharArray();
            Arrays.sort(charList);
            String strKey = new String(charList);
            List<String> defaultList = strToListMap.getOrDefault(strKey, new ArrayList<>());
            defaultList.add(str);
            strToListMap.put(strKey, defaultList);
        }
        return strToListMap.values().stream().toList();
    }

    public static void main(String[] args) {
        num_2 num2 = new num_2();
        num2.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }
}
