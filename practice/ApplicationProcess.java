package practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationProcess {

    List<String> getTop2App(List<Process> prcesses, List<String> sortRules,
                            List<String> selectedUsers) {

        for (Process process : prcesses) {
            if (!selectedUsers.contains(process.userName)) {
                prcesses.remove(process);
            }
        }
        Map<String, Process> nameToProcessMap = new HashMap<>();

        for (Process process : prcesses) {
            if (nameToProcessMap.containsKey(process.appName)) {
                Process tempProcess = nameToProcessMap.get(process.appName);
                tempProcess.setCpuUsed(tempProcess.cpuUsed + process.cpuUsed);
                tempProcess.setMemUsed(tempProcess.memUsed + process.memUsed);
            } else {
                nameToProcessMap.put(process.appName, new Process(process.appName,
                        process.cpuUsed, process.memUsed));
            }
        }
        // 排序
        List<String> result = nameToProcessMap.values().stream().sorted((a, b) -> {
            for (String str : sortRules) {
                if (str.equals("cpuUsed") && a.getCpuUsed() != b.getCpuUsed()) {
                    return b.getCpuUsed() - a.getCpuUsed();
                } else if (str.equals("memUsed") && a.getMemUsed() != b.getMemUsed()) {
                    return b.getMemUsed() - a.getMemUsed();
                }
            }
            return a.getAppName().compareTo(b.getAppName());
        }).limit(3).map(Process::getAppName).collect(Collectors.toList());
        return result;
    }

    class Process {
        String appName;
        String userName;
        int cpuUsed;
        int memUsed;

        public Process(String appName, int cpuUsed, int memUsed) {
            this.appName = appName;
            this.cpuUsed = cpuUsed;
            this.memUsed = memUsed;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setCpuUsed(int cpuUsed) {
            this.cpuUsed = cpuUsed;
        }

        public void setMemUsed(int memUsed) {
            this.memUsed = memUsed;
        }

        public String getAppName() {
            return appName;
        }

        public int getCpuUsed() {
            return cpuUsed;
        }

        public int getMemUsed() {
            return memUsed;
        }
    }

}


