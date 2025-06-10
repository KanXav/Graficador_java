
public class Pentagono {

    // Atributos
    private double lado, apotema, area, perimetro;

    // Métodos
    public double calcularPerimetro() {
        perimetro = lado * 5;
        return perimetro;
    }

    public double calcularArea() {
        area = calcularPerimetro() * apotema / 2;
        return area;
    }

    // Get y set para lado
    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    // Get y set para apotema
    public double getApotema() {
        return apotema;
    }

    public void setApotema(double apotema) {
        this.apotema = apotema;
    }

    // toString
    @Override
    public String toString() {
        return "Tu pentágono tiene las siguientes medidas: \n" +
               "Lado: " + lado +
               "\nApotema: " + apotema +
               "\nÁrea: " + calcularArea() +
               "\nPerímetro: " + calcularPerimetro();
    }

    // Constructores
    public Pentagono() {
    }

    public Pentagono(double nuevoLado, double nuevaApotema) {
        lado = nuevoLado;
        apotema = nuevaApotema;
    }
}
