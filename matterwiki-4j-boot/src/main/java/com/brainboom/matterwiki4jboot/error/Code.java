package com.brainboom.matterwiki4jboot.error;



/**
 *
 * error code enum
 * 错误代码枚举
 * @author volun
 *
 * */
public enum Code {



    B101("B101","B101"),
    B102("B102","B102"),
    B103("B103","B103"),
    B104("B104","B104"),
    B105("B105","B105"),
    B106("B106","B106"),
    B107("B107","B107"),
    B108("B108","B108"),
    B109("B109","B109"),
    B110("B110","B110"),
    B111("B111","B111"),
    B112("B112","B112"),
    B113("B113","B113"),
    B114("B114","B114"),
    B115("B115","B115"),
    B116("B116","B116"),
    B117("B117","B117"),
    B118("B118","B118"),
    B119("B119","B119"),
    B120("B120","B120"),
    B121("B121","B121"),
    B122("B122","B122"),
    B123("B123","B123"),
    B124("B124","B124"),
    B125("B125","B125"),
    B126("B126","B126"),
    B127("B127","B127"),
    B128("B128","B128"),
    B129("B129","B129"),
    B130("B130","B130"),
    B131("B131","B131"),
    B132("B132","B132"),
    B133("B133","B133"),
    B134("B134","B134"),
    B135("B135","B135"),
    B136("B136","B136"),
    B137("B137","B137"),
    B138("B138","B138"),
    BNOTADMIN("BNOTADMIN","BNOTADMIN");



    private String code;
    private String message;

    Code(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }







}
