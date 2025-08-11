package practice;

public class ParseIpv4Data {

    String parseIpv4(String headerInfo) {
        String tag = headerInfo.substring(8,12);
        String sign = headerInfo.substring(12,14);
        // 转10进制
        Integer tag1 = Integer.valueOf(tag, 16);
        // 转10进制，再2进制
        Integer sign1 = Integer.valueOf(sign, 16);
        String binaryString = Integer.toBinaryString(sign1);
        StringBuilder sb = new StringBuilder();
        while(sb.length() + binaryString.length() < 8){
            sb.append("0");
        }
        Integer signResult = Integer.valueOf(sb.append(binaryString).toString().substring(0, 3), 2);
        return String.valueOf(tag1) + "," + String.valueOf(signResult);
    }

    public static void main(String[] args) {
        ParseIpv4Data parseIpv4Data = new ParseIpv4Data();
        System.out.println(parseIpv4Data.parseIpv4("4500103C3721A0038"));
    }
}
