package vo;


public class BookVO{
    private String btitle;
    private String bisbn;
    private String bauthor;
    private int bprice;
    private String bdate;
    private int bpage;
    private String btranslator;
    private String bpublisher;
    private String bimgurl;

//    public BookVO(){
//
//    }

    public BookVO(String bisbn, String btitle, String bauthor, int bprice){
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bauthor = bauthor;
        this.bprice = bprice;
    }

    public String getBisbn() {
        return bisbn;
    }

    public String getBtitle() {
        return btitle;
    }

    public int getBprice() {
        return bprice;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}
