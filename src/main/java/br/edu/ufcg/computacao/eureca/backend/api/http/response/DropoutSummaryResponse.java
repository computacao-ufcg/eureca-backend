package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class DropoutSummaryResponse {
    String periodo;
    DropoutClassification tags;

    public DropoutSummaryResponse(String periodo, DropoutClassification tags) {
        this.periodo = periodo;
        this.tags = tags;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public DropoutClassification getTags() {
        return tags;
    }

    public void setTags(DropoutClassification tags) {
        this.tags = tags;
    }

    private class DropoutClassification {
        int tag1;
        int tag13;
        int tag2;
        int tag3;
        int tag4;
        int tag5;
        int tag6;
        int tag7;
        int tag8;
        int tag9;

        public DropoutClassification(int tag1, int tag13, int tag2, int tag3, int tag4, int tag5, int tag6,
                                     int tag7, int tag8, int tag9) {
            this.tag1 = tag1;
            this.tag13 = tag13;
            this.tag2 = tag2;
            this.tag3 = tag3;
            this.tag4 = tag4;
            this.tag5 = tag5;
            this.tag6 = tag6;
            this.tag7 = tag7;
            this.tag8 = tag8;
            this.tag9 = tag9;
        }

        public int getTag1() {
            return tag1;
        }

        public void setTag1(int tag1) {
            this.tag1 = tag1;
        }

        public int getTag13() {
            return tag13;
        }

        public void setTag13(int tag13) {
            this.tag13 = tag13;
        }

        public int getTag2() {
            return tag2;
        }

        public void setTag2(int tag2) {
            this.tag2 = tag2;
        }

        public int getTag3() {
            return tag3;
        }

        public void setTag3(int tag3) {
            this.tag3 = tag3;
        }

        public int getTag4() {
            return tag4;
        }

        public void setTag4(int tag4) {
            this.tag4 = tag4;
        }

        public int getTag5() {
            return tag5;
        }

        public void setTag5(int tag5) {
            this.tag5 = tag5;
        }

        public int getTag6() {
            return tag6;
        }

        public void setTag6(int tag6) {
            this.tag6 = tag6;
        }

        public int getTag7() {
            return tag7;
        }

        public void setTag7(int tag7) {
            this.tag7 = tag7;
        }

        public int getTag8() {
            return tag8;
        }

        public void setTag8(int tag8) {
            this.tag8 = tag8;
        }

        public int getTag9() {
            return tag9;
        }

        public void setTag9(int tag9) {
            this.tag9 = tag9;
        }
    }
}
