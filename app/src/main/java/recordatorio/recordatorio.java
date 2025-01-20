package recordatorio;

import java.util.Date;

public class recordatorio
{
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setCuerpo(String cuerpo) {
        Cuerpo = cuerpo;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getCuerpo() {
        return Cuerpo;
    }

    public String getFecha() {
        return Fecha;
    }

    private String Titulo;
    private String Cuerpo;
    private String Fecha;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public recordatorio(String _Titulo, String _Cuerpo, String _fecha) {
        Titulo = _Titulo;
        Cuerpo = _Cuerpo;
        Fecha = _fecha;
    }
}
