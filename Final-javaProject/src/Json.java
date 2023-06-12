public class Json {
    public String getValueFromJSON(String jsonStr,String key){
        String value = "";
        int index = jsonStr.indexOf(key);
        if(index != -1){
            index = index + key.length() + 3;
            int index2 = jsonStr.indexOf("\"",index);
            value = jsonStr.substring(index,index2);
        }
        return value;
    }
}
