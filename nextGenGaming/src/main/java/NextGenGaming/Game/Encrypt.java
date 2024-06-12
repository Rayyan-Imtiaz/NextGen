package NextGenGaming.Game;

public class Encrypt {
    private static final int key=65;
    public static String encryptData(String value){
        String encrypted="";
        for(int i = 0;i<=value.length()-1;i++){
            char in = value.charAt(i);
            in +=key;
            encrypted+=in;
        }
        return encrypted;

    }
    public static String dcryptData(String encrypt) {
        String dcrypted = "";
        for (int i = 0; i <= encrypt.length() - 1; i++) {
            char out = encrypt.charAt(i);
            out -= key;
            dcrypted += out;

        }
        return dcrypted;
    }


}
    

