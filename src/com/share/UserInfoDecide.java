package com.share;

/*
* 判断是否为数字或字符
* */
public class UserInfoDecide {

    private boolean isDigit = false;//判断是否有数字
    private boolean isLetter = false;//判断是否为字符
    private boolean ischar = false;//判断是否为字母和数字

    public boolean decideIs(String str){

        for(int i = 0; i < str.length(); i++){

            if(Character.isDigit(str.charAt(i))){//判断是否为数字
                isDigit = true;
            }else if(Character.isLetter(str.charAt(i))){//判断是否为字母
                isLetter = true;
            }else if( Character.isDigit(str.charAt(i)) == false && Character.isLetter(str.charAt(i)) == false){
                //要求只能是数字和字母
                ischar = true;
                break;
            }
        }

        if(isDigit == false){
            return false;
        }else if(isLetter == false ){
            return false;
        }else if(ischar == true ){
            return false;
        }

        return true;
    }
}
