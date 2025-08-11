package practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageReplacement {
    public static int time = 1;

    int calPageReplacementCnt(int frameNum, int windowSize, int[] pages) {
        Map<Integer, Page> dataKeyToMap = new HashMap<>(frameNum);
        if (pages.length <= frameNum) {
            return 0;
        }
        int start = 0;
        while (dataKeyToMap.size() != frameNum) {
            if (dataKeyToMap.containsKey(pages[start])) {
                dataKeyToMap.get(pages[start]).setVisit(dataKeyToMap.get(pages[start]).visit + 1);
                dataKeyToMap.get(pages[start]).setTime(time + 1);
            } else {
                dataKeyToMap.put(pages[start], new Page(1, time++));
            }
            start++;
        }
        int result = 0;
        for (int i = start; i < pages.length; i++) {
            if (dataKeyToMap.containsKey(pages[i])) {
                dataKeyToMap.get(pages[i]).setTime(time++);
                dataKeyToMap.get(pages[i]).setVisit(dataKeyToMap.get(pages[i]).visit + 1);
            } else {
                replacePage(dataKeyToMap, pages[i], windowSize);
                result++;
            }
        }
        return result;
    }

    private void replacePage(Map<Integer, Page> dataKeyToMap, int page, int windowSize) {
        // 选择过期候选
        List<Map.Entry<Integer, Page>> tempCollect = dataKeyToMap.entrySet().stream().sorted((a, b) -> {
            return a.getValue().time - b.getValue().time;
        }).collect(Collectors.toList());
        List<Map.Entry<Integer, Page>> newList = tempCollect.subList(0, windowSize);
        List<Map.Entry<Integer, Page>> result = newList.stream().sorted((a, b) -> {
            if (a.getValue().visit != b.getValue().visit) {
                return a.getValue().visit - b.getValue().visit;
            } else {
                return a.getValue().time - b.getValue().time;
            }
        }).collect(Collectors.toList());
        Integer key = result.get(0).getKey();
        dataKeyToMap.remove(key);
        dataKeyToMap.put(page, new Page(1, time++));
    }

    public static void main(String[] args) {
        PageReplacement pageReplacement = new PageReplacement();
        System.out.println(pageReplacement.calPageReplacementCnt(4, 2, new int[]{1,2,3}));
    }
}

class Page {

    int visit;
    int time;

    public Page(int visit, int time) {
        this.visit = visit;
        this.time = time;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
