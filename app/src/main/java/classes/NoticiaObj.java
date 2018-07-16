package classes;

import java.io.Serializable;

public class NoticiaObj implements Serializable {
   private String noticia;
   private String data;
   private String txt;

    public NoticiaObj(String noticia, String data, String txt) {
        this.noticia = noticia;
        this.data = data;
        this.txt = txt;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return noticia;
    }
}
