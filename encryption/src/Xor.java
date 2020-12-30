/**
 * 异或加密
 */
public class Xor {

    public static void main(String[] args) {
        String str="呜呜呜 hesad";
        System.out.println(str);
        //加密
        String str1 = new Xor().xorEncryption(str);
        System.out.println(str1);
        //二次异或加密相当于解密
        System.out.println(new Xor().xorEncryption(str1));
    }


    /**
     * 异或对象
     */
    private static final int XOR_KEY=99999;

    /**
     * 异或加密
     * @param key 被加密对象
     * @return 加密后对象
     */
    public String xorEncryption(String key){
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i]= (char) (chars[i]^XOR_KEY);
        }
        return new String(chars);
    }
}
