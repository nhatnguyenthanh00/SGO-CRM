package com.example.sgo_crm.model;

import java.util.List;

public class FbAdAccountResponse {

    private List<FbAdAccountResponse.Data> data;

    public List<FbAdAccountResponse.Data> getData() {
        return data;
    }

    public void setData(List<FbAdAccountResponse.Data> data) {
        this.data = data;
    }

    public static class Data {
        private String account_id;

        private String id;

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
