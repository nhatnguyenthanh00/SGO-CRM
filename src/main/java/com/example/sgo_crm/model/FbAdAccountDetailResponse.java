package com.example.sgo_crm.model;

import java.util.Date;
import java.util.List;

public class FbAdAccountDetailResponse {

    private FbAdAccountDetailResponse.Data data;

    public FbAdAccountDetailResponse.Data getData() {
        return data;
    }

    public void setData(FbAdAccountDetailResponse.Data data) {
        this.data = data;
    }

    public static class Data {

        private Double spend;

        private Date date_start;

        private Date date_stop;

        public Double getSpend() {
            return spend;
        }

        public void setSpend(Double spend) {
            this.spend = spend;
        }

        public Date getDate_start() {
            return date_start;
        }

        public void setDate_start(Date date_start) {
            this.date_start = date_start;
        }

        public Date getDate_stop() {
            return date_stop;
        }

        public void setDate_stop(Date date_stop) {
            this.date_stop = date_stop;
        }
    }

}
