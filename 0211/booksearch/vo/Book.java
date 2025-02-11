package booksearch.vo;

public class Book {
    private String btitle;
    private String bisbn;
    private String bauthor;
    private int bprice;
    private String bdate;
    private int bpage;
    private String btranslator;
    private String bpublisher;
    private String bimgurl;

    public Book(){

    }

    public Book(String bisbn, String btitle, String bauthor, int bprice){
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bauthor = bauthor;
        this.bprice = bprice;
    }

    public String getBauthor() {
        return bauthor;
    }

    public int getBprice() {
        return bprice;
    }

    public String getBtitle() {
        return btitle;
    }

    public String getBisbn() {
        return bisbn;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }
}