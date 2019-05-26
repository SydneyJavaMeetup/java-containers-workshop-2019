package com.github.sydneyjavameetup;

public class WriteResult {
    private String result;

    public static WriteResult of(String result) {
        WriteResult writeResult = new WriteResult();
        writeResult.result = result;
        return writeResult;
    }

    public String getResult() {
        return result;
    }


}
